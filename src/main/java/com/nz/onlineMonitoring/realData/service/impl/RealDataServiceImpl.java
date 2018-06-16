package com.nz.onlineMonitoring.realData.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nz.onlineMonitoring.realData.mapper.RealMeteorologicalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.realData.mapper.RealDataMapper;
import com.nz.onlineMonitoring.realData.model.RealData;
import com.nz.onlineMonitoring.realData.service.RealDataService;
@Service
public class RealDataServiceImpl implements RealDataService {
    
    @Autowired
    private RealDataMapper realDataMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private RealMeteorologicalMapper realMeteorologicalMapper;
    /**
     * 
     * 方法描述：查询实时数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    @Override
    public List<RealData> listReal(Map<String, Object> map) {
        Integer countData = realDataMapper.countReal(map);
        Integer countMeteorological = realDataMapper.countReal(map);
        map.put("count", countData+countMeteorological);
        List<RealData> realList = realDataMapper.listReal(map);
        if (realList == null || realList.size() <= 0) {
            throw new RuntimeException("暂无数据");
        }else {
            for (RealData rd : realList) {
                if (rd.getDev_code()!= null && rd.getDev_code() != "") {
                    Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rd.getDev_code().substring(3, 4)));
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rd.getDev_code().substring(3, 6)));
                    if (devObject == null || devType == null) {
                        throw new RuntimeException("暂无数据");
                    }else {
                        rd.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+rd.getDev_code().substring(6, 8)+"个");
                    }
                }
            }
        }
        return realList;
    }

    /**
     * 方法描述：通过ms_code和dev_code查询实时数据
     *
     * @param map
     * @return
     * @author guobj
     * @date 2018年6月16日
     */
    @Override
    public List loadByMsCodeAndDevCode(Map<String, Object> map) {
        String dev_type = map.get("dev_code").toString().substring(3, 6);
        List list = new ArrayList<>();
        if(dev_type.equals("501")){
            list = realMeteorologicalMapper.loadByMsCodeAndDevCode(map);
        } else if (dev_type.equals("202")) {
            list = realDataMapper.loadByMsCodeAndDevCode(map);
        }else {
            RealData realData = new RealData();
            realData.setMs_code(map.get("ms_code").toString());
            //设备编码解析开始
            char c = '0';
            Integer dev_type1 = 0;
            if(map.get("dev_code").toString() != null && map.get("dev_code").toString() != ""){
                c = map.get("dev_code").toString().charAt(3);
                String str = map.get("dev_code").toString().substring(3, 6);
                dev_type1 = Integer.parseInt(str);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
            sb.append(dictMapper.loadByDevType1(dev_type1).getData_name());
            //设备编码解析结束
            realData.setDev_code_value(sb.toString());
            realData.setDev_code(map.get("dev_code").toString());
            list.add(realData);
        }
//         = realDataMapper.loadByMsCodeAndDevCode(map);
        if(list == null || list.size() <= 0){
            throw new RuntimeException("暂无数据");
        }
        map.put("listRealData", list);
        return list;
    }
}
