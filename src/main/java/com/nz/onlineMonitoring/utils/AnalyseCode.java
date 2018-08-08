package com.nz.onlineMonitoring.utils;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
@Component
public class AnalyseCode {
    
    @Autowired
    private DictMapper dictMapper;
    
    public static AnalyseCode analyseCode;
    
    @PostConstruct
    public void init() {
        analyseCode = this;
    }
    
    /**
     * 
     * 方法描述：解析监测站编码
     * @param object
     * @param ms_code
     * @throws Exception
     * @author ssh
     * @date 2018年8月8日 下午2:54:02
     */
    public static void msCode(Object object, String ms_code) throws Exception {
        Class clz = object.getClass();
        Field filed = clz.getDeclaredField("ms_code_value");
        filed.setAccessible(true);
        //解析监测站编码
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        String code2 = ms_code.substring(6, 8);
        //当5,6位是00的时候，不论是山东省，还是济南市这样的编码，都不会再往下加地级市了。所以从字典表中拿出数据，然后解析一下
        if (code02.equals("00")) {
            Dict city = analyseCode.dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                filed.set(object, name1 + "第" + code2 +"个");
            }else {
                filed.set(object, "无法解析编码");
            }
        }else {
            //先取前四位加上00，取到市的具体值
            String code03 = ms_code.substring(0, 4);
            code03 += "00";
            Dict city1 = analyseCode.dictMapper.loadCity(Integer.parseInt(code03));
            //然后取地级市的具体值
            Dict city2 = analyseCode.dictMapper.loadCity(Integer.parseInt(code01));
            if (city1 != null && city2 != null) {
                String name2= city1.getData_name();
                String name1= city2.getData_name();
                filed.set(object, name2+name1 + "第" + code2 +"个");
            }else {
                filed.set(object, "无法解析编码");
            }
        }
    }
    /**
     * 
     * 方法描述：解析设备编码
     * @param object
     * @param dev_code
     * @throws Exception
     * @author ssh
     * @date 2018年8月8日 下午2:55:42
     */
    public static void devCode(Object object, String dev_code) throws Exception {
        Class clz = object.getClass();
        Field filed = clz.getDeclaredField("dev_code_value");
        filed.setAccessible(true);
        //获取设备编码进行解析
        char c = '0';
        Integer dev_type = 0;
        if(dev_code != null && dev_code != ""){
            c = dev_code.charAt(3);
            String str = dev_code.substring(3, 6);
            dev_type = Integer.parseInt(str);
        }
        StringBuilder sb = new StringBuilder();
        //设备对象
        Dict devObject = analyseCode.dictMapper.loadByDevType(Character.getNumericValue(c));
        //设备类型
        Dict devType = analyseCode.dictMapper.loadByDevType1(dev_type);
        if (devObject == null || devType == null) {
            filed.set(object, "编码无法解析！");
        }else {
            filed.set(object, devObject.getData_name()+devType.getData_name()+"第"+dev_code.substring(6, 8)+"个");
        }
    }
    
}
