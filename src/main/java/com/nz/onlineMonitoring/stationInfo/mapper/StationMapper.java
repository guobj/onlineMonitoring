/**
 * StationMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.stationInfo.mapper;

import java.util.List;
import java.util.Map;

import com.nz.onlineMonitoring.stationInfo.model.Station;

public interface StationMapper {
    /**
     * t_station
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int delete(Integer id);

    /**
     * t_station
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(Station station);

    /**
     * t_station
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    Station load(Integer id);

    /**
     * t_station
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int update(Station station);
    /**
     * t_station
     * 方法描述:查询全部数据
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
   List<Station> listStation(Map<String , Object> map);
   /**
    * 方法描述:根据查询条件查询数据的条数
    * @return
    * @param
    * @throws
    * @date 2018年6月2日 上午9:54:59
    */
   int countStation(Map<String , Object> map);
}