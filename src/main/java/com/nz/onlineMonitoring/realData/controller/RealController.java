package com.nz.onlineMonitoring.realData.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nz.onlineMonitoring.realData.model.Real;
import com.nz.onlineMonitoring.realData.service.RealService;

@Controller
@RequestMapping("/real")
public class RealController {
    
    @Autowired
    private RealService realService;
    /**
     * 
     * 方法描述查询实时数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态
     * @param map
     * @param real
     * @return
     * @author ssh
     * @date 2018年6月3日 下午4:18:48
     */
    @RequestMapping("/listReal")
    @ResponseBody
    public List<Real> listReal(Map<String, Object> map,Real real){
        System.out.println("----"+real.getDevice_object());
        map.put("real", real);
        return realService.listReal(map);
    }
}
