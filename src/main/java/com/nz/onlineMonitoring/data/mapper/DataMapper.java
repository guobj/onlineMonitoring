/**
 * DataMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.data.mapper;

import com.nz.onlineMonitoring.data.model.Data;

public interface DataMapper {
    /**
     * t_data
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(Data data);

	/*t_manage连表使用load方法*/
	/**
	 * t_data
	 * 方法描述: 在data_type="ms_type"时，查询一条记录
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Data loadByDataValue(Integer data_value);


	/*t_device连表所使用load方法*/
	/**
	 * t_data
	 * 方法描述: 在data_type="dev_protocol"时，查询一条记录
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Data loadByDevProtocol(Integer data_value);

	/**
	 * t_data
	 * 方法描述: 在data_type="dev_interface"时，查询一条记录
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Data loadByDevInterface(Integer data_value);

	/**
	 * t_data
	 * 方法描述: 在data_type="dev_port"时，查询一条记录
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Data loadByDevPort(Integer data_value);

	/**
	 * t_data
	 * 方法描述: 在data_type="dev_type"时，通过data_value查询一条记录
	 * @param
	 * @return
	 * @date 2018-06-01 11:08:33
	 *
	 **/
	Data loadByDevType(Integer data_value);
	/**
	 *
	 * 方法描述：获取单个监测站所拥有的监测设备编号的值
	 * @param data_value
	 * @return
	 * @date 2018年6月2日 上午11:58:41
	 */
	Data loadDev(int data_value);
	/**
	 *
	 * 方法描述：获取单个监测站的类型(重点、普通、新建、改建)
	 * @param data_value
	 * @return
	 * @date 2018年6月2日 上午11:59:17
	 */
	Data loadType(int data_value);
	/**
	 *
	 * 方法描述：获取单个监测站的资金来源(省、国家)
	 * @param data_value
	 * @return
	 * @date 2018年6月2日 下午12:00:13
	 */
	Data loadFp(int data_value);
	/**
	 *
	 * 方法描述：获取单个监测站网络类型（有线、无线）
	 * @param data_value
	 * @return
	 * @date 2018年6月2日 下午12:00:16
	 */
	Data loadNet(int data_value);
	/**
	 *
	 * 方法描述：获取单个监测站网络类型
	 * @param data_value
	 * @return
	 * @date 2018年6月2日 下午12:00:19
	 */
	Data loadGate(int data_value);
	/**
	 *
	 * 方法描述：查询山东省的所有市
	 * @param data_value
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午9:24:18
	 */
	List<Data> listCity();
	/**
	 *
	 * 方法描述：根据市的id，查询市的区，会把市的长度取前四位，然后模糊查询
	 * @param data_value
	 * @return
	 * @author ssh
	 * @date 2018年6月5日 上午9:24:52
	 */
	List<Data> listArea(int data_value);
	/**
	 *
	 * 方法描述：查询设备的全部状态
	 * @return
	 * @author ssh
	 * @date 2018年6月6日 下午9:27:03
	 */
	List<Data> listDevStauts();
	/**
	 *
	 * 方法描述：查询设备监测的对象
	 * @return
	 * @author ssh
	 * @date 2018年6月6日 下午9:27:28
	 */
	List<Data> listDevType();
	/**
	 *
	 * 方法描述：查询设备的类型
	 * @return
	 * @author ssh
	 * @date 2018年6月6日 下午9:27:51
	 */
	List<Data> listDevType1();
}