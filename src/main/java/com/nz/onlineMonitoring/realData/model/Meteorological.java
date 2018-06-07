/**
 * Meteorological.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.realData.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * t_meteorological 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class Meteorological {

    /**序号-主键、自增、界面不可见,所属表字段为 t_meteorological.id  */
    private Integer id;
    /**监测站名称-与t_station ms_name关联,所属表字段为 t_meteorological.ms_name  */
    private String ms_name;
    /**监测站编码-与t_station ms_code关联,所属表字段为 t_meteorological.ms_code  */
    private String ms_code;
    /**设备编码-与t_devive dev_code关联,所属表字段为 t_meteorological.dev_code  */
    private String dev_code;
    /**空气温度,所属表字段为 t_meteorological.air_t  */
    private BigDecimal air_t;
    /**空气湿度,所属表字段为 t_meteorological.air_h  */
    private BigDecimal air_h;
    /**风速,所属表字段为 t_meteorological.wind_s  */
    private BigDecimal wind_s;
    /**风向,所属表字段为 t_meteorological.wind_d  */
    private BigDecimal wind_d;
    /**露点温度,所属表字段为 t_meteorological.dew_p  */
    private BigDecimal dew_p;
    /**降雨量,所属表字段为 t_meteorological.rain_f  */
    private BigDecimal rain_f;
    /**日照时数,所属表字段为 t_meteorological.sunshine_h  */
    private BigDecimal sunshine_h;
    /**大气压,所属表字段为 t_meteorological.atmo_s  */
    private BigDecimal atmo_s;
    /**土壤温度1,所属表字段为 t_meteorological.soil_t1  */
    private BigDecimal soil_t1;
    /**土壤温度2,所属表字段为 t_meteorological.soil_t2  */
    private BigDecimal soil_t2;
    /**土壤温度3,所属表字段为 t_meteorological.soil_t3  */
    private BigDecimal soil_t3;
    /**土壤湿度1,所属表字段为 t_meteorological.soil_h1  */
    private BigDecimal soil_h1;
    /**土壤湿度2,所属表字段为 t_meteorological.soil_h2  */
    private BigDecimal soil_h2;
    /**土壤湿度3,所属表字段为 t_meteorological.soil_h3  */
    private BigDecimal soil_h3;
    /**土壤EC值,所属表字段为 t_meteorological.soil_ec  */
    private BigDecimal soil_ec;
    /**时间,所属表字段为 t_meteorological.time  */
    private Date time;
    /**设备状态-关联表，见数据字典,所属表字段为 t_meteorological.dev_status  */
    private Integer dev_status;

    //是否删除 1删除0未删除
    private Boolean dr = false;

    public Boolean getDr() {
        return dr;
    }

    public void setDr(Boolean dr) {
        this.dr = dr;
    }

    /**
     * 获取 序号-主键、自增、界面不可见 字段:t_meteorological.id
     *
     * @return  t_meteorological.id  ,序号-主键、自增、界面不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、界面不可见 字段:t_meteorological.id
     *
     * @param id  t_meteorological.id,序号-主键、自增、界面不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 监测站名称-与t_station ms_name关联 字段:t_meteorological.ms_name
     *
     * @return  t_meteorological.ms_name  ,监测站名称-与t_station ms_name关联
     */
    public String getMs_name() {
        return ms_name;
    }

    /**
     * 设置 监测站名称-与t_station ms_name关联 字段:t_meteorological.ms_name
     *
     * @param ms_name  t_meteorological.ms_name,监测站名称-与t_station ms_name关联
     */
    public void setMs_name(String ms_name) {
        this.ms_name = ms_name == null ? null : ms_name.trim();
    }

    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_meteorological.ms_code
     *
     * @return  t_meteorological.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_meteorological.ms_code
     *
     * @param ms_code  t_meteorological.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 设备编码-与t_devive dev_code关联 字段:t_meteorological.dev_code
     *
     * @return  t_meteorological.dev_code  ,设备编码-与t_devive dev_code关联
     */
    public String getDev_code() {
        return dev_code;
    }

    /**
     * 设置 设备编码-与t_devive dev_code关联 字段:t_meteorological.dev_code
     *
     * @param dev_code  t_meteorological.dev_code,设备编码-与t_devive dev_code关联
     */
    public void setDev_code(String dev_code) {
        this.dev_code = dev_code == null ? null : dev_code.trim();
    }

    /**
     * 获取 空气温度 字段:t_meteorological.air_t
     *
     * @return  t_meteorological.air_t  ,空气温度
     */
    public BigDecimal getAir_t() {
        return air_t;
    }

    /**
     * 设置 空气温度 字段:t_meteorological.air_t
     *
     * @param air_t  t_meteorological.air_t,空气温度
     */
    public void setAir_t(BigDecimal air_t) {
        this.air_t = air_t;
    }

    /**
     * 获取 空气湿度 字段:t_meteorological.air_h
     *
     * @return  t_meteorological.air_h  ,空气湿度
     */
    public BigDecimal getAir_h() {
        return air_h;
    }

    /**
     * 设置 空气湿度 字段:t_meteorological.air_h
     *
     * @param air_h  t_meteorological.air_h,空气湿度
     */
    public void setAir_h(BigDecimal air_h) {
        this.air_h = air_h;
    }

    /**
     * 获取 风速 字段:t_meteorological.wind_s
     *
     * @return  t_meteorological.wind_s  ,风速
     */
    public BigDecimal getWind_s() {
        return wind_s;
    }

    /**
     * 设置 风速 字段:t_meteorological.wind_s
     *
     * @param wind_s  t_meteorological.wind_s,风速
     */
    public void setWind_s(BigDecimal wind_s) {
        this.wind_s = wind_s;
    }

    /**
     * 获取 风向 字段:t_meteorological.wind_d
     *
     * @return  t_meteorological.wind_d  ,风向
     */
    public BigDecimal getWind_d() {
        return wind_d;
    }

    /**
     * 设置 风向 字段:t_meteorological.wind_d
     *
     * @param wind_d  t_meteorological.wind_d,风向
     */
    public void setWind_d(BigDecimal wind_d) {
        this.wind_d = wind_d;
    }

    /**
     * 获取 露点温度 字段:t_meteorological.dew_p
     *
     * @return  t_meteorological.dew_p  ,露点温度
     */
    public BigDecimal getDew_p() {
        return dew_p;
    }

    /**
     * 设置 露点温度 字段:t_meteorological.dew_p
     *
     * @param dew_p  t_meteorological.dew_p,露点温度
     */
    public void setDew_p(BigDecimal dew_p) {
        this.dew_p = dew_p;
    }

    /**
     * 获取 降雨量 字段:t_meteorological.rain_f
     *
     * @return  t_meteorological.rain_f  ,降雨量
     */
    public BigDecimal getRain_f() {
        return rain_f;
    }

    /**
     * 设置 降雨量 字段:t_meteorological.rain_f
     *
     * @param rain_f  t_meteorological.rain_f,降雨量
     */
    public void setRain_f(BigDecimal rain_f) {
        this.rain_f = rain_f;
    }

    /**
     * 获取 日照时数 字段:t_meteorological.sunshine_h
     *
     * @return  t_meteorological.sunshine_h  ,日照时数
     */
    public BigDecimal getSunshine_h() {
        return sunshine_h;
    }

    /**
     * 设置 日照时数 字段:t_meteorological.sunshine_h
     *
     * @param sunshine_h  t_meteorological.sunshine_h,日照时数
     */
    public void setSunshine_h(BigDecimal sunshine_h) {
        this.sunshine_h = sunshine_h;
    }

    /**
     * 获取 大气压 字段:t_meteorological.atmo_s
     *
     * @return  t_meteorological.atmo_s  ,大气压
     */
    public BigDecimal getAtmo_s() {
        return atmo_s;
    }

    /**
     * 设置 大气压 字段:t_meteorological.atmo_s
     *
     * @param atmo_s  t_meteorological.atmo_s,大气压
     */
    public void setAtmo_s(BigDecimal atmo_s) {
        this.atmo_s = atmo_s;
    }

    /**
     * 获取 土壤温度1 字段:t_meteorological.soil_t1
     *
     * @return  t_meteorological.soil_t1  ,土壤温度1
     */
    public BigDecimal getSoil_t1() {
        return soil_t1;
    }

    /**
     * 设置 土壤温度1 字段:t_meteorological.soil_t1
     *
     * @param soil_t1  t_meteorological.soil_t1,土壤温度1
     */
    public void setSoil_t1(BigDecimal soil_t1) {
        this.soil_t1 = soil_t1;
    }

    /**
     * 获取 土壤温度2 字段:t_meteorological.soil_t2
     *
     * @return  t_meteorological.soil_t2  ,土壤温度2
     */
    public BigDecimal getSoil_t2() {
        return soil_t2;
    }

    /**
     * 设置 土壤温度2 字段:t_meteorological.soil_t2
     *
     * @param soil_t2  t_meteorological.soil_t2,土壤温度2
     */
    public void setSoil_t2(BigDecimal soil_t2) {
        this.soil_t2 = soil_t2;
    }

    /**
     * 获取 土壤温度3 字段:t_meteorological.soil_t3
     *
     * @return  t_meteorological.soil_t3  ,土壤温度3
     */
    public BigDecimal getSoil_t3() {
        return soil_t3;
    }

    /**
     * 设置 土壤温度3 字段:t_meteorological.soil_t3
     *
     * @param soil_t3  t_meteorological.soil_t3,土壤温度3
     */
    public void setSoil_t3(BigDecimal soil_t3) {
        this.soil_t3 = soil_t3;
    }

    /**
     * 获取 土壤湿度1 字段:t_meteorological.soil_h1
     *
     * @return  t_meteorological.soil_h1  ,土壤湿度1
     */
    public BigDecimal getSoil_h1() {
        return soil_h1;
    }

    /**
     * 设置 土壤湿度1 字段:t_meteorological.soil_h1
     *
     * @param soil_h1  t_meteorological.soil_h1,土壤湿度1
     */
    public void setSoil_h1(BigDecimal soil_h1) {
        this.soil_h1 = soil_h1;
    }

    /**
     * 获取 土壤湿度2 字段:t_meteorological.soil_h2
     *
     * @return  t_meteorological.soil_h2  ,土壤湿度2
     */
    public BigDecimal getSoil_h2() {
        return soil_h2;
    }

    /**
     * 设置 土壤湿度2 字段:t_meteorological.soil_h2
     *
     * @param soil_h2  t_meteorological.soil_h2,土壤湿度2
     */
    public void setSoil_h2(BigDecimal soil_h2) {
        this.soil_h2 = soil_h2;
    }

    /**
     * 获取 土壤湿度3 字段:t_meteorological.soil_h3
     *
     * @return  t_meteorological.soil_h3  ,土壤湿度3
     */
    public BigDecimal getSoil_h3() {
        return soil_h3;
    }

    /**
     * 设置 土壤湿度3 字段:t_meteorological.soil_h3
     *
     * @param soil_h3  t_meteorological.soil_h3,土壤湿度3
     */
    public void setSoil_h3(BigDecimal soil_h3) {
        this.soil_h3 = soil_h3;
    }

    /**
     * 获取 土壤EC值 字段:t_meteorological.soil_ec
     *
     * @return  t_meteorological.soil_ec  ,土壤EC值
     */
    public BigDecimal getSoil_ec() {
        return soil_ec;
    }

    /**
     * 设置 土壤EC值 字段:t_meteorological.soil_ec
     *
     * @param soil_ec  t_meteorological.soil_ec,土壤EC值
     */
    public void setSoil_ec(BigDecimal soil_ec) {
        this.soil_ec = soil_ec;
    }

    /**
     * 获取 时间 字段:t_meteorological.time
     *
     * @return  t_meteorological.time  ,时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置 时间 字段:t_meteorological.time
     *
     * @param time  t_meteorological.time,时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取 设备状态-关联表，见数据字典 字段:t_meteorological.dev_status
     *
     * @return  t_meteorological.dev_status  ,设备状态-关联表，见数据字典
     */
    public Integer getDev_status() {
        return dev_status;
    }

    /**
     * 设置 设备状态-关联表，见数据字典 字段:t_meteorological.dev_status
     *
     * @param dev_status  t_meteorological.dev_status,设备状态-关联表，见数据字典
     */
    public void setDev_status(Integer dev_status) {
        this.dev_status = dev_status;
    }
}