package com.nz.onlineMonitoring.stationInfo.contrller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nz.onlineMonitoring.data.service.DataService;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
import com.nz.onlineMonitoring.utils.PageBean;

@Controller
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;
    @Autowired
    private DataService dataService;
    
   /**
    * 
    * 方法描述：分页查询全部，并根据查询条件进行查询
    * @param station
    * @param map
    * @param pages
    * @return
    * @author ssh
    * @date 2018年6月2日 上午10:27:10
    */
    @RequestMapping("/listStation")
    public String listStation(HttpServletRequest request, Station station,Map<String , Object> map,@RequestParam(required=false,defaultValue="1") int pages,@RequestParam(required=false,name="city")String[] citys) {
        if (citys != null) {
            if (citys[1] != null && citys[1] != "") {
                station.setMs_code(citys[1]);
            }else if (citys[0] != null && citys[0] != "") {
                station.setMs_code(citys[0]);
            }else {
                station.setMs_code("37");
            }
        }
        map = PageBean.serverMap(map , station , pages);
        List<Station> listStation = stationService.listStation(map);
        map = PageBean.clientMap(map ,pages,request);
        map.put("listStation", listStation);
        map.put("msType", dataService.listMsType());
        map.put("msFp", dataService.listMsFp());
        map.put("msGate", dataService.listMsGate());
        return "station/listStation";
    }
    /**
     * 
     * 方法描述：根据id，查询单个监测站
     * @param id
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月2日 下午7:51:31
     */
    @PostMapping("/loadStation")
    @ResponseBody
    public Station loadStation(Integer id,Map<String , Object> map) {
        Station station = stationService.load(id);
        return station;
    }
    /**
     * 
     * 方法描述：根据id，删除一个监测站
     * @param id
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月2日 下午8:12:14
     */
    @GetMapping("/deleteStation")
    public String deleteStation(Integer id,Map<String , Object> map) {
        String result = stationService.delete(id);
        map.put("result", result);
        return "station/deleteStation";
    }
    /**
     * 
     * 方法描述：根据id，修改一个监测站的信息
     * @param station
     * @param map
     * @return
     * @author ssh 
     * @date 2018年6月2日 下午9:18:29
     */
    @PostMapping("/updateStation")
    public String updateStation(Station station,Map<String , Object> map) {
        String result = stationService.update(station);
        map.put("result", result);
        return "station/updateStation";
    }
    /**
     * 
     * 方法描述：修改编码时，判断是否有重复的编码
     * @param ms_code
     * @return
     * @author ssh
     * @date 2018年6月3日 上午10:35:49
     */
    @PostMapping("/existMsCode")
    @ResponseBody
    public Integer existMsCode(String ms_code) {
        return stationService.existMsCode(ms_code);
    }
}
