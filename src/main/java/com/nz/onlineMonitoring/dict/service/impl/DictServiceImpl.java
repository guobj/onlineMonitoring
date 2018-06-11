package com.nz.onlineMonitoring.dict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.dict.mapper.DictMapper;
import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.dict.service.DictService;
@Service
public class DictServiceImpl implements DictService {
    
    @Autowired
    private DictMapper dictMapper;
    /**
     * 
     * 方法描述：查询山东省的所有市
     * @param data_value
     * @return
     * @author ssh
     * @date 2018年6月5日 上午9:24:18
     */
    @Override
    public List<Dict> listCity() {
        List<Dict> dataList = dictMapper.listCity();
        //将编码除以100.得到其前四位
        for (Dict d : dataList) {
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
    public List<Dict> listArea(int data_value) {
        return dictMapper.listArea(data_value);
    }
    /**
     * 
     * 方法描述：查询设备的全部状态
     * @return 
     * @author ssh
     * @date 2018年6月6日 下午9:27:03
     */
    @Override
    public List<Dict> listDevStauts() {
        return dictMapper.listDevStauts();
    }
    /**
     * 
     * 方法描述：查询设备监测的对象
     * @return
     * @author ssh
     * @date 2018年6月6日 下午9:27:28
     */
    @Override
    public List<Dict> listDevType() {
        return dictMapper.listDevType();
    }
    /**
     * 
     * 方法描述：查询设备的类型
     * @return
     * @author ssh
     * @date 2018年6月6日 下午9:27:51
     */
    @Override
    public List<Dict> listDevType1() {
        return dictMapper.listDevType1();
    }
    @Override
    public List<Dict> listMsDev() {
        // TODO Auto-generated method stub
        return dictMapper.listMsDev();
    }
    @Override
    public List<Dict> listMsFp() {
        return dictMapper.listMsFp();
    }
    @Override
    public List<Dict> listMsType() {
        return dictMapper.listMsType();
    }
    @Override
    public List<Dict> listMsGate() {
        return dictMapper.listMsGate();
    }
    @Override
    public Dict loadByDevType1(Integer data_value) {
        return dictMapper.loadByDevType1(data_value);
    }
    @Override
    public List<Dict> listMsNet() {
        return dictMapper.listMsNet();
    }
    @Override
    public List<Dict> analysisMsDev() {
        List<Dict> listData = dictMapper.listMsDev();
        if (listData == null || listData.size() < 0) {
            throw new RuntimeException("暂无数据");
        } else {
            for (Dict d : listData) {
                StringBuffer sb = new StringBuffer();
                sb.append(dictMapper.loadByDevType(Integer.parseInt(d.getData_name().substring(3, 4))).getData_name());
                sb.append(dictMapper.loadByDevType1(Integer.parseInt(d.getData_name().substring(3, 6))).getData_name());
                d.setData_name(sb.toString());
            }
        }
        return listData;
    }

}
