package com.nz.onlineMonitoring.realData.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.realData.mapper.RealDataMapper;
import com.nz.onlineMonitoring.realData.mapper.RealMeteorologicalMapper;
import com.nz.onlineMonitoring.realData.model.RealData;
import com.nz.onlineMonitoring.realData.model.RealMeteorological;
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
    public List listReal(Map<String, Object> map) {
        //用于气象表字段中查询的数量，会根据数据表查出的数量，与之互补
        map.put("num", 10);
        //返回前台的list，泛型
        List list = new ArrayList<>();
        //获取前台查询的值
        RealData realData = (RealData) map.get("realData");
        //建立一个RealMeteorological类，用于mapper.xml里的查数据时用
        RealMeteorological realMeteorological = new RealMeteorological();
        //不管ms_code,dev_status用没有值，赋给RealMeteorological就行，他们不决定查询哪个表
        realMeteorological.setMs_code(realData.getMs_code());
        realMeteorological.setDev_status(realData.getDev_status());
        //建立两个list，用于装两个表中返回的数据，不一定都会装东西，可能只查一个表
        List<RealMeteorological> realMeteorologicalList = null;
        List<RealData> realList = null;
        //两个表中的数量
        Integer countData = 0;
        Integer countMeteorological = 0;
        //获取设备监测对象和设备类型
        String dev_object = realData.getDevice_object();
        String dev_type = realData.getDevice_type();
        //如果这两个是空的话，就是两个表一块查
        if (dev_object != null && dev_object != "" && dev_type != null && dev_type!= "") {
            //如果两个字段不相等，例如对象是害虫监测，而设备是气象设备，那就直接返回空，若果相等并且等于5，说明查气象表，否则数据表
            if (!dev_object.equals(String.valueOf(dev_type.charAt(0)))) {
                throw new RuntimeException("暂无数据");
            }else if (dev_object.equals("5")) {
                realMeteorological.setDevice_type(dev_type);
                map.put("realMeteorological", realMeteorological);
                realMeteorologicalList = realMeteorologicalMapper.listMeteorological(map);
                countMeteorological = realMeteorologicalMapper.countMeteorological(map);
            }else {
                realList = realDataMapper.listReal(map);
                countData = realDataMapper.countReal(map);
            }
        }else if (dev_object != null && dev_object != "") {
            //两个字段中如果只有一个字段有数据，判断是否为5，是则气象表，否则数据表
            if (dev_object.equals("5")) {
                realMeteorological.setDevice_object(dev_object);
                map.put("realMeteorological", realMeteorological);
                realMeteorologicalList = realMeteorologicalMapper.listMeteorological(map);
                countMeteorological = realMeteorologicalMapper.countMeteorological(map);
            }else {
                realList = realDataMapper.listReal(map);
                countData = realDataMapper.countReal(map);
            }
        }else if (dev_type != null && dev_type != "") {
            if (dev_type.charAt(0) == '5') {
                realMeteorological.setDevice_type(dev_type);
                map.put("realMeteorological", realMeteorological);
                realMeteorologicalList = realMeteorologicalMapper.listMeteorological(map);
                countMeteorological = realMeteorologicalMapper.countMeteorological(map);
            }else {
                realList = realDataMapper.listReal(map);
                countData = realDataMapper.countReal(map);
            }
        }else {
            realList = realDataMapper.listReal(map);
            countData = realDataMapper.countReal(map);
            //获取此次数据表中查询出来的数量，如果小于10，就从气象表中拿数据
            if (realList.size() < 10) {
                map.put("realMeteorological", realMeteorological);
                //一开始，record是0，countData为6大于它，此时从气象表的第0条记录开始拿数据，然后下一页record是10，需要从第4条记录拿数据，因为前4条已经在上一页显示了
                map.put("record", countData > Integer.parseInt(map.get("record").toString()) ? Integer.parseInt(map.get("record").toString()) : Integer.parseInt(map.get("record").toString())-countData);
                //拿取数据的数量，与数据表在一页上的数据互补
                map.put("num", 10-realList.size());
                realMeteorologicalList = realMeteorologicalMapper.listMeteorological(map);
            }
            countMeteorological = realMeteorologicalMapper.countMeteorological(map);
        }
        map.put("count", countData+countMeteorological);
        //根据各个list有无数据，进行设备编码解析，有就解析，没有算了
        if ((realList == null || realList.size() <= 0) && (realMeteorologicalList == null || realMeteorologicalList.size() <= 0)) {
            throw new RuntimeException("暂无数据");
        }else {
            if (realList != null && realList.size() > 0) { 
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
                list.addAll(realList);
            }
            if (realMeteorologicalList != null && realMeteorologicalList.size() > 0) {
                for (RealMeteorological rm : realMeteorologicalList) {
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
                list.addAll(realMeteorologicalList);
            }
        }
        return list;
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
    public void loadByMsCodeAndDevCode(Map<String, Object> map) {
        List<RealData> listRealData = new ArrayList<>();
        List<RealMeteorological> listRealMeteorological = new ArrayList<>();
        String dev_code = map.get("dev_code").toString();
        String ms_code = map.get("ms_code").toString();
        String dev_code_object = dev_code.substring(3,4);
        String dev_code_type = dev_code.substring(3,6);
        //如果第4位是5，则是查气象表，查出来一条数据，然后编码解析
        if(dev_code.charAt(3) == '5'){
            listRealMeteorological = realMeteorologicalMapper.loadByMsCodeAndDevCode(map);
            if (listRealMeteorological != null && listRealMeteorological.size() == 1) {
                Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
                Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
                if (devObject == null || devType == null) {
                    throw new RuntimeException("暂无数据");
                }else {
                    listRealMeteorological.get(0).setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+listRealMeteorological.get(0).getDev_code().substring(6, 8)+"个");
                }
            }
        } else if (dev_code_type.equals("101") || dev_code_type.equals("201")) {
            RealData rd = new RealData();
            rd.setDev_code(dev_code);
            rd.setMs_code(ms_code);
            listRealData.add(rd);
            Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
            Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
            if (devObject == null || devType == null) {
                throw new RuntimeException("暂无数据");
            }else {
                listRealData.get(0).setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+listRealData.get(0).getDev_code().substring(6, 8)+"个");
            }
        }else {
            listRealData = realDataMapper.loadByMsCodeAndDevCode(map);
            if (listRealData != null && listRealData.size() == 1) {
                Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
                Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
                if (devObject == null || devType == null) {
                    throw new RuntimeException("暂无数据");
                }else {
                    listRealData.get(0).setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+listRealData.get(0).getDev_code().substring(6, 8)+"个");
                }
            }
        }
        map.put("listRealMeteorological", listRealMeteorological);
        map.put("listRealData", listRealData);
    }
}
