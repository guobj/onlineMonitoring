package com.nz.onlineMonitoring.stationInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.stationInfo.mapper.StationMapper;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationMapper stationMapper;
    @Override
    public List<Station> listStation(Map<String, Object> map) {
        return stationMapper.listStation(map);
    }
    @Override
    public Integer countStation(Map<String , Object> map) {
        return stationMapper.countStation(map);
    }

}
