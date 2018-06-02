package com.nz.onlineMonitoring.deviceInfo.service.impl;

import com.nz.onlineMonitoring.deviceInfo.mapper.DeviceMapper;
import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	/**
	 * t_device
	 * 方法描述: 设备信息查询
	 * @param map,device
	 * @return
	 * @throws
	 *
	 **/
	@Override
	public List<Map<String, Object>> queryDeviceInfo(Map<String, Object> map, Device device) {

		List<Map<String, Object>> queryList = deviceMapper.queryDeviceInfo(map);

		if(queryList == null || queryList.size() <= 0){
			throw new  RuntimeException("暂无数据！");
		}
		return queryList;
	}
}
