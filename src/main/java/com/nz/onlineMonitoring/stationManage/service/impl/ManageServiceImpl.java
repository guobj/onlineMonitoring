package com.nz.onlineMonitoring.stationManage.service.impl;

import com.nz.onlineMonitoring.data.mapper.DataMapper;
import com.nz.onlineMonitoring.data.model.Data;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationManage.mapper.ManageMapper;
import com.nz.onlineMonitoring.stationManage.model.Manage;
import com.nz.onlineMonitoring.stationManage.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

	@Autowired
	private ManageMapper manageMapper;

	@Autowired
	private DataMapper dataMapper;
	/**
	 * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
	 * @author guobj
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryStationInfo(Map<String, Object> map) {

		//查询站点配置的相关信息
		List<Map<String, Object>> stationInfoList = manageMapper.queryStationInfo(map);
		//查询记录总数
		Integer count = manageMapper.count(map);
		if(stationInfoList == null || stationInfoList.size() <= 0){
			throw new RuntimeException("暂无相关数据！");
		}else{
			map.put("stationInfoList",stationInfoList);
			map.put("count",count);
		}

		return stationInfoList;
	}

	/**
	 * 修改监测站属性信息
	 * @author guobj
	 * @param
	 * @return
	 */
	@Override
	public Integer updateById(Map<String, Object> map, Manage manage) {

		Integer res = manageMapper.updateById(manage);

		if(res <= 0){
			throw new RuntimeException("更新失败！");
		}
		return res;
	}

	/**
	 * t_manage
	 * 方法描述:删除监测站相关信息。
	 * @author guobj
	 * @param
	 * @return
	 **/
	@Override
	public Integer deleteById(Integer id) {

		Integer res = manageMapper.deleteById(id);

		if(res <= 0){
			throw new RuntimeException("删除失败！");
		}
		return res;
	}

	/**
	 * t_manage
	 * 方法描述:通过id查看配置信息
	 * @author guobj
	 * @param
	 * @return
	 **/
	@Override
	public Manage load(Map<String, Object> map, Integer id) {
		Manage manage = manageMapper.load(id);
		if(manage == null){
			throw new RuntimeException("数据不存在");
		}
		map.put("manage", manage);
		return manage;
	}
}
