/**
 * Data.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.data.model;

/**
 * t_data 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class Data {

    /**数据类型,所属表字段为 t_data.data_type  */
    private String data_type;
    /**数据值,所属表字段为 t_data.data_value  */
    private Integer data_value;
    /**名称,所属表字段为 t_data.data_name  */
    private String data_name;
    /**排序,所属表字段为 t_data.data_order  */
    private Integer data_order;

    /**
     * 获取 数据类型 字段:t_data.data_type
     *
     * @return  t_data.data_type  ,数据类型
     */
    public String getData_type() {
        return data_type;
    }

    /**
     * 设置 数据类型 字段:t_data.data_type
     *
     * @param data_type  t_data.data_type,数据类型
     */
    public void setData_type(String data_type) {
        this.data_type = data_type == null ? null : data_type.trim();
    }

    /**
     * 获取 数据值 字段:t_data.data_value
     *
     * @return  t_data.data_value  ,数据值
     */
    public Integer getData_value() {
        return data_value;
    }

    /**
     * 设置 数据值 字段:t_data.data_value
     *
     * @param data_value  t_data.data_value,数据值
     */
    public void setData_value(Integer data_value) {
        this.data_value = data_value;
    }

    /**
     * 获取 名称 字段:t_data.data_name
     *
     * @return  t_data.data_name  ,名称
     */
    public String getData_name() {
        return data_name;
    }

    /**
     * 设置 名称 字段:t_data.data_name
     *
     * @param data_name  t_data.data_name,名称
     */
    public void setData_name(String data_name) {
        this.data_name = data_name == null ? null : data_name.trim();
    }

    /**
     * 获取 排序 字段:t_data.data_order
     *
     * @return  t_data.data_order  ,排序
     */
    public Integer getData_order() {
        return data_order;
    }

    /**
     * 设置 排序 字段:t_data.data_order
     *
     * @param data_order  t_data.data_order,排序
     */
    public void setData_order(Integer data_order) {
        this.data_order = data_order;
    }
}