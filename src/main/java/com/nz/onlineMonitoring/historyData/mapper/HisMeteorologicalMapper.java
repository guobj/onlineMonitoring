/**
 * HisMeteorologicalMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-13 19:52:05
 **/
package com.nz.onlineMonitoring.historyData.mapper;

import java.util.List;
import java.util.Map;

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
    /**
     * 
     * 方法描述：查询历史时数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月16日 下午2:36:02
     */
    List<HisMeteorological> listHisMeteorological(Map<String, Object> map);
    /**
     * 
     * 方法描述：查询数据数量
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月16日 上午11:14:52
     */
    Integer countHisMeteorological(Map<String, Object> map);
}