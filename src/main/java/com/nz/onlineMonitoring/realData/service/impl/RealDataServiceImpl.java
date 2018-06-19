package com.nz.onlineMonitoring.realData.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.deviceInfo.mapper.DeviceMapper;
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
    @Autowired
    private DeviceMapper deviceMapper;
    
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
            map.put("realMeteorological", realMeteorological);
            if (realList.size() < 10) {
                //一开始，record是0，countData为6大于它，此时从气象表的第0条记录开始拿数据，然后下一页record是10，需要从第4条记录拿数据，因为前4条已经在上一页显示了
                map.put("record", countData >= Integer.parseInt(map.get("record").toString()) ? 0 : Integer.parseInt(map.get("record").toString())-countData);
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
        if(dev_code_object.equals("5")){
            listRealMeteorological = realMeteorologicalMapper.loadByMsCodeAndDevCode(map);
            if (listRealMeteorological != null && listRealMeteorological.size() > 0) {
                String dev_code_value = "";
                Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
                Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
                if (devObject == null || devType == null) {
                    dev_code_value = "编码无法解析！";
                }else {
                    dev_code_value = devObject.getData_name()+devType.getData_name()+"第"+dev_code.substring(6, 8)+"个";
                }
                for (RealMeteorological realMeteorological : listRealMeteorological) {
                    realMeteorological.setDev_code_value(dev_code_value);
                }
            }else {
                throw new RuntimeException("暂无数据");
            }
        } else if (dev_code_type.equals("101") || dev_code_type.equals("201")) {
            RealData rd = new RealData();
            rd.setDev_code(dev_code);
            rd.setMs_code(ms_code);
            Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
            Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
            if (devObject == null || devType == null) {
                rd.setDev_code_value("编码无法解析！");
            }else {
                rd.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+dev_code.substring(6, 8)+"个");
            }
            
            File file = new File("E:\\gw_pictuces\\"+ms_code+"\\"+dev_code);
            if (file.exists()) {
                File[] files = file.listFiles();
                if (files.length != 0) {
                    //最新的时间
                    String maxTime = "";
                    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    //孢子仪和测报灯图片的命名不同，分开解析
                    if (dev_code.startsWith("dev101")) {
                        for (File file2 : files) {
                            if (!file2.isDirectory()) {
                                String t = file2.getName();
                                int index = t.lastIndexOf(".");
                                maxTime = maxTime.compareTo(t.substring(index-16, index-2)) < 0 ? t.substring(index-16, index-2) : maxTime;
                            }
                        }
                        StringBuffer s = new StringBuffer(maxTime);
                        s.insert(12, ":");
                        s.insert(10, ":");
                        s.insert(8, " ");
                        s.insert(6, "-");
                        s.insert(4, "-");
                        maxTime = s.toString();
                    }else {
                        for (File file2 : files) {
                            if (!file2.isDirectory()) {
                                String t = file2.getName();
                                int index = t.lastIndexOf(".");
                                maxTime = maxTime.compareTo(t.substring(index-19, index-2)) < 0 ? t.substring(index-19, index-2) : maxTime;
                            }
                        }
                        StringBuffer s = new StringBuffer(maxTime);
                        s.replace(14, 15, ":");
                        s.replace(11, 12, ":");
                        s.replace(8, 9, " ");
                        s.insert(0, "20");
                        maxTime = s.toString();
                    }
                    try {
                        rd.setData_time(formatter.parse(maxTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    throw new RuntimeException("暂无数据");
                }
            }else {
                throw new RuntimeException("暂无数据");
            }
            listRealData.add(rd);
        }else {
            listRealData = realDataMapper.loadByMsCodeAndDevCode(map);
            if (listRealData != null && listRealData.size() > 0) {
                String dev_code_value = "";
                Dict devObject = dictMapper.loadByDevType(Integer.parseInt(dev_code_object));
                Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code_type));
                if (devObject == null || devType == null) {
                    dev_code_value = "编码无法解析！";
                }else {
                    dev_code_value = devObject.getData_name()+devType.getData_name()+"第"+dev_code.substring(6, 8)+"个";
                }
                for (RealData realData : listRealData) {
                    realData.setDev_code_value(dev_code_value);
                }
            }else {
                throw new RuntimeException("暂无数据");
            }
        }
        List list = new ArrayList<>();
        list.addAll(listRealMeteorological);
        list.addAll(listRealData);
        map.put("listRealData", list);
    }


    /**
     * 
     * 方法描述：通过监测站的编码，查询监测站下所有设备的数据，包括图片信息，数据信息，气象信息
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月18日 下午2:00:12
     */
    @Override
    public List listDataByMsCode(String ms_code) { 
        List listAll = new ArrayList<>();
        //拿出监测站下所有的孢子和测报灯的编码
        List<String> listDevCode = deviceMapper.listDevCodeByMsCode(ms_code);
        //拿图片数据
        //循环这些编码，并分别处理
        for (String dev_code : listDevCode) {
            File file = new File("E:\\gw_pictuces\\"+ms_code+"\\"+dev_code);
            if (file.exists()) {
                File[] files = file.listFiles();
                //判断里面是否有图片
                if (files.length != 0) {
                    //最新的时间
                    String maxTime = "";
                    //用于装最新的10张图片名，用，隔开，存在data_value中
                    StringBuffer sb = new StringBuffer();
                    RealData rd = new RealData();
                    //用于装文件下所有图片的名，然后排序，取前10
                    List<String> list = new ArrayList<>();
                    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    //记录文件夹下图片的数量，如果图片不到10个，就装sum个
                    int sum = 0;
                    //孢子仪和测报灯图片的命名不同，分开解析
                    if (dev_code.startsWith("dev101")) {
                        for (File file2 : files) {
                            if (!file2.isDirectory()) {
                                String t = file2.getName();
                                list.add(t);
                                int index = t.lastIndexOf(".");
                                sum++;
                                if (index >= 16) {
                                    maxTime = maxTime.compareTo(t.substring(index-16, index-2)) < 0 ? t.substring(index-16, index-2) : maxTime;
                                } 
                                
                            }
                        }
                        StringBuffer s = new StringBuffer(maxTime);
                        s.insert(12, ":");
                        s.insert(10, ":");
                        s.insert(8, " ");
                        s.insert(6, "-");
                        s.insert(4, "-");
                        maxTime = s.toString();
                    }else {
                        for (File file2 : files) {
                            if (!file2.isDirectory()) {
                                String t = file2.getName();
                                list.add(t);
                                int index = t.lastIndexOf(".");
                                sum++;
                                if (index >= 19) {
                                    maxTime = maxTime.compareTo(t.substring(index-19, index-2)) < 0 ? t.substring(index-19, index-2) : maxTime;
                                }
                            }
                        }
                        StringBuffer s = new StringBuffer(maxTime);
                        s.replace(14, 15, ":");
                        s.replace(11, 12, ":");
                        s.replace(8, 9, " ");
                        s.insert(0, "20");
                        maxTime = s.toString();
                    }
                    //所有图片的名，排序，取前10
                    Collections.sort(list, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return -1;
                        }
                    });
                  //记录文件夹下图片的数量，如果图片不到10个，就装sum个
                    sum = sum>=10?10:sum;
                    for (int i = 0; i < sum; i++) {
                        sb.append(list.get(i)+",");
                    }
                    //装入RealData中，用于前台遍历
                    try {
                        rd.setData_time(formatter.parse(maxTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    rd.setMs_code(ms_code);
                    rd.setDev_code(dev_code);
                    rd.setData_value(sb.toString());
                    //解析设备编码，只解析类型
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(dev_code.substring(3, 6)));
                    if (devType == null) {
                        rd.setDev_code_value("无此类型设备");
                    }else {
                        rd.setDev_code_value(devType.getData_name());
                    }
                    listAll.add(rd);
                }
            }
        }
        //此数据和气象表拿数据
        List<RealMeteorological> realMeteorologicalList = realMeteorologicalMapper.listMeteorologicalByMsCode(ms_code);
        List<RealData> realList = realDataMapper.listRealByMsCode(ms_code);
        if (realList != null && realList.size() > 0) { 
            for (RealData rd : realList) {
                if (rd.getDev_code()!= null && rd.getDev_code() != "") {
                    Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rd.getDev_code().substring(3, 4)));
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rd.getDev_code().substring(3, 6)));
                    if (devObject == null || devType == null) {
                        rd.setDev_code_value("无此类型设备");
                    }else {
                        rd.setDev_code_value(devType.getData_name());
                    }
                }
            }
            listAll.addAll(realList);
        }
        if (realMeteorologicalList != null && realMeteorologicalList.size() > 0) {
            for (RealMeteorological rm : realMeteorologicalList) {
                if (rm.getDev_code()!= null && rm.getDev_code() != "") {
                    Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rm.getDev_code().substring(3, 4)));
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rm.getDev_code().substring(3, 6)));
                    if (devObject == null || devType == null) {
                        rm.setDev_code_value("无此类型设备");
                    }else {
                        rm.setDev_code_value(devType.getData_name());
                    }
                }
            }
            listAll.addAll(realMeteorologicalList);
        }
        if (listAll == null || listAll.size() <= 0) {
            throw new RuntimeException("暂无数据");
        }
        return listAll;
    }
}
