package com.nz.onlineMonitoring.historyData.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nz.onlineMonitoring.dict.service.DictService;
import com.nz.onlineMonitoring.historyData.model.HisData;
import com.nz.onlineMonitoring.historyData.service.HisDataService;
import com.nz.onlineMonitoring.utils.AuthorityUtil;
import com.nz.onlineMonitoring.utils.PageBean;

@Controller
@RequestMapping("/hisData")
public class HisDataController {
    @Autowired
    private HisDataService hisDataService;
    @Autowired
    private DictService dictService;
    
    /**
     * 
     * 方法描述查询历史数据，根据监测站名称、监测站编码（见数据字典中行政区划代码，输入市、县代码则显示全市、全县的监测站列表）、
     * 设备类型、监测对象、设备状态、时间段
     * @param map
     * @param real
     * @return
     * @author ssh
     * @date 2018年6月3日 下午4:18:48
     */
    @RequestMapping("/listHisData")
    public String listHisData(Map<String, Object> map,HisData hisData,HttpServletRequest request,
                                @RequestParam(required=false,defaultValue="1") int pages,
                                @RequestParam(required=false,name="city")String[] citys,
                                @RequestParam(required=false,defaultValue="1900-01-01T00:00")String data_time_begin1,
                                @RequestParam(required=false,defaultValue="9999-01-01T00:00")String data_time_end1){
        data_time_begin1 = data_time_begin1.replace('T', ' ');
        data_time_end1 = data_time_end1.replace('T', ' ');
        hisData.setData_time_begin(data_time_begin1);
        hisData.setData_time_end(data_time_end1);
        List<HisData> listHisData = null;
        try {
            AuthorityUtil.getInstance().assignPermissions(citys, request, hisData);
            map = PageBean.serverMap(map , hisData , pages);
            listHisData = hisDataService.listHisData(map);
            map = PageBean.clientMap(map ,pages,request);
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }finally {
            map.put("listHisData", listHisData);
            map.put("devStauts", dictService.listDevStauts());
            map.put("devObject", dictService.listDevType());
            map.put("devType", dictService.listDevType1());
        }
        return "hisData/listHisData";
    }
}


