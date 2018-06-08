/**
 * ManageMapper.java
 * * 济南农智信息科技有限公司所有
 *  * Create By guobj
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.stationManage.mapper;

import com.nz.onlineMonitoring.stationManage.model.Manage;

import java.util.List;
import java.util.Map;

public interface ManageMapper {
	/**
	 * t_manage
	 * 方法描述:删除监测站相关信息。
	 * @author guobj
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Integer deleteById(Integer id);

	/**
	 * t_manage
	 * 方法描述:通过id查查询一条数据
	 * @author guobj
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Manage load(Integer id);

	/**
	 * t_manage
	 * 方法描述:修改监测站属性信息
	 * @author guobj
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Integer updateById(Manage manage);

	/**
	 * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
	 * @author guobj
	 * @param
	 * @return
	 */
	List<Manage> queryStationInfo(Map<String, Object> map);

	/**
	 * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
	 * 查询记录总数
	 * @author guobj
	 * @param
	 * @return
	 */
	Integer count(Map<String, Object> map);
}