package com.nz.onlineMonitoring.historyData.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.historyData.mapper.HisMeteorologicalMapper;
import com.nz.onlineMonitoring.historyData.model.HisMeteorological;
import com.nz.onlineMonitoring.historyData.service.HisMeteorologicalService;
@Service
public class HisMeteorologicalServiceImpl implements HisMeteorologicalService {
    
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HisMeteorologicalMapper hisMeteorologicalMapper;
    
    @Override
    public HisMeteorological load(Integer id) {
        HisMeteorological hisMeteorological = hisMeteorologicalMapper.load(id);
        if (hisMeteorological == null) {
            throw new RuntimeException("暂无数据");
        }
        //解析检测设备编码
        if (hisMeteorological.getDev_code()!= null && hisMeteorological.getDev_code() != "") {
            Dict devObject = dictMapper.loadByDevType(Integer.parseInt(hisMeteorological.getDev_code().substring(3, 4)));
            Dict devType = dictMapper.loadByDevType1(Integer.parseInt(hisMeteorological.getDev_code().substring(3, 6)));
            if (devObject == null || devType == null) {
                throw new RuntimeException("暂无数据");
            }else {
                hisMeteorological.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+hisMeteorological.getDev_code().substring(6, 8)+"个");
            }
        }
        //解析监测站编码
        String ms_code = hisMeteorological.getMs_code();
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        
        String code2 = ms_code.substring(6, 8);
        if (code02.equals("00")) {
            Dict city = dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                hisMeteorological.setMs_code_value(name1 + "第" + code2 +"个");
            }else {
                hisMeteorological.setMs_code_value("无法解析编码");
            }
            
        }else {
            String code03 = ms_code.substring(0, 4);
            code03 += "00";
            Dict city1 = dictMapper.loadCity(Integer.parseInt(code03));
            Dict city2 = dictMapper.loadCity(Integer.parseInt(code01));
            if (city1 != null && city2 != null) {
                String name2= city1.getData_name();
                String name1= city2.getData_name();
                hisMeteorological.setMs_code_value(name2+name1 + "第" + code2 +"个");
            }else {
                hisMeteorological.setMs_code_value("无法解析编码");
            }
        }
        return hisMeteorological;
    }

}
