package com.nz.onlineMonitoring.stationManage.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ManageService {

	/**
	 * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
	 * @author guobj
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryStationInfo(Map<String, Object> map);
}
