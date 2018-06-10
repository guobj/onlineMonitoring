package com.nz.onlineMonitoring.deviceInfo.controller;

import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import com.nz.onlineMonitoring.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	//设备信息管理查询
	@RequestMapping(value = "/queryDeviceInfo")
	public String queryDeviceInfo(Map<String, Object> map, Device device,
									   @RequestParam(required=false,defaultValue="1")Integer pages, HttpServletRequest request) {

		try {
			//存储前端传来的数据
			map = PageBean.serverMap(map, device, pages);
			List<Device> list = deviceService.queryDeviceInfo(map,device);
			//返回给前端的数据
			map = PageBean.clientMap(map, pages, request);
		}catch (Exception e){
			map.put("message", e.getMessage());
		}
		return  "device/devInfoManage_list";
	}

	//设备信息查询（设备采集数据图片再次接口上写）
	@RequestMapping(value = "/queryDeviceInfoList")
	public String queryDeviceInfoList(Map<String, Object> map, Device device,
								  @RequestParam(required=false,defaultValue="1")Integer pages,
								  @RequestParam(required=false,name="city")String[] citys,
								  String dev_object,HttpServletRequest request) {
		if(dev_object != null && !dev_object.equals("")){
			device.setDev_code("dev"+dev_object);
		}
		try {
			if (citys != null) {
				if (citys[1] != null && citys[1] != "") {
					device.setMs_code(citys[1]);
				}else if (citys[0] != null && citys[0] != "") {
					device.setMs_code(citys[0]);
				}else {
					device.setMs_code("37");
				}
			}
			//存储前端传来的数据
			map = PageBean.serverMap(map, device, pages);
			List<Device> list = deviceService.queryDeviceInfo(map,device);
			//返回给前端的数据
			map = PageBean.clientMap(map, pages, request);
		}catch (Exception e){
			map.put("message", e.getMessage());
		}
		return  "device/deviceInfo_list";
	}
}
