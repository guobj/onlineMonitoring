package com.nz.onlineMonitoring.stationManage.service;

import com.nz.onlineMonitoring.stationManage.model.Manage;
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

	/**
	 * 修改监测站属性信息
	 * @author guobj
	 * @param
	 * @return
	 */
	public Integer updateById(Map<String, Object> map, Manage manage);

	/**
	 * t_manage
	 * 方法描述:删除监测站相关信息。
	 * @author guobj
	 * @param
	 * @return
	 **/
	public Integer deleteById(Integer id);
}
