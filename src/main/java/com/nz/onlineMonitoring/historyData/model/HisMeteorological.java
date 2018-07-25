/**
 * HisMeteorological.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-13 19:52:05
 **/
package com.nz.onlineMonitoring.historyData.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nz.onlineMonitoring.dict.model.Dict;

/**
 * t_his_meteorological 类
 * @Description : 
 * 
 * @author 胖先生
 * @version 1.0 
 * 文件创建于: 2018-06-13 19:52:05
 **/
public class HisMeteorological {

    /**序号-主键、自增、界面不可见,所属表字段为 t_his_meteorological.id  */
    private Integer id;
    /**监测站编码-与t_station ms_code关联,所属表字段为 t_his_meteorological.ms_code  */
    private String ms_code;
    /**设备编码-与t_devive dev_code关联,所属表字段为 t_his_meteorological.dev_code  */
    private String dev_code;
    /**空气温度,所属表字段为 t_his_meteorological.air_t  */
    private Double air_t;
    /**空气湿度,所属表字段为 t_his_meteorological.air_h  */
    private Double air_h;
    /**风速,所属表字段为 t_his_meteorological.wind_s  */
    private Double wind_s;
    /**风向,所属表字段为 t_his_meteorological.wind_d  */
    private Double wind_d;
    /**露点温度,所属表字段为 t_his_meteorological.dew_p  */
    private Double dew_p;
    /**降雨量,所属表字段为 t_his_meteorological.rain_f  */
    private Double rain_f;
    /**日照时数,所属表字段为 t_his_meteorological.sunshine_h  */
    private Double sunshine_h;
    /**大气压,所属表字段为 t_his_meteorological.atmo_s  */
    private Double atmo_s;
    /**土壤温度1,所属表字段为 t_his_meteorological.soil_t1  */
    private Double soil_t1;
    /**土壤温度2,所属表字段为 t_his_meteorological.soil_t2  */
    private Double soil_t2;
    /**土壤温度3,所属表字段为 t_his_meteorological.soil_t3  */
    private Double soil_t3;
    /**土壤湿度1,所属表字段为 t_his_meteorological.soil_h1  */
    private Double soil_h1;
    /**土壤湿度2,所属表字段为 t_his_meteorological.soil_h2  */
    private Double soil_h2;
    /**土壤湿度3,所属表字段为 t_his_meteorological.soil_h3  */
    private Double soil_h3;
    /**土壤EC值,所属表字段为 t_his_meteorological.soil_ec  */
    private Double soil_ec;
    /**数据时间,所属表字段为 t_his_meteorological.data_time  */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date data_time;
    /**设备状态-关联表，见数据字典,所属表字段为 t_his_meteorological.dev_status  */
    private Integer dev_status;

    /**根据设备的类型查询数据 */
    private String device_type;
    /**根据设备监测对象的查询数据 */
    private String device_object;
    /**设备编码 解析设备*/
    private String dev_code_value;
    /**监测站编码 解析数据*/
    private String ms_code_value;
    //设备状态
    private Dict dataDevStatus;
    /**查询时开始时间 */
    private String data_time_begin;
    /**查询时结束时间 */
    private String data_time_end;
    /**在前台根据设备查询的时候，如果是气象设备，会出现温度，湿度等的下拉框，hisData作为传到后台的对象，加这个字段传递次参数
     * 而hisMeteorological加上这个字段，用来在mapper.xml中查询
     *  */
    private String weather;
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

    public String getData_time_begin() {
        return data_time_begin;
    }

    public void setData_time_begin(String data_time_begin) {
        this.data_time_begin = data_time_begin;
    }

    public String getData_time_end() {
        return data_time_end;
    }

    public void setData_time_end(String data_time_end) {
        this.data_time_end = data_time_end;
    }

    /**
     * 获取 序号-主键、自增、界面不可见 字段:t_his_meteorological.id
     *
     * @return  t_his_meteorological.id  ,序号-主键、自增、界面不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、界面不可见 字段:t_his_meteorological.id
     *
     * @param id  t_his_meteorological.id,序号-主键、自增、界面不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_his_meteorological.ms_code
     *
     * @return  t_his_meteorological.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_his_meteorological.ms_code
     *
     * @param ms_code  t_his_meteorological.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 设备编码-与t_devive dev_code关联 字段:t_his_meteorological.dev_code
     *
     * @return  t_his_meteorological.dev_code  ,设备编码-与t_devive dev_code关联
     */
    public String getDev_code() {
        return dev_code;
    }

    /**
     * 设置 设备编码-与t_devive dev_code关联 字段:t_his_meteorological.dev_code
     *
     * @param dev_code  t_his_meteorological.dev_code,设备编码-与t_devive dev_code关联
     */
    public void setDev_code(String dev_code) {
        this.dev_code = dev_code == null ? null : dev_code.trim();
    }

    /**
     * 获取 空气温度 字段:t_his_meteorological.air_t
     *
     * @return  t_his_meteorological.air_t  ,空气温度
     */
    public Double getAir_t() {
        return air_t;
    }

    /**
     * 设置 空气温度 字段:t_his_meteorological.air_t
     *
     * @param air_t  t_his_meteorological.air_t,空气温度
     */
    public void setAir_t(Double air_t) {
        this.air_t = air_t;
    }

    /**
     * 获取 空气湿度 字段:t_his_meteorological.air_h
     *
     * @return  t_his_meteorological.air_h  ,空气湿度
     */
    public Double getAir_h() {
        return air_h;
    }

    /**
     * 设置 空气湿度 字段:t_his_meteorological.air_h
     *
     * @param air_h  t_his_meteorological.air_h,空气湿度
     */
    public void setAir_h(Double air_h) {
        this.air_h = air_h;
    }

    /**
     * 获取 风速 字段:t_his_meteorological.wind_s
     *
     * @return  t_his_meteorological.wind_s  ,风速
     */
    public Double getWind_s() {
        return wind_s;
    }

    /**
     * 设置 风速 字段:t_his_meteorological.wind_s
     *
     * @param wind_s  t_his_meteorological.wind_s,风速
     */
    public void setWind_s(Double wind_s) {
        this.wind_s = wind_s;
    }

    /**
     * 获取 风向 字段:t_his_meteorological.wind_d
     *
     * @return  t_his_meteorological.wind_d  ,风向
     */
    public Double getWind_d() {
        return wind_d;
    }

    /**
     * 设置 风向 字段:t_his_meteorological.wind_d
     *
     * @param wind_d  t_his_meteorological.wind_d,风向
     */
    public void setWind_d(Double wind_d) {
        this.wind_d = wind_d;
    }

    /**
     * 获取 露点温度 字段:t_his_meteorological.dew_p
     *
     * @return  t_his_meteorological.dew_p  ,露点温度
     */
    public Double getDew_p() {
        return dew_p;
    }

    /**
     * 设置 露点温度 字段:t_his_meteorological.dew_p
     *
     * @param dew_p  t_his_meteorological.dew_p,露点温度
     */
    public void setDew_p(Double dew_p) {
        this.dew_p = dew_p;
    }

    /**
     * 获取 降雨量 字段:t_his_meteorological.rain_f
     *
     * @return  t_his_meteorological.rain_f  ,降雨量
     */
    public Double getRain_f() {
        return rain_f;
    }

    /**
     * 设置 降雨量 字段:t_his_meteorological.rain_f
     *
     * @param rain_f  t_his_meteorological.rain_f,降雨量
     */
    public void setRain_f(Double rain_f) {
        this.rain_f = rain_f;
    }

    /**
     * 获取 日照时数 字段:t_his_meteorological.sunshine_h
     *
     * @return  t_his_meteorological.sunshine_h  ,日照时数
     */
    public Double getSunshine_h() {
        return sunshine_h;
    }

    /**
     * 设置 日照时数 字段:t_his_meteorological.sunshine_h
     *
     * @param sunshine_h  t_his_meteorological.sunshine_h,日照时数
     */
    public void setSunshine_h(Double sunshine_h) {
        this.sunshine_h = sunshine_h;
    }

    /**
     * 获取 大气压 字段:t_his_meteorological.atmo_s
     *
     * @return  t_his_meteorological.atmo_s  ,大气压
     */
    public Double getAtmo_s() {
        return atmo_s;
    }

    /**
     * 设置 大气压 字段:t_his_meteorological.atmo_s
     *
     * @param atmo_s  t_his_meteorological.atmo_s,大气压
     */
    public void setAtmo_s(Double atmo_s) {
        this.atmo_s = atmo_s;
    }

    /**
     * 获取 土壤温度1 字段:t_his_meteorological.soil_t1
     *
     * @return  t_his_meteorological.soil_t1  ,土壤温度1
     */
    public Double getSoil_t1() {
        return soil_t1;
    }

    /**
     * 设置 土壤温度1 字段:t_his_meteorological.soil_t1
     *
     * @param soil_t1  t_his_meteorological.soil_t1,土壤温度1
     */
    public void setSoil_t1(Double soil_t1) {
        this.soil_t1 = soil_t1;
    }

    /**
     * 获取 土壤温度2 字段:t_his_meteorological.soil_t2
     *
     * @return  t_his_meteorological.soil_t2  ,土壤温度2
     */
    public Double getSoil_t2() {
        return soil_t2;
    }

    /**
     * 设置 土壤温度2 字段:t_his_meteorological.soil_t2
     *
     * @param soil_t2  t_his_meteorological.soil_t2,土壤温度2
     */
    public void setSoil_t2(Double soil_t2) {
        this.soil_t2 = soil_t2;
    }

    /**
     * 获取 土壤温度3 字段:t_his_meteorological.soil_t3
     *
     * @return  t_his_meteorological.soil_t3  ,土壤温度3
     */
    public Double getSoil_t3() {
        return soil_t3;
    }

    /**
     * 设置 土壤温度3 字段:t_his_meteorological.soil_t3
     *
     * @param soil_t3  t_his_meteorological.soil_t3,土壤温度3
     */
    public void setSoil_t3(Double soil_t3) {
        this.soil_t3 = soil_t3;
    }

    /**
     * 获取 土壤湿度1 字段:t_his_meteorological.soil_h1
     *
     * @return  t_his_meteorological.soil_h1  ,土壤湿度1
     */
    public Double getSoil_h1() {
        return soil_h1;
    }

    /**
     * 设置 土壤湿度1 字段:t_his_meteorological.soil_h1
     *
     * @param soil_h1  t_his_meteorological.soil_h1,土壤湿度1
     */
    public void setSoil_h1(Double soil_h1) {
        this.soil_h1 = soil_h1;
    }

    /**
     * 获取 土壤湿度2 字段:t_his_meteorological.soil_h2
     *
     * @return  t_his_meteorological.soil_h2  ,土壤湿度2
     */
    public Double getSoil_h2() {
        return soil_h2;
    }

    /**
     * 设置 土壤湿度2 字段:t_his_meteorological.soil_h2
     *
     * @param soil_h2  t_his_meteorological.soil_h2,土壤湿度2
     */
    public void setSoil_h2(Double soil_h2) {
        this.soil_h2 = soil_h2;
    }

    /**
     * 获取 土壤湿度3 字段:t_his_meteorological.soil_h3
     *
     * @return  t_his_meteorological.soil_h3  ,土壤湿度3
     */
    public Double getSoil_h3() {
        return soil_h3;
    }

    /**
     * 设置 土壤湿度3 字段:t_his_meteorological.soil_h3
     *
     * @param soil_h3  t_his_meteorological.soil_h3,土壤湿度3
     */
    public void setSoil_h3(Double soil_h3) {
        this.soil_h3 = soil_h3;
    }

    /**
     * 获取 土壤EC值 字段:t_his_meteorological.soil_ec
     *
     * @return  t_his_meteorological.soil_ec  ,土壤EC值
     */
    public Double getSoil_ec() {
        return soil_ec;
    }

    /**
     * 设置 土壤EC值 字段:t_his_meteorological.soil_ec
     *
     * @param soil_ec  t_his_meteorological.soil_ec,土壤EC值
     */
    public void setSoil_ec(Double soil_ec) {
        this.soil_ec = soil_ec;
    }

    /**
     * 获取 数据时间 字段:t_his_meteorological.data_time
     *
     * @return  t_his_meteorological.data_time  ,数据时间
     */
    public Date getData_time() {
        return data_time;
    }

    /**
     * 设置 数据时间 字段:t_his_meteorological.data_time
     *
     * @param data_time  t_his_meteorological.data_time,数据时间
     */
    public void setData_time(Date data_time) {
        this.data_time = data_time;
    }

    /**
     * 获取 设备状态-关联表，见数据字典 字段:t_his_meteorological.dev_status
     *
     * @return  t_his_meteorological.dev_status  ,设备状态-关联表，见数据字典
     */
    public Integer getDev_status() {
        return dev_status;
    }

    /**
     * 设置 设备状态-关联表，见数据字典 字段:t_his_meteorological.dev_status
     *
     * @param dev_status  t_his_meteorological.dev_status,设备状态-关联表，见数据字典
     */
    public void setDev_status(Integer dev_status) {
        this.dev_status = dev_status;
    }

    public String getMs_code_value() {
        return ms_code_value;
    }

    public void setMs_code_value(String ms_code_value) {
        this.ms_code_value = ms_code_value;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    
}