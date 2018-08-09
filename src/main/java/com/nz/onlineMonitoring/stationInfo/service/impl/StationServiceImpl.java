package com.nz.onlineMonitoring.stationInfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.stationInfo.mapper.StationMapper;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
@Service
public class StationServiceImpl implements StationService{
 
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private DictMapper dictMapper;
    /**
     * t_station
     * 方法描述:查询全部数据
     * @param  
     * @return 
     * @throws 
     * @date 2018年6月2日 上午9:54:59
     * 
     **/
    @Override
    public List<Station> listStation(Map<String, Object> map) {
        Integer count = stationMapper.countStation(map);
        map.put("count", count);
        List<Station> listStation = stationMapper.listStation(map);
        if (listStation == null || listStation.size() <= 0) {
            throw new RuntimeException("暂无数据");
        }
        List<Dict> listData = dictMapper.listMsDev();
        if (listData == null || listData.size() < 0) {
            throw new RuntimeException("暂无数据");
        }
        //将data中的ms_code的value作为键，name作为值
        Map<String, String> mapDev = new HashMap<>();
        for (Dict d : listData) {
            mapDev.put(String.valueOf(d.getData_value()), d.getData_name());
        }
        //循环listStation,将ms_code中的值，一一取出，然后通过值去mapdev中比较，拿到具体的名字，然后拼接到ms_dev_value中
        for (Station s : listStation) {
            if (s.getMs_dev() != null && !Objects.equals("", s.getMs_dev())) {
                String[] temp = s.getMs_dev().split(",");
                for (int i = 0,n = temp.length; i < n; i++) {
                    temp[i] = mapDev.get(temp[i]);
                    //将设备编码解析成具体的name
                    StringBuffer sb = new StringBuffer();
                    Dict devObject = dictMapper.loadByDevType(Integer.parseInt(temp[i].substring(3, 4)));
                    Dict devType = dictMapper.loadByDevType1(Integer.parseInt(temp[i].substring(3, 6)));
                    if (devObject != null && devType != null) {
                         sb.append(devObject.getData_name());
                         sb.append(devType.getData_name());
                         temp[i] = sb.toString();
                    }
                    
                }
                s.setMs_dev_value(String.join(",", temp));
            }
        }
        return listStation;
        
    }
    /**
     * 
     * 方法描述：根据查询条件查询数据的条数
     * @return
     * @param
     * @throws
     * @author ssh
     * @date 2018年6月2日 上午9:57:22
     */
    @Override
    public Integer countStation(Map<String , Object> map) {
        int count = stationMapper.countStation(map);
        if (count <= 0) {
            throw new RuntimeException("暂无数据"); 
        }
        return count;
    }
    /**
     * 
     * 方法描述：查询单条数据
     * @param id
     * @return
     * @author ssh
     * @date 2018年6月2日 下午6:56:43
     */
    @Override
    public Station load(Integer id) {
        Station station = stationMapper.load(id);
        if (station == null) {
            throw new RuntimeException("暂无数据");
        }
        String dev = station.getMs_dev();
        List<Dict> listData = dictMapper.listMsDev();
        if (listData == null || listData.size() < 0) {
            throw new RuntimeException("暂无数据");
        }
        Map<String, String> mapDev = new HashMap<>();
        //将data中的ms_code的value作为键，name作为值
        for (Dict d : listData) {
            mapDev.put(String.valueOf(d.getData_value()), d.getData_name());
        }
        //因为ms_dev中的数据是用，分开的多个数据，所以没法用mapper直接查询，如果ms_dev不等于空，那么循环其中的数据，把从字典表中拿到的name值，拼接成字符串，传到ms_dev_value,用，隔开
        if (dev != null && !Objects.equals(dev, "")) {
            String[] temp = dev.split(",");
            for (int i = 0, n = temp.length; i < n; i++) {
                temp[i] = mapDev.get(temp[i]);
                StringBuffer sb = new StringBuffer();
                Dict devObject = dictMapper.loadByDevType(Integer.parseInt(temp[i].substring(3, 4)));
                Dict devType = dictMapper.loadByDevType1(Integer.parseInt(temp[i].substring(3, 6)));
                sb.append(devObject.getData_name());
                sb.append(devType.getData_name());
                temp[i] = sb.toString();
            }
            station.setMs_dev_value(String.join(",", temp));
        }
        //解析监测站编码
        String ms_code = station.getMs_code();
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        String code2 = ms_code.substring(6, 8);
        //当5,6位是00的时候，不论是山东省，还是济南市这样的编码，都不会再往下加地级市了。所以从字典表中拿出数据，然后解析一下
        if (code02.equals("00")) {
            Dict city = dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                station.setMs_code_value(name1 + "第" + code2 +"个");
            }else {
                station.setMs_code_value("无法解析编码");
            }
            
        }else {
            //先取前四位加上00，取到市的具体值
            String code03 = ms_code.substring(0, 4);
            code03 += "00";
            Dict city1 = dictMapper.loadCity(Integer.parseInt(code03));
            //然后取地级市的具体值
            Dict city2 = dictMapper.loadCity(Integer.parseInt(code01));
            if (city1 != null && city2 != null) {
                String name2= city1.getData_name();
                String name1= city2.getData_name();
                station.setMs_code_value(name2+name1 + "第" + code2 +"个");
            }else {
                station.setMs_code_value("无法解析编码");
            }
        }
        return station;
    }
    /**
     * 
     * 方法描述：根据id，删除一条监测站
     * @param id
     * @return
     * @author ssh
     * @date 2018年6月2日 下午8:07:08
     */
    @Override
    public Integer delete(Integer id) {
        int result = stationMapper.delete(id);
        if (result < 0) {
            throw new RuntimeException("删除失败");
        }
        return result;
    }
    /**
     * 根据id，修改监测站的信息
     * 方法描述：
     * @param station
     * @return
     * @author ssh
     * @date 2018年6月2日 下午8:50:50
     */
    @Override
    public Integer update(Station station) {
        int result = stationMapper.update(station);
        if (result < 0) {
            throw new RuntimeException("更新失败");
        }
        return result;
    }
    /**
     * 
     * 方法描述：修改编码时，判断是否有重复的编码
     * @param ms_code
     * @return
     * @author ssh
     * @date 2018年6月3日 上午10:17:15
     */
    @Override
    public Integer existMsCode(String ms_code) {
        return stationMapper.existMsCode(ms_code);
    }
    /**
     * 
     * 方法描述：根据id，查询一个监测站的信息以及字典表中的数据
     * @param station
     * @param map
     * @return
     * @author ssh 
     * @date 2018年6月2日 下午9:18:29
     */
    @Override
    public Station getStation(Map<String , Object> map,Integer id) {
        Station station = stationMapper.load(id);
        String dev= "";
        if (station == null) {
            throw new RuntimeException("暂无数据");
        }else {
            dev = station.getMs_dev();
        }
        List<Dict> listData = dictMapper.listMsDev();
        if (listData == null || listData.size() < 0) {
            throw new RuntimeException("暂无数据");
        }else {
            //将data中的ms_code的value作为键，name作为值
            //将字典表中的设备编码解析，用于前台修改时的遍历选择
            Map<String, String> mapDev = new HashMap<>();
            for (Dict d : listData) {
                mapDev.put(String.valueOf(d.getData_value()), d.getData_name());
            }
            //因为ms_dev中的数据是用，分开的多个数据，所以没法用mapper直接查询，如果ms_dev不等于空，那么循环其中的数据，把从字典表中拿到的name值，拼接成字符串，传到ms_dev_value,用，隔开
            if (dev != null && !Objects.equals(dev, "")) {
                String[] temp = dev.split(",");
                for (int i = 0, n = temp.length; i < n; i++) {
                    temp[i] = mapDev.get(temp[i]);
                    StringBuffer sb = new StringBuffer();
                    sb.append(dictMapper.loadByDevType(Integer.parseInt(temp[i].substring(3, 4))).getData_name());
                    sb.append(dictMapper.loadByDevType1(Integer.parseInt(temp[i].substring(3, 6))).getData_name());
                    temp[i] = sb.toString();
                }
                station.setMs_dev_value(String.join(",", temp));
            }
        }
        return station;
    }
    
    @Override
    public int add(Station station) {
        int result = stationMapper.add(station);
        if (result < 0) {
            throw new RuntimeException("添加失败");
        }
        return result;
    }

}
