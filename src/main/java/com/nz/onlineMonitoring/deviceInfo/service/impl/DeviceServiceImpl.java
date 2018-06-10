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
				Integer dev_type = 0;
				if(dev_code != null && dev_code != ""){
					c = dev_code.charAt(3);
					String str = dev_code.substring(3, 6);
					dev_type = Integer.parseInt(str);
				}
				StringBuilder sb = new StringBuilder();
				sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
				sb.append(dictMapper.loadByDevType1(dev_type).getData_name());
//			dbmap.put("dev_code",data.getData_name());
				dev.setDev_code(sb.toString());
			}
			map.put("list",queryList);
			map.put("count",count);
		}
		return queryList;
	}

	@Override
	public Device deviceLoad(Integer id) {
		Device device = deviceMapper.load(id);
		if(device == null){
			throw new RuntimeException("数据不存在");
		}
		String dev_code = device.getDev_code();
		//设备编码解析开始
		char c = '0';
		Integer dev_type = 0;
		if(dev_code != null && dev_code != ""){
			c = dev_code.charAt(3);
			String str = dev_code.substring(3, 6);
			dev_type = Integer.parseInt(str);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
		sb.append(dictMapper.loadByDevType1(dev_type).getData_name());
		device.setDev_code(sb.toString());
		//设备编码解析结束
		//监测站编码解析开始
		String ms_code = device.getStation().getMs_code();
		String code01 = ms_code.substring(0, 6);
		String code02 = ms_code.substring(4, 6);

		String code2 = ms_code.substring(6, 8);
		if (code02.equals("00")) {
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			device.getStation().setMs_code(name1 + "第" + code2 +"个");
		}else {
			String code03 = ms_code.substring(0, 4);
			code03 += "00";
			String name2= dictMapper.loadCity(Integer.parseInt(code03)).getData_name();
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			device.setMs_code(name2+name1 + "第" + code2 +"个");
		}
		//监测站编码解析结束
		return device;
	}
}
