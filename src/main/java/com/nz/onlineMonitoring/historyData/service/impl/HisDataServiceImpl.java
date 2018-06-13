package com.nz.onlineMonitoring.historyData.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.historyData.mapper.HisDataMapper;
import com.nz.onlineMonitoring.historyData.model.HisData;
import com.nz.onlineMonitoring.historyData.service.HisDataService;
@Service
public class HisDataServiceImpl implements HisDataService{
    @Autowired
    private HisDataMapper hisDataMapper;
    @Autowired
    private DictMapper dictMapper;
    
    @Override
    public List<HisData> listHisData(Map<String, Object> map) {
        Integer count = hisDataMapper.countHisData(map);
        map.put("count", count);
        List<HisData> hisList = hisDataMapper.listHisData(map);
        if (hisList == null || hisList.size() <= 0) {
            throw new RuntimeException("暂无数据");
        }else {
            for (HisData hd : hisList) {
                if (hd.getDev_code()!= null && hd.getDev_code() != "") {
                    Dict devObject = dictMapper.loadByDevType(Integer.parseInt(hd.getDev_code().substring(3, 4)));
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(hd.getDev_code().substring(3, 6)));
                    if (devObject == null || devType == null) {
                        throw new RuntimeException("暂无数据");
                    }else {
                        hd.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+hd.getDev_code().substring(6, 8)+"个");
                    }
                }
            }
        }
        return hisList;
    }

}
