package com.nz.onlineMonitoring.stationInfo.contrller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nz.onlineMonitoring.dict.service.DictService;
import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.stationInfo.model.Station;
import com.nz.onlineMonitoring.stationInfo.service.StationService;
import com.nz.onlineMonitoring.utils.AuthorityUtil;
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
    public String listStation(HttpServletRequest request, Station station,Map<String , Object> map,
                                    @RequestParam(required=false,defaultValue="1") int pages,
                                    @RequestParam(required=false,name="city")String[] citys,
                                    @RequestParam(required=false,defaultValue="1900-01-01")String date_begin1,
                                    @RequestParam(required=false,defaultValue="9999-01-01")String date_end1) {
        List<Station> listStation = null;
        station.setDate_begin(date_begin1);
        station.setDate_end(date_end1);
        try {
            AuthorityUtil.getInstance().assignPermissions(citys, request, station);
            map = PageBean.serverMap(map , station , pages);
            listStation = stationService.listStation(map);
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }finally {
            map = PageBean.clientMap(map ,pages,request);
            map.put("listStation", listStation);
            map.put("msType", dictService.listMsType());
            map.put("msFp", dictService.listMsFp());
            map.put("msGate", dictService.listMsGate());
            map.put("msNet", dictService.listMsNet());
            map.put("msDev", dictService.analysisMsDev());
        }
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
        Station station = null;
        try {
            station = stationService.load(id);
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }
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
        try {
            int result = stationService.delete(id);
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
        Station station = null;
        try {
            station = stationService.getStation(map,id);
            jd.success(map);
        } catch (Exception e) {
            jd.failure(e.getMessage());
        }finally {
            map.put("msType", dictService.listMsType());
            map.put("msFp", dictService.listMsFp());
            map.put("msGate", dictService.listMsGate());
            map.put("msNet", dictService.listMsNet());
            map.put("msDev", dictService.analysisMsDev());
            map.put("station", station);
        }
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
        if (ms_date1 != null && !Objects.equals(ms_date1, "")) {
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
    @PostMapping("/permissionMsCode")
    @ResponseBody
    public Integer permissionMsCode(String ms_code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login login = (Login) session.getAttribute("user");
        //获取登陆的账号
        int userMsCode1 = login.getAccount();
        String userMsCode2 = String.valueOf(userMsCode1);
        //如果登陆的账号是省级，直接返回1
        if (userMsCode2.startsWith("0000", 2)) {
            return 1; 
        }else if (userMsCode2.startsWith("00", 4)) {
            //如果账号是市级，判断前4位，相等返回1
            if (userMsCode2.substring(0, 4).equals(ms_code.substring(0, 4))) {
                return 1;
            }else {
                return -1;
            }
        }else {
            //如果是地级，判断前6位
            if (ms_code.startsWith(userMsCode2)) {
                return 1;
            }else {
                return -1;
            }
        }
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
    @GetMapping("/addStation")
    public String jumpAddStation(Map<String, Object> map) {
        map.put("msType", dictService.listMsType());
        map.put("msFp", dictService.listMsFp());
        map.put("msGate", dictService.listMsGate());
        map.put("msNet", dictService.listMsNet());
        map.put("msDev", dictService.analysisMsDev());
        return "station/addStation";
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
        if (ms_date1 != null && !Objects.equals(ms_date1, "")) {
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
