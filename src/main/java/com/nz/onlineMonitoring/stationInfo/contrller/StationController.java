package com.nz.onlineMonitoring.stationInfo.contrller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
import com.nz.onlineMonitoring.utils.PageBean;

@Controller
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;
   /**
    * 
    * 方法描述：分页查询全部，并根据查询条件进行查询
    * @param station
    * @param map
    * @param pages
    * @return
    * @date 2018年6月2日 上午10:27:10
    */
    @RequestMapping("/listStation")
    public String listStation(HttpServletRequest request, Station station,Map<String , Object> map,@RequestParam(required=false,defaultValue="1") int pages) {
        map = PageBean.serverMap(map , station , pages);
        List<Station> listStation = stationService.listStation(map);
        Integer count = stationService.countStation(map);
        map = PageBean.clientMap(map ,pages,request);
        map.put("url" , "station/listStation");
        System.out.println("ceshi");
        return "station/listStation";
    }
    
}
