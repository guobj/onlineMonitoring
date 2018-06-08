package com.nz.onlineMonitoring.stationManage.controller;

import com.nz.onlineMonitoring.stationManage.model.Manage;
import com.nz.onlineMonitoring.stationManage.service.ManageService;
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
public class ManageController {

	@Autowired
	private ManageService manageService;

	@RequestMapping(value = "/queryStationInfo")
	public String queryStationInfo(Map<String, Object> map, Manage manage,
										@RequestParam(required=false,defaultValue="1")Integer pages,
								   @RequestParam(required=false,name="city")String[] citys, HttpServletRequest request){
		try {
			if (citys != null) {
				if (citys[1] != null && citys[1] != "") {
					manage.setMs_code(citys[1]);
				}else if (citys[0] != null && citys[0] != "") {
					manage.setMs_code(citys[0]);
				}else {
					manage.setMs_code("37");
				}
			}
			//存储前台传过来的值
			map = PageBean.serverMap(map,manage,pages);
			//将数据返回前端
			List<Map<String, Object>> list = manageService.queryStationInfo(map);
			//返回前端页面的值
			map = PageBean.clientMap(map,pages,request);
		}catch (Exception e){
			map.put("message",e.getMessage());
		}

		return "stationManage/stationManage_list";
	}

	@RequestMapping("/load")
	@ResponseBody
	public JacksonData load(Map<String, Object> map,@RequestParam Integer id){
		JacksonData jacksonData = new JacksonData();
		try {
			Manage manage = manageService.load(map,id);
			jacksonData.success(manage);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}
		return jacksonData;
	}
	@RequestMapping(value = "/updateById",method = RequestMethod.POST)
	@ResponseBody
	public JacksonData updateById(Map<String, Object> map,Manage manage){
		JacksonData jacksonData = new JacksonData();

		try {
			Integer res = manageService.updateById(map, manage);
			jacksonData.success(res);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}
		return jacksonData;
	}

	@RequestMapping(value = "/deleteById",method = RequestMethod.GET)
	@ResponseBody
	public JacksonData deleteById(Integer id){
		JacksonData jacksonData = new JacksonData();
		try {
			Integer res = manageService.deleteById(id);
			jacksonData.success(res);
		}catch (Exception e){
			jacksonData.failure(e.getMessage());
		}
		return jacksonData;
	}
	@RequestMapping(value = "/foreward")
	public String foreward(){
		return "test/";
	}

	@RequestMapping(value = "/foreward2")
	public String foreward2(){
		return "test/index_1";
	}

}
