/**
 * DeviceMapper.java
 * 济南农智信息科技有限公司所有
 * Create By guobj
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.deviceInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nz.onlineMonitoring.deviceInfo.model.Device;

public interface DeviceMapper {
    /**
     * t_device
     * 方法描述:
     * @param  id
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int delete(Integer id);

    /**
     * t_device
     * 方法描述:
     * @param  device
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(Device device);

    /**
     * t_device
     * 方法描述:
     * @param  id
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    Device load(Integer id);

    /**
     * t_device
     * 方法描述:
     * @param device
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int update(Device device);

    /**
     * t_device
     * 方法描述: 设备信息查询
     * @param map
     * @return
     * @throws
     *
     **/
    List<Device> queryDeviceInfo(Map<String, Object> map);

    /**
     * t_device
     * 方法描述: 设备信息查询,记录总数
     * @param map
     * @return
     * @throws
     *
     **/
    Integer count(Map<String, Object> map);
    /**
     * 
     * 方法描述：通过监测站的编码,查询该监测站下的孢子捕捉仪和测报灯的编码
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月18日 下午1:55:09
     */
    List<String> listDevCodeByMsCode(@Param("ms_code")String ms_code);
}