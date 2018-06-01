package com.nz.onlineMonitoring.stationManage.controller;

import com.nz.onlineMonitoring.stationManage.model.Manage;
import com.nz.onlineMonitoring.stationManage.service.ManageService;
import com.nz.onlineMonitoring.utils.JacksonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ManageController {

	@Autowired
	private ManageService manageService;

	@RequestMapping(value = "/queryStationInfo", method = RequestMethod.POST)
	@ResponseBody
	public JacksonData queryStationInfo(Map<String, Object> map, Manage manage){

		JacksonData jacksonData = new JacksonData();
		try {
			//存储前台传过来的值
			map.put("manage",manage);

			//将数据返回前端
			List<Map<String, Object>> list = manageService.queryStationInfo(map);

			//成功返回数据u

			jacksonData.success(list);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}

		return jacksonData;
	}

	@RequestMapping(value = "/foreward")
	public String foreward(){
		return "test/index_1";
	}

	@RequestMapping(value = "/foreward2")
	public String foreward2(){
		return "test/index";
	}

}
