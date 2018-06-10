package com.nz.onlineMonitoring.deviceInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.deviceInfo.mapper.DeviceMapper;
import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private DictMapper dictMapper;

	/**
	 * t_device
	 * 方法描述: 设备信息查询
	 * @param map,device
	 * @return
	 * @throws
	 *
	 **/
	@Override
	public List<Device> queryDeviceInfo(Map<String, Object> map, Device device) {

		List<Device> queryList = deviceMapper.queryDeviceInfo(map);

		//记录总数
		Integer count = deviceMapper.count(map);
		if(queryList == null || queryList.size() <= 0){
			throw new  RuntimeException("暂无数据！");
		}else{
			for (Device dev : queryList) {

				//获取设备编码进行解析
//			String dev_code = dbmap.get("dev_code").toString();
				String dev_code = dev.getDev_code();
				char c = '0';
				if(dev_code != null && dev_code != ""){
					c = dev_code.charAt(3);
				}
				Dict data = dictMapper.loadByDevType(Character.getNumericValue(c));
//			dbmap.put("dev_code",data.getData_name());
				dev.setDev_code(data.getData_name());
			}
			map.put("list",queryList);
			map.put("count",count);
		}
		return queryList;
	}
}
