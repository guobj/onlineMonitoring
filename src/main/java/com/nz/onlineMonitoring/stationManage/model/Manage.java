/**
 * Manage.java
 * 济南农智信息科技有限公司所有
 * Create By guobj
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.stationManage.model;

import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.stationInfo.model.Station;

/**
 * t_manage 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class Manage {

    /**序号-主键、自增、不可见,所属表字段为 t_manage.id  */
    private Integer id;

    /**监测站编码-与t_station ms_code关联,所属表字段为 t_manage.ms_code  */
    private String ms_code;

    /**上传地址,所属表字段为 t_manage.server_ip  */
    private String server_ip;

    /**端口号,所属表字段为 t_manage.server_port  */
    private String server_port;

    /**上传频率-按分钟计算,所属表字段为 t_manage.data_upload  */
    private Integer data_upload;

    /**存储周期-按天计算,所属表字段为 t_manage.data_storage  */
    private Integer data_storage;
    
    //存储t_station相关字段
    private Station station;

    //数据字典的字段
    private Dict data;

    public Dict getData() {
        return data;
    }

    public void setData(Dict data) {
        this.data = data;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * 获取 序号-主键、自增、不可见 字段:t_manage.id
     *
     * @return  t_manage.id  ,序号-主键、自增、不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、不可见 字段:t_manage.id
     *
     * @param id  t_manage.id,序号-主键、自增、不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_manage.ms_code
     *
     * @return  t_manage.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_manage.ms_code
     *
     * @param ms_code  t_manage.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 上传地址 字段:t_manage.server_ip
     *
     * @return  t_manage.server_ip  ,上传地址
     */
    public String getServer_ip() {
        return server_ip;
    }

    /**
     * 设置 上传地址 字段:t_manage.server_ip
     *
     * @param server_ip  t_manage.server_ip,上传地址
     */
    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip == null ? null : server_ip.trim();
    }

    /**
     * 获取 端口号 字段:t_manage.server_port
     *
     * @return  t_manage.server_port  ,端口号
     */
    public String getServer_port() {
        return server_port;
    }

    /**
     * 设置 端口号 字段:t_manage.server_port
     *
     * @param server_port  t_manage.server_port,端口号
     */
    public void setServer_port(String server_port) {
        this.server_port = server_port == null ? null : server_port.trim();
    }

    /**
     * 获取 上传频率-按分钟计算 字段:t_manage.data_upload
     *
     * @return  t_manage.data_upload  ,上传频率-按分钟计算
     */
    public Integer getData_upload() {
        return data_upload;
    }

    /**
     * 设置 上传频率-按分钟计算 字段:t_manage.data_upload
     *
     * @param data_upload  t_manage.data_upload,上传频率-按分钟计算
     */
    public void setData_upload(Integer data_upload) {
        this.data_upload = data_upload;
    }

    /**
     * 获取 存储周期-按天计算 字段:t_manage.data_storage
     *
     * @return  t_manage.data_storage  ,存储周期-按天计算
     */
    public Integer getData_storage() {
        return data_storage;
    }

    /**
     * 设置 存储周期-按天计算 字段:t_manage.data_storage
     *
     * @param data_storage  t_manage.data_storage,存储周期-按天计算
     */
    public void setData_storage(Integer data_storage) {
        this.data_storage = data_storage;
    }
}