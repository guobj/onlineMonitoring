package com.nz.onlineMonitoring.realData.service;

import java.util.List;
import java.util.Map;

public interface RealDataService {
    /**
     * 
     * 方法描述：查询实时数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    List listReal(Map<String, Object> map) throws Exception;

    /**
     * 方法描述：通过ms_code和dev_code查询实时数据
     *
     * @param map
     * @return
     * @author guobj
     * @date 2018年6月16日
     */
    void loadByMsCodeAndDevCode(Map<String, Object> map);
    /**
     * 
     * 方法描述：通过监测站的编码，查询监测站下所有设备的数据，包括图片信息，数据信息，气象信息
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月18日 下午2:00:12
     */
    List listDataByMsCode(String ms_code);

    
}
