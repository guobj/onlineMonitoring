/**
 * RealMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.realData.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nz.onlineMonitoring.realData.model.RealData;

public interface RealDataMapper {
    /**
     * t_real
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int delete(Integer id);

    /**
     * t_real
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(RealData real);

    /**
     * t_real
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    RealData load(Integer id);

    /**
     * t_real
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int update(RealData realData);
    /**
     * 
     * 方法描述：查询实时数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    List<RealData> listReal(Map<String, Object> map);
    /**
     * 
     * 方法描述：查询数据数量
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月5日 上午11:14:52
     */
    Integer countReal(Map<String, Object> map);

    /**
     * 方法描述：通过ms_code和dev_code查询实时数据
     *
     * @param map
     * @return
     * @author guobj
     * @date 2018年6月16日
     */
    List<RealData> loadByMsCodeAndDevCode(Map<String, Object> map);
    /**
     * 
     * 方法描述：通过ms_code和dev_code查询实时数据的数量
     * @param map
     * @return
     * @author ssh
     * @date 2018年8月8日 下午1:15:40
     */
    Integer countLoadByMsCodeAndDevCode(Map<String, Object> map);
    /**
     * 
     * 方法描述：查询一个监测站下所有设备的数据
     * @param ms_code
     * @return
     * @author ssh
     * @date 2018年6月18日 下午3:37:16
     */
    List<RealData> listRealByMsCode(@Param("ms_code")String ms_code);
    
}