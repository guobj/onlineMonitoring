package com.nz.onlineMonitoring.historyData.service.impl;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
import com.nz.onlineMonitoring.utils.AnalyseCode;
@Service
public class HisDataServiceImpl implements HisDataService{
    @Autowired
    private HisDataMapper hisDataMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HisMeteorologicalMapper hisMeteorologicalMapper;
    
    /**
     * 
     * 方法描述：查询历史数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * 包括数据设备和气象设备
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月3日 下午2:36:02
     */
    @Override
    public List listHisData(Map<String, Object> map) throws Exception{
        String view = map.get("view").toString();
        //用于气象表字段中查询的数量，会根据数据表查出的数量，与之互补
        map.put("num", 15);
        //返回前台的list，泛型
        List list = new ArrayList<>();
        //获取前台查询的值
        HisData hisData = (HisData) map.get("hisdata");
        //建立一个HisMeteorological类，用于mapper.xml里的查数据时用
        HisMeteorological hisMeteorological = new HisMeteorological();
        //不管ms_code,dev_status有没有值，赋给HisMeteorological就行，他们不决定查询哪个表
        hisMeteorological.setMs_code(hisData.getMs_code());
        hisMeteorological.setDev_status(hisData.getDev_status());
        hisMeteorological.setData_time_begin(hisData.getData_time_begin());
        hisMeteorological.setData_time_end(hisData.getData_time_end());
        hisMeteorological.setDev_code(hisData.getDev_code());
        if (view.equals("chart")) {
            hisMeteorological.setWeather(hisData.getWeather());
        }
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
                map.put("hismeteorological", hisMeteorological);
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
                map.put("hismeteorological", hisMeteorological);
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
                countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            }else {
                hisList = hisDataMapper.listHisData(map);
                countData = hisDataMapper.countHisData(map);
            }
        }else if (dev_type != null && dev_type != "") {
            if (dev_type.charAt(0) == '5') {
                hisMeteorological.setDevice_type(dev_type);
                map.put("hismeteorological", hisMeteorological);
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
                countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
            }else {
                hisList = hisDataMapper.listHisData(map);
                countData = hisDataMapper.countHisData(map);
            }
        }else {
            hisList = hisDataMapper.listHisData(map);
            countData = hisDataMapper.countHisData(map);
            map.put("hismeteorological", hisMeteorological);
            if (hisList.size() < 15) {
                //一开始，record是0，countData为6大于它，此时从气象表的第0条记录开始拿数据，然后下一页record是15，需要从第9条记录拿数据，因为前4条已经在上一页显示了
                map.put("record", countData >= Integer.parseInt(map.get("record").toString()) ? 0 : Integer.parseInt(map.get("record").toString())-countData);
                //拿取数据的数量，与数据表在一页上的数据互补
                map.put("num", 15-hisList.size());
                hisMeteorologicalList = hisMeteorologicalMapper.listHisMeteorological(map);
            }
            countHisMeteorological = hisMeteorologicalMapper.countHisMeteorological(map);
        }
        map.put("count", countData+countHisMeteorological);
        //根据各个list有无数据，进行设备编码解析，有就解析，没有算了
        if ((hisList == null || hisList.size() <= 0) && (hisMeteorologicalList == null || hisMeteorologicalList.size() <= 0)) {
            throw new RuntimeException("暂无数据");
        }else {
            if (hisList != null && hisList.size() > 0) { 
                for (HisData rd : hisList) {
                    if (rd.getDev_code()!= null && rd.getDev_code() != "" && rd.getMs_code() != null && rd.getMs_code() != "") {
                        Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rd.getDev_code().substring(3, 4)));
                        Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rd.getDev_code().substring(3, 6)));
                        if (devObject == null || devType == null) {
                            throw new RuntimeException("暂无数据");
                        }else {
                            rd.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+rd.getDev_code().substring(6, 8)+"个");
                        }
                        AnalyseCode.msCode(rd, rd.getMs_code());
                    }
                    
                }
                if (view.equals("chart")) {
                    //用来传到前台，每天害虫的数量的平均值
                    List<HisData> hisList1 = new ArrayList<>();
                    //害虫条件的按天分段的总和
                    Map<String, Long> sumMap = new HashMap<>();
                    //害虫条件的按天分段的总个数
                    Map<String, Integer> numberMap = new HashMap<>();
                    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    Date date = null;
                    String dateStr = "";
                    for (HisData hd : hisList) {
                        //取时间的前10位
                        date = hd.getData_time();
                        dateStr = formatter.format(date).substring(0, 10);
                        if (sumMap.containsKey(dateStr)) {
                            sumMap.put(dateStr, sumMap.get(dateStr)+Integer.parseInt(hd.getData_value()));
                            numberMap.put(dateStr, numberMap.get(dateStr)+1);
                        }else {
                            sumMap.put(dateStr, Long.parseLong(hd.getData_value()));
                            numberMap.put(dateStr, 1);
                        }
                    }
                    for (String key : sumMap.keySet()) {
                        HisData hd = new HisData();
                        Long sum = sumMap.get(key);
                        Integer number = numberMap.get(key);
                        hd.setAvg(String.valueOf(sum/number));
                        hd.setDate_time(key);
                        hisList1.add(hd);
                    }
                    //因为加入到map中，顺序乱了，进行重新排序
                    Collections.sort(hisList1, new Comparator<HisData>() {
                        @Override
                        public int compare(HisData o1, HisData o2) {
                            return o1.getDate_time().compareTo(o2.getDate_time());
                        }
                    });
                    list.addAll(hisList1);
                }else {
                    list.addAll(hisList);
                }
            }
            if (hisMeteorologicalList != null && hisMeteorologicalList.size() > 0) {
                for (HisMeteorological rm : hisMeteorologicalList) {
                    if (rm.getDev_code()!= null && rm.getDev_code() != "" && rm.getMs_code() != null && rm.getMs_code() != "") {
                        Dict devObject = dictMapper.loadByDevType(Integer.parseInt(rm.getDev_code().substring(3, 4)));
                        Dict devType = dictMapper.loadByDevType1(Integer.parseInt(rm.getDev_code().substring(3, 6)));
                        if (devObject == null || devType == null) {
                            throw new RuntimeException("暂无数据");
                        }else {
                            rm.setDev_code_value(devObject.getData_name()+devType.getData_name()+"第"+rm.getDev_code().substring(6, 8)+"个");
                        }
                        AnalyseCode.msCode(rm, rm.getMs_code());
                    }
                }
                //如果view等于chart，表示折线展示
                String weather = hisData.getWeather();
                if (view.equals("chart") && weather != null && weather != "") {
                    //用来传到前台，装天气条件按小时的平均值和时间
                    List<HisMeteorological> hisMeteorologicalList1 = new ArrayList<>();
                    //气象条件的安小时分段的总和
                    Map<String, Double> sumMap = new HashMap<>();
                    //气象条件的安小时分段的总个数
                    Map<String, Integer> numberMap = new HashMap<>();
                    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    Date date = null;
                    String dateStr = "";
                    Method m = null;
                    for (HisMeteorological rm : hisMeteorologicalList) {
                        //取时间的前13位
                        date = rm.getData_time();
                        dateStr = formatter.format(date).substring(0, 13);
                        weather = weather.substring(0, 1).toUpperCase() + weather.substring(1);
                        //动态获取要查询的气象条件的get方法
                        m = HisMeteorological.class.getMethod("get"+weather);
                        Double value = (Double) m.invoke(rm, null);
                        if (sumMap.containsKey(dateStr)) {
                            sumMap.put(dateStr, sumMap.get(dateStr)+value);
                            numberMap.put(dateStr, numberMap.get(dateStr)+1);
                        }else {
                            sumMap.put(dateStr, value);
                            numberMap.put(dateStr, 1);
                        }
                    }
                    //将map中各个时间段的总值，以及数量的总和获取，取平均,保留两位小数点
                    DecimalFormat df = new DecimalFormat("######0.00");   
                    for (String key : sumMap.keySet()) {
                        HisMeteorological hm = new HisMeteorological();
                        Double sum = sumMap.get(key);
                        Integer number = numberMap.get(key);
                        hm.setAvg(df.format(sum/number));
                        hm.setDate_time(key);
                        hisMeteorologicalList1.add(hm);
                    }
                    //因为加入到map中，顺序乱了，进行重新排序
                    Collections.sort(hisMeteorologicalList1, new Comparator<HisMeteorological>() {
                        @Override
                        public int compare(HisMeteorological o1, HisMeteorological o2) {
                            return o1.getDate_time().compareTo(o2.getDate_time());
                        }
                    });
                    list.addAll(hisMeteorologicalList1);
                }else {
                    list.addAll(hisMeteorologicalList);
                }
                
            }
        }
        return list;
    }



}
