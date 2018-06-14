package com.nz.onlineMonitoring.realData.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nz.onlineMonitoring.dict.service.DictService;
import com.nz.onlineMonitoring.realData.model.RealData;
import com.nz.onlineMonitoring.realData.service.RealDataService;
import com.nz.onlineMonitoring.utils.AuthorityUtil;
import com.nz.onlineMonitoring.utils.PageBean;

@Controller
@RequestMapping("/realData")
public class RealDataController {
    
    @Autowired
    private RealDataService realDataService;
    @Autowired
    private DictService dictService;
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
    @RequestMapping("/listRealData")
    public String listReal(Map<String, Object> map,RealData realData,HttpServletRequest request,@RequestParam(required=false,defaultValue="1") int pages,@RequestParam(required=false,name="city")String[] citys){
        List<RealData> listReal = null;
        try {
            AuthorityUtil.getInstance().assignPermissions(citys, request, realData);
            map = PageBean.serverMap(map , realData , pages);
            listReal = realDataService.listReal(map);
            map = PageBean.clientMap(map ,pages,request);
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }finally {
            map.put("listRealData", listReal);
            map.put("devStauts", dictService.listDevStauts());
            map.put("devObject", dictService.listDevType());
            map.put("devType", dictService.listDevType1());
        }
        return "realData/listRealData";
    }
}
