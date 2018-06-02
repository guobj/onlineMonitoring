package com.nz.onlineMonitoring.deviceInfo.controller;

import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import com.nz.onlineMonitoring.utils.JacksonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/queryDeviceInfo",method = RequestMethod.POST)
	@ResponseBody
	public JacksonData queryDeviceInfo(Map<String, Object> map, Device device) {

		JacksonData jacksonData = new JacksonData();
		try {
			List<Map<String, Object>> list = deviceService.queryDeviceInfo(map,device);
			jacksonData.success(list);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}
		return  jacksonData;
	}
}
