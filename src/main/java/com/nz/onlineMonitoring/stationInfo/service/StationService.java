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
     * @author ssh
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
    * @author ssh
    * @date 2018年6月2日 上午9:57:22
    */
   Integer countStation(Map<String , Object> map);
   
   /**
    * 
    * 方法描述：查询单条数据,并解析监测站和设备
    * @param id
    * @return
    * @author ssh
    * @date 2018年6月2日 下午6:56:43
    */
   Station load(Integer id);
   /**
    * 
    * 方法描述：查询单条数据,只解析设备
    * @param id
    * @return
    * @author ssh
    * @date 2018年6月2日 下午6:56:43
    */
   Station getStation(Map<String , Object> map,Integer id);
   /**
    * 
    * 方法描述：根据id，删除一条监测站
    * @param id
    * @return
    * @author ssh
    * @date 2018年6月2日 下午8:07:08
    */
   Integer delete(Integer id);
   /**
    * 根据id，修改监测站的信息
    * 方法描述：
    * @param station
    * @return
    * @author ssh
    * @date 2018年6月2日 下午8:50:50
    */
   Integer update(Station station);
   /**
    * 
    * 方法描述：修改编码时，判断是否有重复的编码
    * @param ms_code
    * @return
    * @author ssh
    * @date 2018年6月3日 上午10:17:15
    */
   Integer existMsCode(String ms_code);
   /**
    * t_station
    * 方法描述:
    * @param  
    * @return 
    * @throws 
    * @date 2018-06-11 11:08:33
    * 
    **/
   int add(Station station);
}
