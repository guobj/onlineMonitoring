/**
 * DeviceMapper.java
 * 济南农智信息科技有限公司所有
 * Create By guobj
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.deviceInfo.mapper;

import com.nz.onlineMonitoring.deviceInfo.model.Device;

import java.util.List;
import java.util.Map;

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
}