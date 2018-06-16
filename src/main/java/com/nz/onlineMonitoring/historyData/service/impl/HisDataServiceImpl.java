package com.nz.onlineMonitoring.historyData.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.historyData.mapper.HisDataMapper;
import com.nz.onlineMonitoring.historyData.mapper.HisMeteorologicalMapper;
import com.nz.onlineMonitoring.historyData.model.HisData;
import com.nz.onlineMonitoring.historyData.model.HisMeteorological;
import com.nz.onlineMonitoring.historyData.service.HisDataService;
@Service
public class HisDataServiceImpl implements HisDataService{
    @Autowired
    private HisDataMapper hisDataMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HisMeteorologicalMapper hisMeteorologicalMapper;
    
    
    @Override
    public List<HisData> listHisData(Map<String, Object> map) {
      //获取前台查询的值
        HisData hisData = (HisData) map.get("hisData");
        //建立一个HisMeteorological类，用于mapper.xml里的查数据时用
        HisMeteorological hisMeteorological = new HisMeteorological();
        //不管ms_code,dev_status用没有值，赋给HisMeteorological就行，他们不决定查询哪个表
        hisMeteorological.setMs_code(hisData.getMs_code());
        hisMeteorological.setDev_status(hisData.getDev_status());
        hisMeteorological.setData_time_begin(hisData.getData_time_begin());
        hisMeteorological.setData_time_end(hisData.getData_time_end());
        //建立两个list，用于装两个表中返回的数据，不一定都会装东西，可能只查一个表
        List<HisMeteorological> hisMeteorologicalList = null;
        List<HisData> hisList = null;
        //两个表中的数量
        Integer countData = 0;
        Integer countHisMeteorological = 0;
        //获取设备监测对象和设备类型
        String dev_object = hisData.getDevice_object();
        String dev_type = hisData.getDevice_type();
        //如果这两个是空的话，就是两个表一块查
        if (dev_object != null && dev_object != "" && dev_type != null && dev_type!= "") {
            //如果两个字段不相等，例如对象是害虫监测，而设备是气象设备，那就直接返回空，若果相等并且等于5，说明查气象表，否则数据表
            if (!dev_object.equals(String.valueOf(dev_type.charAt(0)))) {
                throw new RuntimeException("暂无数据");
            }else if (dev_object.equals("5")) {
                hisMeteorological.setDevice_type(dev_type);
                map.put("hisMeteorological", hisMeteorological);
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
                countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            }else {
                hisList = hisDataMapper.listHisData(map);
                countData = hisDataMapper.countHisData(map);
            }
        }else if (dev_object != null && dev_object != "") {
            //两个字段中如果只有一个字段有数据，判断是否为5，是则气象表，否则数据表
            if (dev_object.equals("5")) {
                hisMeteorological.setDevice_object(dev_object);
                map.put("hisMeteorological", hisMeteorological);
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
                countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            }else {
                hisList = hisDataMapper.listHisData(map);
                countData = hisDataMapper.countHisData(map);
            }
        }else if (dev_type != null && dev_type != "") {
            if (dev_type.charAt(0) == '5') {
                hisMeteorological.setDevice_type(dev_type);
                map.put("hisMeteorological", hisMeteorological);
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
                countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            }else {
                hisList = hisDataMapper.listHisData(map);
                countData = hisDataMapper.countHisData(map);
            }
        }else {
            map.put("hisMeteorological", hisMeteorological);
            hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
            hisList = hisDataMapper.listHisData(map);
            countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            countData = hisDataMapper.countHisData(map);
        }
        map.put("count", countData+countHisMeteorological);
        //根据各个list有无数据，进行设备编码解析，有就解析，没有算了
        if ((hisList == null || hisList.size() <= 0) && (hisMeteorologicalList == null || hisMeteorologicalList.size() <= 0)) {
            throw new RuntimeException("暂无数据");
        }else {
            if (hisList != null && hisList.size() > 0) { 
                for (HisData rd : hisList) {
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
            if (hisMeteorologicalList != null && hisMeteorologicalList.size() > 0) {
                for (HisMeteorological rm : hisMeteorologicalList) {
                    if (rm.getDev_code()!= null && rm.getDev_code() != "") {
                        Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rm.getDev_code().substring(3, 4)));
                        Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rm.getDev_code().substring(3, 6)));
                        if (devObject == null || devType == null) {
                            throw new RuntimeException("暂无数据");
                        }else {
                            rm.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+rm.getDev_code().substring(6, 8)+"个");
                        }
                    }
                }
            }
        }
        map.put("listHisMeteorological", hisMeteorologicalList);
        return hisList;
    }

}
