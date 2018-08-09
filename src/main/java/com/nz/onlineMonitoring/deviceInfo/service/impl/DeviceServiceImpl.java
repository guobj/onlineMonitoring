package com.nz.onlineMonitoring.deviceInfo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.deviceInfo.mapper.DeviceMapper;
import com.nz.onlineMonitoring.deviceInfo.model.Device;
import com.nz.onlineMonitoring.deviceInfo.service.DeviceService;
import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private DictMapper dictMapper;

	/**
	 * t_device
	 * 方法描述: 设备信息查询
	 * @param map,device
	 * @return
	 * @throws
	 *
	 **/
	@Override
	public List<Device> queryDeviceInfo(Map<String, Object> map, Device device) {

		List<Device> queryList = deviceMapper.queryDeviceInfo(map);

		//记录总数
		Integer count = deviceMapper.count(map);
		if(queryList == null || queryList.size() <= 0){
			throw new  RuntimeException("暂无数据！");
		}
	    if (device.getController_ms_code()!=null && !Objects.equals(device.getController_ms_code(), "")) {
	        for (Device dev : queryList) {
	            //获取设备编码进行解析
	            analysisDevCode(dev);
                
                //解析监测站编码
                analysisMsCode(dev);
            }
	    }else {
	        for (Device dev : queryList) {
	            //解析监测站编码
                analysisMsCode(dev);
	        }
	    }
		map.put("list",queryList);
		map.put("count",count);
		
		return queryList;
	}

	@Override
	public Device deviceLoad(Integer id) {
		Device device = deviceMapper.load(id);
		if(device == null){
			throw new RuntimeException("数据不存在");
		}
		String dev_code = device.getDev_code();
		//设备编码解析开始
		char c = '0';
		Integer dev_type = 0;
		if(dev_code != null && !Objects.equals(dev_code, "")){
			c = dev_code.charAt(3);
			String str = dev_code.substring(3, 6);
			dev_type = Integer.parseInt(str);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
		sb.append(dictMapper.loadByDevType1(dev_type).getData_name());
		device.setDev_code(sb.toString());
		//设备编码解析结束
		//监测站编码解析开始
		String ms_code = device.getStation().getMs_code();
		String code01 = ms_code.substring(0, 6);
		String code02 = ms_code.substring(4, 6);

		String code2 = ms_code.substring(6, 8);
		if (code02.equals("00")) {
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			device.getStation().setMs_code(name1 + "第" + code2 +"个");
		}else {
			String code03 = ms_code.substring(0, 4);
			code03 += "00";
			String name2= dictMapper.loadCity(Integer.parseInt(code03)).getData_name();
			String name1= dictMapper.loadCity(Integer.parseInt(code01)).getData_name();
			device.setMs_code(name2+name1 + "第" + code2 +"个");
		}
		//监测站编码解析结束
		return device;
	}

	@Override
	public Integer updateById(Device device) {
		Integer res = deviceMapper.update(device);
		if(res <= 0){
			throw new RuntimeException("修改失败");
		}
		return res;
	}
	/**
	 * 
	 * 方法描述：解析监测设备编码
	 * @param device
	 * @author ssh
	 * @date 2018年8月7日 上午8:16:46
	 */
	private void analysisDevCode(Device device) {
	    //获取设备编码进行解析
        String dev_code = device.getDev_code();
        char c = '0';
        Integer dev_type = 0;
        if(dev_code != null && !Objects.equals(dev_code, "")){
            c = dev_code.charAt(3);
            String str = dev_code.substring(3, 6);
            dev_type = Integer.parseInt(str);
        }
        StringBuilder sb = new StringBuilder();
        //设备对象
        sb.append(dictMapper.loadByDevType(Character.getNumericValue(c)).getData_name());
        //设备类型
        sb.append(dictMapper.loadByDevType1(dev_type).getData_name());
        
        sb.append("第"+dev_code.substring(6)+"个");
        device.setDev_value(sb.toString());
        
	}
	
	/**
	 * 
	 * 方法描述：解析监测站编码
	 * @param device
	 * @author ssh
	 * @date 2018年8月6日 下午5:02:40
	 */
	private void analysisMsCode(Device device) {
	  //解析监测站编码
        String ms_code = device.getMs_code();
        String code01 = ms_code.substring(0, 6);
        String code02 = ms_code.substring(4, 6);
        String code2 = ms_code.substring(6, 8);
        //当5,6位是00的时候，不论是山东省，还是济南市这样的编码，都不会再往下加地级市了。所以从字典表中拿出数据，然后解析一下
        if (code02.equals("00")) {
            Dict city = dictMapper.loadCity(Integer.parseInt(code01));
            if (city != null) {
                String name1= city.getData_name();
                device.setMs_code_value(name1 + "第" + code2 +"个");
            }else {
                device.setMs_code_value("无法解析编码");
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
                device.setMs_code_value(name2+name1 + "第" + code2 +"个");
            }else {
                device.setMs_code_value("无法解析编码");
            }
        }
	}
	
}
