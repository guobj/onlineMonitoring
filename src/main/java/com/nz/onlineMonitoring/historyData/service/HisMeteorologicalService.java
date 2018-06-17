package com.nz.onlineMonitoring.historyData.service;

import com.nz.onlineMonitoring.historyData.model.HisMeteorological;

public interface HisMeteorologicalService {

    /**
     * 
     * 方法描述：根据id，查询气象表中的一条数据
     * @param id
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月17日 下午7:32:09
     */
    HisMeteorological load (Integer id);
}
