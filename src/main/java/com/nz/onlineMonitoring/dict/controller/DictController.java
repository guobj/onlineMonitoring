package com.nz.onlineMonitoring.dict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.dict.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {
    
    @Autowired
    private DictService dictService;
    /**
     * 
     * 方法描述：查询山东省的所有市,使用ajax获取
     * @return
     * @author ssh
     * @date 2018年6月5日 上午10:32:24
     */
    @RequestMapping("/listCity")
    @ResponseBody
    public List<Dict> listCity() {
        return dictService.listCity();
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
    public List<Dict> listArea(Integer city_id) {
        if (city_id == null) {
            return null;
        }
        return dictService.listArea(city_id);
    }
    
    
}
