package com.nz.onlineMonitoring.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.data.mapper.DataMapper;
import com.nz.onlineMonitoring.data.model.Data;
import com.nz.onlineMonitoring.data.service.DataService;
@Service
public class DataServiceImpl implements DataService {
    
    @Autowired
    private DataMapper dataMapper;
    /**
     * 
     * 方法描述：查询山东省的所有市
     * @param data_value
     * @return
     * @author ssh
     * @date 2018年6月5日 上午9:24:18
     */
    @Override
    public List<Data> listCity() {
        List<Data> dataList = dataMapper.listCity();
        //将编码除以100.得到其前四位
        for (Data d : dataList) {
            d.setData_value(d.getData_value()/100);
        }
        return dataList;
    }
    /**
     * 
     * 方法描述：根据市的id，查询市的区，会把市的长度取前四位，然后模糊查询
     * @param data_value 市的前四位
     * @return
     * @author ssh
     * @date 2018年6月5日 上午9:24:52
     */
    @Override
    public List<Data> listArea(int data_value) {
        return dataMapper.listArea(data_value);
    }

}
