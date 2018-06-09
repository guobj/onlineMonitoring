package com.nz.onlineMonitoring.data.controller;

import com.nz.onlineMonitoring.data.model.Data;
import com.nz.onlineMonitoring.data.service.DataService;
import com.nz.onlineMonitoring.utils.JacksonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class DataController {
    
    @Autowired
    private DataService dataService;
    /**
     * 
     * 方法描述：查询山东省的所有市,使用ajax获取
     * @return
     * @author ssh
     * @date 2018年6月5日 上午10:32:24
     */
    @RequestMapping("/listCity")
    @ResponseBody
    public List<Data> listCity() {
        return dataService.listCity();
    }
    
    /**
     * 
     * 方法描述：根据市的id，查询市的区，会把市的长度取前四位，然后模糊查询,使用ajax获取
     * @return
     * @author ssh
     * @date 2018年6月5日 上午10:32:24
     */
    @RequestMapping("/listArea")
    @ResponseBody
    public List<Data> listArea(Integer city_id) {
        if (city_id == null) {
            return null;
        }
        return dataService.listArea(city_id);
    }

    /**
     *
     * 方法描述：获取监测对象和监测类型
     * @return
     * @author ssh
     * @date 2018年6月5日 上午10:32:24
     */
    @RequestMapping("/queryDevType")
    @ResponseBody
    public JacksonData queryDevType() {
        JacksonData jacksonData = new JacksonData();
        Map<String, Object> map = new HashMap<>();
        //获取检测对象
        List<Data> devType = dataService.listDevType();
        //获取监测类型
        List<Data> devType1 = dataService.listDevType1();
        map.put("devType", devType);
        map.put("devType1", devType1);
        jacksonData.success(map);
        return jacksonData;
    }
    
}
