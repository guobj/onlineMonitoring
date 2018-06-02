package com.nz.onlineMonitoring.stationInfo.service;

import java.util.List;
import java.util.Map;

import com.nz.onlineMonitoring.stationInfo.model.Station;

public interface StationService {
    /**
     * t_station
     * 方法描述:查询全部数据
     * @param  
     * @return 
     * @throws 
     * @date 2018年6月2日 上午9:54:59
     * 
     **/
   List<Station> listStation(Map<String , Object> map);
   /**
    * 
    * 方法描述：根据查询条件查询数据的条数
    * @return
    * @param
    * @throws
    * @date 2018年6月2日 上午9:57:22
    */
   Integer countStation(Map<String , Object> map);
   
   /**
    * 
    * 方法描述：查询单条数据
    * @param id
    * @return
    * @date 2018年6月2日 下午6:56:43
    */
   Station load(Integer id);
   
}
