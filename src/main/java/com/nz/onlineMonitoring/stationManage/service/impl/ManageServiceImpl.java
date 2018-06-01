package com.nz.onlineMonitoring.stationManage.service.impl;

import com.nz.onlineMonitoring.stationManage.mapper.ManageMapper;
import com.nz.onlineMonitoring.stationManage.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

	@Autowired
	private ManageMapper manageMapper;

	@Override
	public List<Map<String, Object>> queryStationInfo(Map<String, Object> map) {

		//查询站点配置的相关信息
		List<Map<String, Object>> stationInfoList = manageMapper.queryStationInfo(map);

		if(stationInfoList == null || stationInfoList.size() <= 0){
			throw new RuntimeException("暂无相关数据！");
		}else{
			map.put("stationInfoList",stationInfoList);
		}

		return stationInfoList;
	}
}
