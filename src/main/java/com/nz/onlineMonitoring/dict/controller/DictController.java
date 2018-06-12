package com.nz.onlineMonitoring.dict.controller;

import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.dict.service.DictService;
import com.nz.onlineMonitoring.utils.JacksonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class DictController {

	@Autowired
	private DictService dictService;
	/**
	 *
	 * 方法描述：查询山东省的所有市,使用ajax获取
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午10:32:24
	 */
	@RequestMapping("/listCity")
	@ResponseBody
	public List<Dict> listCity() { return dictService.listCity();
	}

	/**
	 *
	 * 方法描述：根据市的id，查询市的区，会把市的长度取前四位，然后模糊查询,使用ajax获取
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午10:32:24
	 */
	@RequestMapping("/listArea")
	@ResponseBody
	public List<Dict> listArea(Integer city_id) {
		if (city_id == null) {
			return null;
		}
		return dictService.listArea(city_id);
	}

	/**
	 *
	 * 方法描述：获取监测对象和监测类型
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午10:32:24
	 */
	@RequestMapping("/queryDevType")
	@ResponseBody
	public JacksonData queryDevType() {
		JacksonData jacksonData = new JacksonData();
		Map<String, Object> map = new HashMap<>();
		//获取检测对象
		List<Dict> devObject = dictService.listDevType();
		//获取监测类型
		List<Dict> devType = dictService.listDevType1();
		map.put("devObject", devObject);
		map.put("devType", devType);
		jacksonData.success(map);
		return jacksonData;
	}

	/**
	 *
	 * 方法描述：获取资金来源、监测站类型、网管类型
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午10:32:24
	 */
	@RequestMapping("/queryTypeAndFpAndGate")
	@ResponseBody
	public JacksonData queryTypeAndFpAndGate() {
		JacksonData jacksonData = new JacksonData();
		Map<String, Object> map = new HashMap<>();
		//获取监测站类型
		List<Dict> msTypes = dictService.listMsType();
		//获取资金来源
		List<Dict> msFps = dictService.listMsFp();
		//获取网管类型
		List<Dict> msGates = dictService.listMsGate();
		map.put("msTypes", msTypes);
		map.put("msFps", msFps);
		map.put("msGates", msGates);
		jacksonData.success(map);
		return jacksonData;
	}
    
}
