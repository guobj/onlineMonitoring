/**
 * ManageMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.stationManage.mapper;

import com.nz.onlineMonitoring.stationManage.model.Manage;

import java.util.List;
import java.util.Map;

public interface ManageMapper {
    /**
     * t_manage
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int delete(Integer id);

    /**
     * t_manage
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(Manage manage);

    /**
     * t_manage
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    Manage load(Integer id);

    /**
     * t_manage
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int update(Manage manage);

    /**
     * 通过监测站名称、监测站编码、监测站类型、资金来源、网关类型综合查询（支持模糊查询）
     * @author guobj
     * @param
     * @return
     */
    List<Map<String, Object>> queryStationInfo(Map<String, Object> map);
}