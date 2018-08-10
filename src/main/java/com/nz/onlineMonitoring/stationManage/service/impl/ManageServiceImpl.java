package com.nz.onlineMonitoring.stationManage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.stationManage.mapper.ManageMapper;
import com.nz.onlineMonitoring.stationManage.model.Manage;
import com.nz.onlineMonitoring.stationManage.service.ManageService;

@Service
public class ManageServiceImpl implements ManageService {

	@Autowired
	private ManageMapper manageMapper;

	@Autowired
	private DictMapper dictMapper;
	/**
	 * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
	 * @author guobj
	 * @param
	 * @return
	 */
	@Override
	public List<Manage> queryStationInfo(Map<String, Object> map) {

		//查询站点配置的相关信息
		List<Manage> stationInfoList = manageMapper.queryStationInfo(map);
		//查询记录总数
		Integer count = manageMapper.count(map);
		if(stationInfoList == null || stationInfoList.size() <= 0){
			throw new RuntimeException("暂无数据");
		}else{
			List<Dict> listData = dictMapper.listMsDev();
			Map<String, String> mapDev = new HashMap<>();
			//将data中的ms_code的value作为键，name作为值
			for (Dict d : listData) {
				mapDev.put(String.valueOf(d.getData_value()), d.getData_name());
			}
			//循环listStation,将ms_code中的值，一一取出，然后通过值去mapdev中比较，拿到具体的名字，然后拼接到ms_dev_value中
			for (Manage manage : stationInfoList) {
				if (manage.getStation().getMs_dev() != null && !Objects.equals(manage.getStation().getMs_dev(), "")) {
					String[] temp = manage.getStation().getMs_dev().split(",");
					for (int i = 0,n = temp.length; i < n; i++) {
						temp[i] = mapDev.get(temp[i]);
						//将设备编码解析成具体的name
						StringBuffer sb = new StringBuffer();
						sb.append(dictMapper.loadByDevType(Integer.parseInt(temp[i].substring(3, 4))).getData_name());
						sb.append(dictMapper.loadByDevType1(Integer.parseInt(temp[i].substring(3, 6))).getData_name());
						temp[i] = sb.toString();
					}
					manage.getStation().setMs_dev_value(String.join(",", temp));
				}
			}
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
		List<Dict> listData = dictMapper.listMsDev();
		Map<String, String> mapDev = new HashMap<>();
		//将data中的ms_code的value作为键，name作为值
		for (Dict d : listData) {
			mapDev.put(String.valueOf(d.getData_value()), d.getData_name());
		}
		//因为ms_dev中的数据是用，分开的多个数据，所以没法用mapper直接查询，如果ms_dev不等于空，那么循环其中的数据，把从字典表中拿到的name值，拼接成字符串，传到ms_dev_value,用，隔开
		if (manage.getStation().getMs_dev() != null && !Objects.equals(manage.getStation().getMs_dev(), "")) {
			String[] temp = manage.getStation().getMs_dev().split(",");
			for (int i = 0, n = temp.length; i < n; i++) {
				temp[i] = mapDev.get(temp[i]);
				StringBuffer sb = new StringBuffer();
				sb.append(dictMapper.loadByDevType(Integer.parseInt(temp[i].substring(3, 4))).getData_name());
				sb.append(dictMapper.loadByDevType1(Integer.parseInt(temp[i].substring(3, 6))).getData_name());
				temp[i] = sb.toString();
			}
			manage.getStation().setMs_dev_value(String.join(",", temp));
		}
		//解析监测站编码
		String ms_code = manage.getStation().getMs_code();
		String code01 = ms_code.substring(0, 6);
		String code02 = ms_code.substring(4, 6);

		String code2 = ms_code.substring(6, 8);
		if (code02.equals("00")) {
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			manage.getStation().setMs_code(name1 + "第" + code2 +"个");
		}else {
			String code03 = ms_code.substring(0, 4);
			code03 += "00";
			String name2= dictMapper.loadCity(Integer.parseInt(code03)).getData_name();
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			manage.setMs_code(name2+name1 + "第" + code2 +"个");
		}
		map.put("manage", manage);
		return manage;
	}
}
