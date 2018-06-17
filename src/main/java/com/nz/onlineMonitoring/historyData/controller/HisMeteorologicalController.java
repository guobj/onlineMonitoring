package com.nz.onlineMonitoring.historyData.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nz.onlineMonitoring.historyData.model.HisMeteorological;
import com.nz.onlineMonitoring.historyData.service.HisMeteorologicalService;

@Controller
@RequestMapping("/hisMeteorological")
public class HisMeteorologicalController {
    
    @Autowired
    private HisMeteorologicalService hisMeteorologicalService;
    
    /**
     * 
     * 方法描述：根据id，查询气象表中的一条数据
     * @param station
     * @param map
     * @return
     * @author ssh 
     * @date 2018年6月2日 下午9:18:29
     */
    @RequestMapping("/loadHisMeteorological")
    @ResponseBody
    public HisMeteorological getHisMeteorological(Integer id,Map<String , Object> map) {
        HisMeteorological hisMeteorological = null;
        try {
            hisMeteorological = hisMeteorologicalService.load(id);
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }
        return hisMeteorological;
    }
}
