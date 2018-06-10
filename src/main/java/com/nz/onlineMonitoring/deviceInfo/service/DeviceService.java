package com.nz.onlineMonitoring.deviceInfo.service;

import com.nz.onlineMonitoring.deviceInfo.model.Device;

import java.util.List;
import java.util.Map;

public interface DeviceService {

	/**
	 * t_device
	 * 方法描述: 设备信息查询
	 * @param map,device
	 * @return
	 * @throws
	 *
	 **/
	public List<Device> queryDeviceInfo(Map<String, Object> map, Device device);

	/**
	 * t_device
	 * 方法描述: 设备信息查询  查询一条数据
	 * @param id
	 * @return
	 * @throws
	 *
	 **/
	public Device deviceLoad(Integer id);
}
