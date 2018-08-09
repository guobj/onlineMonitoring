package com.nz.onlineMonitoring.view.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.view.mapper.VDevStatusMapper;
import com.nz.onlineMonitoring.view.model.VDevStatus;
import com.nz.onlineMonitoring.view.service.VDevStatusService;

@Service
public class VDevStatusServiceImpl implements VDevStatusService {

	@Autowired
	private VDevStatusMapper vDevStatusMapper;

	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<VDevStatus> deviceStatusList(Map<String, Object> map, VDevStatus vDevStatus) {
		List<VDevStatus> vDevStatusList = vDevStatusMapper.deviceStatusList(map);
		Integer count = vDevStatusMapper.count(map);
		if(vDevStatusList == null || vDevStatusList.size() <= 0){
			throw new RuntimeException("暂无数据");
		}
		if (vDevStatus.getController_ms_code() != null && !Objects.equals(vDevStatus.getController_ms_code(), "")) {
		    for (VDevStatus vds : vDevStatusList) {
		        //获取设备编码进行解析
		        analysisDevCode(vds);
		        //解析监测站编码
		        analysisMsCode(vds);
		    }
		}else {
		    for (VDevStatus vds : vDevStatusList) {
		      //解析监测站编码
                analysisMsCode(vds);
		    }
		}
		map.put("list", vDevStatusList);
		map.put("count", count);
		return vDevStatusList;
	}
	
	/**
     * 
     * 方法描述：解析监测设备编码
     * @param device
     * @author ssh
     * @date 2018年8月7日 上午8:16:46
     */
    private void analysisDevCode(VDevStatus vDevStatus) {
      //获取设备编码进行解析
        String dev_code = vDevStatus.getDev_code();
        char c = '0';
        Integer dev_type = 0;
        if(dev_code != null && !Objects.equals(dev_code, "")){
            c = dev_code.charAt(3);
            String str = dev_code.substring(3, 6);
            dev_type = Integer.parseInt(str); 
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
        vDevStatus.setDev_code(sb.toString());
        
    }
    
    /**
     * 
     * 方法描述：解析监测站编码
     * @param device
     * @author ssh
     * @date 2018年8月6日 下午5:02:40
     */
    private void analysisMsCode(VDevStatus vDevStatus) {
      //解析监测站编码
        String ms_code = vDevStatus.getMs_code();
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        String code2 = ms_code.substring(6, 8);
        //当5,6位是00的时候，不论是山东省，还是济南市这样的编码，都不会再往下加地级市了。所以从字典表中拿出数据，然后解析一下
        if (code02.equals("00")) {
            Dict city = dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                vDevStatus.setMs_code_value(name1 + "第" + code2 +"个");
            }else {
                vDevStatus.setMs_code_value("无法解析编码");
            }
            
        }else {
            //先取前四位加上00，取到市的具体值
            String code03 = ms_code.substring(0, 4);
            code03 += "00";
            Dict city1 = dictMapper.loadCity(Integer.parseInt(code03));
            //然后取地级市的具体值
            Dict city2 = dictMapper.loadCity(Integer.parseInt(code01));
            if (city1 != null && city2 != null) {
                String name2= city1.getData_name();
                String name1= city2.getData_name();
                vDevStatus.setMs_code_value(name2+name1 + "第" + code2 +"个");
            }else {
                vDevStatus.setMs_code_value("无法解析编码");
            }
        }
    }
}
