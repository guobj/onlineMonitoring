package com.nz.onlineMonitoring.deviceInfo.controller;

import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import com.nz.onlineMonitoring.utils.JacksonData;
import com.nz.onlineMonitoring.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/queryDeviceInfo",method = RequestMethod.POST)
	@ResponseBody
	public JacksonData queryDeviceInfo(Map<String, Object> map, Device device,
									   @RequestParam(required=false,defaultValue="1")Integer pages, HttpServletRequest request) {

		JacksonData jacksonData = new JacksonData();
		try {
			//存储前端传来的数据
			map = PageBean.serverMap(map, device, pages);
			List<Device> list = deviceService.queryDeviceInfo(map,device);
			//返回给前端的数据
			map = PageBean.clientMap(map, pages, request);
			jacksonData.success(list);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}
		return  jacksonData;
	}
}
