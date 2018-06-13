/**
 * HisMeteorologicalMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-13 19:52:05
 **/
package com.nz.onlineMonitoring.historyData.mapper;

import com.nz.onlineMonitoring.historyData.model.HisMeteorological;

public interface HisMeteorologicalMapper {
    /**
     * t_his_meteorological
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @author 胖先生
     * @date 2018-06-13 19:52:05
     * 
     **/
    int delete(Integer id);

    /**
     * t_his_meteorological
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @author 胖先生
     * @date 2018-06-13 19:52:05
     * 
     **/
    int add(HisMeteorological hisMeteorological);

    /**
     * t_his_meteorological
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @author 胖先生
     * @date 2018-06-13 19:52:05
     * 
     **/
    HisMeteorological load(Integer id);

    /**
     * t_his_meteorological
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @author 胖先生
     * @date 2018-06-13 19:52:05
     * 
     **/
    int update(HisMeteorological hisMeteorological);
}