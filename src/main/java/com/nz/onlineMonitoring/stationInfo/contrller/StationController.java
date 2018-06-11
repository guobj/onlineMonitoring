package com.nz.onlineMonitoring.stationInfo.contrller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.nz.onlineMonitoring.dict.service.DictService;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
import com.nz.onlineMonitoring.utils.JacksonData;
import com.nz.onlineMonitoring.utils.PageBean;

@Controller
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;
    @Autowired
    private DictService dictService;
    
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
        map.put("msType", dictService.listMsType());
        map.put("msFp", dictService.listMsFp());
        map.put("msGate", dictService.listMsGate());
        map.put("msNet", dictService.listMsNet());
        map.put("msDev", dictService.analysisMsDev());
        map.put("station", station);
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
    @ResponseBody
    public JacksonData deleteStation(Integer id,Map<String , Object> map) {
        JacksonData jd = new JacksonData();
        Station station = new Station();
        station.setId(id);
        station.setDr(true);
        try {
            int result = stationService.update(station);
            jd.success(result);
        } catch (Exception e) {
            jd.failure(e.getMessage());
        }
        return jd;
    }
    /**
     * 
     * 方法描述：根据id，查询一个监测站的信息以及字典表中的数据
     * @param station
     * @param map
     * @return
     * @author ssh 
     * @date 2018年6月2日 下午9:18:29
     */
    @RequestMapping("/getStation")
    @ResponseBody
    public JacksonData getStation(@RequestParam(required=false)Integer id,Map<String , Object> map) {
        JacksonData jd = new JacksonData();
        Station station = stationService.getStation(map,id);
        map.put("msType", dictService.listMsType());
        map.put("msFp", dictService.listMsFp());
        map.put("msGate", dictService.listMsGate());
        map.put("msNet", dictService.listMsNet());
        map.put("msDev", dictService.analysisMsDev());
        map.put("station", station);
        jd.success(map);
        return jd;
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
    @ResponseBody
    public JacksonData updateStation(Station station,Map<String , Object> map,String ms_date1) {
        if (ms_date1 != null && ms_date1 != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                station.setMs_date(sdf.parse(ms_date1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        JacksonData jd = new JacksonData();
        try {
            int result = stationService.update(station);
            jd.success(result);
        } catch (Exception e) {
            jd.failure(e.getMessage());
        }
        return jd;
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
    /**
     * 
     * 方法描述：添加监测站
     * @param id
     * @param map
     * @return
     * @author ssh
     * @date 2018年6月11日 下午3:26:58
     */
    @PostMapping("/addStation")
    @ResponseBody
    public JacksonData addStation(Station station,Map<String , Object> map,String ms_date1) {
        if (ms_date1 != null && ms_date1 != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                station.setMs_date(sdf.parse(ms_date1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        JacksonData jd = new JacksonData();
        try {
            int result = stationService.add(station);
            jd.success(result);
        } catch (Exception e) {
            jd.failure(e.getMessage());
        }
        return jd;
    }
}
