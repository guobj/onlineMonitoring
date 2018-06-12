package com.nz.onlineMonitoring.realData.service.impl;

import java.util.List;
import java.util.Map;

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
    private RealDataMapper RealDataMapper;
    @Autowired
    private DictMapper dictMapper;
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
        Integer count = RealDataMapper.countReal(map);
        map.put("count", count);
        List<RealData> realList = RealDataMapper.listReal(map);
        List<Dict> listData = dictMapper.listMsDev();
        if (listData == null || listData.size() < 0) {
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

}
