package com.nz.onlineMonitoring.realData.service;

import java.util.List;
import java.util.Map;

import com.nz.onlineMonitoring.realData.model.Meteorological;

public interface MeteorologicalService {
    /**
     * 
     * 方法描述：查询气象数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    List<Meteorological> listMeteorological(Map<String, Object> map);
}
