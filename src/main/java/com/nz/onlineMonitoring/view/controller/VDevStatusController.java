package com.nz.onlineMonitoring.view.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nz.onlineMonitoring.utils.AuthorityUtil;
import com.nz.onlineMonitoring.utils.PageBean;
import com.nz.onlineMonitoring.view.model.VDevStatus;
import com.nz.onlineMonitoring.view.service.VDevStatusService;

@Controller
@RequestMapping("/vDevStatus")
public class VDevStatusController {

	@Autowired
	private VDevStatusService vDevStatusService;

	//设备状态查看
	@RequestMapping(value = "/devcieStatusList")
	public String devcieStatusList(Map<String, Object> map, VDevStatus vDevStatus,
										@RequestParam(required=false,defaultValue="1")Integer pages,
										@RequestParam(required=false,name="city")String[] citys,
										String dev_object,HttpServletRequest request){
		if(dev_object != null && !dev_object.equals("")){
			vDevStatus.setDev_code("dev"+dev_object);
		}
		try {
			AuthorityUtil.getInstance().assignPermissions(citys, request, vDevStatus);
			map = PageBean.serverMap(map, vDevStatus, pages);
			List<VDevStatus> vDevStatusList = vDevStatusService.deviceStatusList(map,vDevStatus);
			//返回给前端的数据
			map = PageBean.clientMap(map, pages, request);
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		return "device/deviceStatus_list";
	}
}
