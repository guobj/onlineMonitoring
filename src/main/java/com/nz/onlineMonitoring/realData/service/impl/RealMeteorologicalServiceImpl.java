package com.nz.onlineMonitoring.realData.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.realData.mapper.RealMeteorologicalMapper;
import com.nz.onlineMonitoring.realData.model.RealMeteorological;
import com.nz.onlineMonitoring.realData.service.RealMeteorologicalService;
@Service
public class RealMeteorologicalServiceImpl implements RealMeteorologicalService {
    
    @Autowired
    private RealMeteorologicalMapper realMeteorologicalMapper;
    @Autowired
    private DictMapper dictMapper;
    /**
     * 
     * 方法描述：查询气象数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    @Override
    public List<RealMeteorological> listMeteorological(Map<String, Object> map) {
        Integer count = realMeteorologicalMapper.countMeteorological(map);
        map.put("count", count);
        return realMeteorologicalMapper.listMeteorological(map);
    }
    /**
     * 
     * 方法描述：根据id，查询气象表中的一条数据
     * @param id
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月17日 下午7:32:09
     */
    @Override
    public RealMeteorological load(Integer id) {
        RealMeteorological realMeteorological = realMeteorologicalMapper.load(id);
        if (realMeteorological == null) {
            throw new RuntimeException("暂无数据");
        }
        //解析检测设备编码
        if (realMeteorological.getDev_code()!= null && realMeteorological.getDev_code() != "") {
            Dict devObject = dictMapper.loadByDevType(Integer.parseInt(realMeteorological.getDev_code().substring(3, 4)));
            Dict devType = dictMapper.loadByDevType1(Integer.parseInt(realMeteorological.getDev_code().substring(3, 6)));
            if (devObject == null || devType == null) {
                throw new RuntimeException("暂无数据");
            }else {
                realMeteorological.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+realMeteorological.getDev_code().substring(6, 8)+"个");
            }
        }
        //解析监测站编码
        String ms_code = realMeteorological.getMs_code();
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        
        String code2 = ms_code.substring(6, 8);
        if (code02.equals("00")) {
            Dict city = dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                realMeteorological.setMs_code_value(name1 + "第" + code2 +"个");
            }else {
                realMeteorological.setMs_code_value("无法解析编码");
            }
            
        }else {
            String code03 = ms_code.substring(0, 4);
            code03 += "00";
            Dict city1 = dictMapper.loadCity(Integer.parseInt(code03));
            Dict city2 = dictMapper.loadCity(Integer.parseInt(code01));
            if (city1 != null && city2 != null) {
                String name2= city1.getData_name();
                String name1= city2.getData_name();
                realMeteorological.setMs_code_value(name2+name1 + "第" + code2 +"个");
            }else {
                realMeteorological.setMs_code_value("无法解析编码");
            }
        }
        return realMeteorological;
    }

}
