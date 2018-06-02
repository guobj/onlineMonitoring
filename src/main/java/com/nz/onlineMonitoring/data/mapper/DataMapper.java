/**
 * DataMapper.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.data.mapper;

import com.nz.onlineMonitoring.data.model.Data;

public interface DataMapper {
    /**
     * t_data
     * 方法描述:
     * @param  
     * @return 
     * @throws 
     * @date 2018-06-01 11:08:33
     * 
     **/
    int add(Data data);

    /*t_manage连表使用load方法*/
    /**
     * t_data
     * 方法描述: 在data_type="ms_type"时，查询一条记录
     * @param
     * @return
     * @date 2018-06-01 11:08:33
     *
     **/
    Data loadByDataValue(Integer data_value);


    /*t_device连表所使用load方法*/
    /**
     * t_data
     * 方法描述: 在data_type="dev_protocol"时，查询一条记录
     * @param
     * @return
     * @date 2018-06-01 11:08:33
     *
     **/
    Data loadByDevProtocol(Integer data_value);

    /**
     * t_data
     * 方法描述: 在data_type="dev_interface"时，查询一条记录
     * @param
     * @return
     * @date 2018-06-01 11:08:33
     *
     **/
    Data loadByDevInterface(Integer data_value);

    /**
     * t_data
     * 方法描述: 在data_type="dev_port"时，查询一条记录
     * @param
     * @return
     * @date 2018-06-01 11:08:33
     *
     **/
    Data loadByDevPort(Integer data_value);
}