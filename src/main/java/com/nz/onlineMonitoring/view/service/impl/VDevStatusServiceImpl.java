package com.nz.onlineMonitoring.view.service.impl;

import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.view.mapper.VDevStatusMapper;
import com.nz.onlineMonitoring.view.model.VDevStatus;
import com.nz.onlineMonitoring.view.service.VDevStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VDevStatusServiceImpl implements VDevStatusService {

	@Autowired
	private VDevStatusMapper vDevStatusMapper;

	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<VDevStatus> deviceStatusList(Map<String, Object> map, VDevStatus vDevStatus) {
		List<VDevStatus> vDevStatusList = vDevStatusMapper.deviceStatusList(map);
		Integer count = vDevStatusMapper.count(map);
		if(vDevStatusList == null || vDevStatusList.size() <= 0){
			throw new RuntimeException("暂无数据");
		}
		for (VDevStatus vds : vDevStatusList) {

			//获取设备编码进行解析
//			String dev_code = dbmap.get("dev_code").toString();
			String dev_code = vds.getDev_code();
			char c = '0';
			Integer dev_type = 0;
			if(dev_code != null && dev_code != ""){
				c = dev_code.charAt(3);
				String str = dev_code.substring(3, 6);
				dev_type = Integer.parseInt(str);
			}
			StringBuilder sb = new StringBuilder();
			sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
//			sb.append(dictMapper.loadByDevType1(dev_type).getData_name());
//			dbmap.put("dev_code",data.getData_name());
			vds.setDev_code(sb.toString());
		}
		map.put("list", vDevStatusList);
		map.put("count", count);
		return vDevStatusList;
	}
}
