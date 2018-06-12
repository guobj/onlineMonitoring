package com.nz.onlineMonitoring.view.controller;

import com.nz.onlineMonitoring.utils.JacksonData;
import com.nz.onlineMonitoring.utils.PageBean;
import com.nz.onlineMonitoring.view.model.VDevStatus;
import com.nz.onlineMonitoring.view.service.VDevStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
		if (citys != null) {
			if (citys[1] != null && citys[1] != "") {
				vDevStatus.setMs_code(citys[1]);
			}else if (citys[0] != null && citys[0] != "") {
				vDevStatus.setMs_code(citys[0]);
			}else {
				vDevStatus.setMs_code("37");
			}
		}
		JacksonData jacksonData = new JacksonData();
		try {
			map = PageBean.serverMap(map, vDevStatus, pages);
			List<VDevStatus> vDevStatusList = vDevStatusService.deviceStatusList(map,vDevStatus);
			//返回给前端的数据
			map = PageBean.clientMap(map, pages, request);
			jacksonData.success(vDevStatusList);
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		return "device/deviceStatus_list";
//		return jacksonData;
	}
}
