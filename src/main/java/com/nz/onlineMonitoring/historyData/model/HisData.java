/**
 * HisData.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-13 19:52:05
 **/
package com.nz.onlineMonitoring.historyData.model;

import java.util.Date;

import com.nz.onlineMonitoring.dict.model.Dict;

/**
 * t_his_data 类
 * @Description : 
 * 
 * @author 胖先生
 * @version 1.0 
 * 文件创建于: 2018-06-13 19:52:05
 **/
public class HisData {

    /**序号-主键、自增、界面不可见,所属表字段为 t_his_data.id  */
    private Integer id;
    /**监测站编码-与t_station ms_code关联,所属表字段为 t_his_data.ms_code  */
    private String ms_code;
    /**设备编码-与t_devive dev_code关联,所属表字段为 t_his_data.dev_code  */
    private String dev_code;
    /**数据值,所属表字段为 t_his_data.data_value  */
    private String data_value;
    /**数据时间,所属表字段为 t_his_data.data_time  */
    private Date data_time;
    /**设备状态-关联表，见数据字典,所属表字段为 t_his_data.dev_status  */
    private Integer dev_status;

    /**根据设备的类型查询数据 */
    private String device_type;
    /**根据设备监测对象的查询数据 */
    private String device_object;
    /**设备编码 解析设备*/
    private String dev_code_value;
    //设备状态
    private Dict dataDevStatus;
    /**查询时开始时间 */
    private Date data_time_begin;
    /**查询时结束时间 */
    private Date data_time_end;
    /**
     * 获取 序号-主键、自增、界面不可见 字段:t_his_data.id
     *
     * @return  t_his_data.id  ,序号-主键、自增、界面不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、界面不可见 字段:t_his_data.id
     *
     * @param id  t_his_data.id,序号-主键、自增、界面不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_his_data.ms_code
     *
     * @return  t_his_data.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_his_data.ms_code
     *
     * @param ms_code  t_his_data.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 设备编码-与t_devive dev_code关联 字段:t_his_data.dev_code
     *
     * @return  t_his_data.dev_code  ,设备编码-与t_devive dev_code关联
     */
    public String getDev_code() {
        return dev_code;
    }

    /**
     * 设置 设备编码-与t_devive dev_code关联 字段:t_his_data.dev_code
     *
     * @param dev_code  t_his_data.dev_code,设备编码-与t_devive dev_code关联
     */
    public void setDev_code(String dev_code) {
        this.dev_code = dev_code == null ? null : dev_code.trim();
    }

    /**
     * 获取 数据值 字段:t_his_data.data_value
     *
     * @return  t_his_data.data_value  ,数据值
     */
    public String getData_value() {
        return data_value;
    }

    /**
     * 设置 数据值 字段:t_his_data.data_value
     *
     * @param data_value  t_his_data.data_value,数据值
     */
    public void setData_value(String data_value) {
        this.data_value = data_value == null ? null : data_value.trim();
    }

    /**
     * 获取 数据时间 字段:t_his_data.data_time
     *
     * @return  t_his_data.data_time  ,数据时间
     */
    public Date getData_time() {
        return data_time;
    }

    /**
     * 设置 数据时间 字段:t_his_data.data_time
     *
     * @param data_time  t_his_data.data_time,数据时间
     */
    public void setData_time(Date data_time) {
        this.data_time = data_time;
    }

    /**
     * 获取 设备状态-关联表，见数据字典 字段:t_his_data.dev_status
     *
     * @return  t_his_data.dev_status  ,设备状态-关联表，见数据字典
     */
    public Integer getDev_status() {
        return dev_status;
    }

    /**
     * 设置 设备状态-关联表，见数据字典 字段:t_his_data.dev_status
     *
     * @param dev_status  t_his_data.dev_status,设备状态-关联表，见数据字典
     */
    public void setDev_status(Integer dev_status) {
        this.dev_status = dev_status;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_object() {
        return device_object;
    }

    public void setDevice_object(String device_object) {
        this.device_object = device_object;
    }

    public String getDev_code_value() {
        return dev_code_value;
    }

    public void setDev_code_value(String dev_code_value) {
        this.dev_code_value = dev_code_value;
    }

    public Dict getDataDevStatus() {
        return dataDevStatus;
    }

    public void setDataDevStatus(Dict dataDevStatus) {
        this.dataDevStatus = dataDevStatus;
    }

    public Date getData_time_begin() {
        return data_time_begin;
    }

    public void setData_time_begin(Date data_time_begin) {
        this.data_time_begin = data_time_begin;
    }

    public Date getData_time_end() {
        return data_time_end;
    }

    public void setData_time_end(Date data_time_end) {
        this.data_time_end = data_time_end;
    }
    
}