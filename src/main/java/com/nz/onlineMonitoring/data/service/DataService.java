package com.nz.onlineMonitoring.data.service;

import java.util.List;

import com.nz.onlineMonitoring.data.model.Data;

public interface DataService {
    /**
     * 
     * 方法描述：设备类型
     * @param data_value
     * @return
     * @author ssh
     * @date 2018年6月8日 下午4:37:31
     */
    Data loadByDevType1(Integer data_value);
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
    /**
     * 
     * 方法描述：设备编码
     * @return
     * @author ssh
     * @date 2018年6月8日 下午4:55:53
     */
    List<Data> listMsDev();
    /**
     * 
     * 方法描述：查询全部资金来源
     * @return
     * @author ssh
     * @date 2018年6月8日 上午10:08:59
     */
    List<Data> listMsFp();
    /**
     * 
     * 方法描述：查询全部监测站类型
     * @return
     * @author ssh
     * @date 2018年6月8日 上午10:08:59
     */
    List<Data> listMsType();
    /**
     * 
     * 方法描述：查询全部网关类型
     * @return
     * @author ssh
     * @date 2018年6月8日 上午10:08:59
     */
    List<Data> listMsGate();
    /**
     * 
     * 方法描述：查询全部网络类型
     * @return
     * @author ssh
     * @date 2018年6月8日 上午10:08:59
     */
    List<Data> listMsNet();
   
}
