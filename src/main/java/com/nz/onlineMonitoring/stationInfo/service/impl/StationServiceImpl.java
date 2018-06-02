package com.nz.onlineMonitoring.stationInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.data.mapper.DataMapper;
import com.nz.onlineMonitoring.data.model.Data;
import com.nz.onlineMonitoring.stationInfo.mapper.StationMapper;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private DataMapper dataMapper;
   
    @Override
    public List<Station> listStation(Map<String, Object> map) {
        Integer count = stationMapper.countStation(map);
        map.put("count", count);
        return stationMapper.listStation(map);
        
    }
    @Override
    public Integer countStation(Map<String , Object> map) {
        return stationMapper.countStation(map);
    }
    @Override
    public Station load(Integer id) {
        Station station = stationMapper.load(id);
        String dev = station.getMs_dev();
        //因为ms_dev中的数据是用，分开的多个数据，所以没法用mapper直接查询，如果ms_dev不等于空，那么循环其中的数据，把从字典表中拿到的name值，拼接成字符串，传到ms_dev_value,用，隔开
        if (dev != null || dev != "") {
            String[] temp = dev.split(",");
            for (int i = 0, n = temp.length; i < n; i++) {
                Data data = dataMapper.loadDev(Integer.parseInt(temp[i]));
                temp[i] = data.getData_name();
            }
            station.setMs_dev_value(String.join(",", temp));
        }
        return station;
    }

}
