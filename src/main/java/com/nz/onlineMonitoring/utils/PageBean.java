package com.nz.onlineMonitoring.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageBean {
    public static Map<String , Object> serverMap(Map<String , Object> map,Object obj,Integer pages){
        //获取obj的类名
        String t = obj.getClass().getSimpleName();
        //将类名全部转为小写
        String type = t.toLowerCase();
        map.put(type , obj);
        //record mapper.xml中根据他进行选择从哪开始显示
        map.put("record" , (pages-1)*10);
        return map;
    }
    public static Map<String , Object> clientMap(Map<String , Object> map,Integer pages,HttpServletRequest request){
        int count = Integer.parseInt(map.get("count").toString());
        int sumPage = (count%10==0)?(count/10):(count/10+1);
        map.put("pages" , pages);
        map.put("sumPage" , sumPage);
        map.put("url" , request.getServletPath());
        return map;
    }
}
