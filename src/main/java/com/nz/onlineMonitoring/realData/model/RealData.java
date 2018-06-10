/**
 * Real.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.realData.model;

import java.util.Date;

/**
 * t_real 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class RealData {

    /**序号-主键、自增、界面不可见,所属表字段为 t_real.id  */
    private Integer id;
    /**监测站名称-与t_station ms_name关联,所属表字段为 t_real.ms_name  */
    private String ms_code;
    /**设备编码-与t_devive dev_code关联,所属表字段为 t_real.dev_code  */
    private String dev_code;
    /**数据,所属表字段为 t_real.data_value  */
    private String data_value;
    /**时间-仅显示时间,所属表字段为 t_real.data_time  */
    private Date data_time;
    /**设备状态-关联表，见数据字典,所属表字段为 t_real.dev_stauts  */
    private Integer dev_stauts;


	//是否删除 1删除0未删除
	private Boolean dr = false;

	/**根据设备的类型查询数据 */
	private String device_type;
	/**根据设备监测对象的查询数据 */
	private String device_object;
    /**
     * 获取 序号-主键、自增、界面不可见 字段:t_real.id
     *
     * @return  t_real.id  ,序号-主键、自增、界面不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、界面不可见 字段:t_real.id
     *
     * @param id  t_real.id,序号-主键、自增、界面不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_real.ms_code
     *
     * @return  t_real.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_real.ms_code
     *
     * @param ms_code  t_real.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 设备编码-与t_devive dev_code关联 字段:t_real.dev_code
     *
     * @return  t_real.dev_code  ,设备编码-与t_devive dev_code关联
     */
    public String getDev_code() {
        return dev_code;
    }

    /**
     * 设置 设备编码-与t_devive dev_code关联 字段:t_real.dev_code
     *
     * @param dev_code  t_real.dev_code,设备编码-与t_devive dev_code关联
     */
    public void setDev_code(String dev_code) {
        this.dev_code = dev_code == null ? null : dev_code.trim();
    }

    /**
     * 获取 数据 字段:t_real.real_data
     *
     * @return  t_real.real_data  ,数据
     */
    public String getData_value() {
        return data_value;
    }

    /**
     * 设置 数据 字段:t_real.real_data
     *
     * @param real_data  t_real.real_data,数据
     */
    public void setData_value(String data_value) {
        this.data_value = data_value == null ? null : data_value.trim();
    }

    /**
     * 获取 时间-仅显示时间 字段:t_real.data_time
     *
     * @return  t_real.data_time  ,时间-仅显示时间
     */
    public Date getData_time() {
        return data_time;
    }

    /**
     * 设置 时间-仅显示时间 字段:t_real.data_time
     *
     * @param data_time  t_real.data_time,时间-仅显示时间
     */
    public void setData_time(Date data_time) {
        this.data_time = data_time;
    }

    /**
     * 获取 设备状态-关联表，见数据字典 字段:t_real.dev_stauts
     *
     * @return  t_real.dev_stauts  ,设备状态-关联表，见数据字典
     */
    public Integer getDev_stauts() {
        return dev_stauts;
    }

    /**
     * 设置 设备状态-关联表，见数据字典 字段:t_real.dev_stauts
     *
     * @param dev_stauts  t_real.dev_stauts,设备状态-关联表，见数据字典
     */
    public void setDev_stauts(Integer dev_stauts) {
        this.dev_stauts = dev_stauts;
    }
	/**
	 *
	 * 方法描述：是否删除 默认为0未删除，1为已删除
	 * @return
	 * @author ssh
	 * @date 2018年6月3日 下午1:12:03
	 */
	public Boolean getDr() {
		return dr;
	}
	/**
	 *
	 * 方法描述：是否删除 默认为0未删除，1为已删除
	 * @return
	 * @author ssh
	 * @date 2018年6月3日 下午1:12:03
	 */
	public void setDr(Boolean dr) {
		this.dr = dr;
	}
	/**
	 *
	 * 方法描述：根据设备的类型查询数据
	 * @return
	 * @author ssh
	 * @date 2018年6月3日 下午2:25:43
	 */
	public String getDevice_type() {
		return device_type;
	}
	/**
	 *
	 * 方法描述：根据设备的类型查询数据
	 * @param device_type
	 * @author ssh
	 * @date 2018年6月3日 下午2:25:55
	 */
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	/**
	 *
	 * 方法描述：根据设备监测对象的查询数据
	 * @return
	 * @author ssh
	 * @date 2018年6月3日 下午2:26:11
	 */
	public String getDevice_object() {
		return device_object;
	}
	/**
	 *
	 * 方法描述：根据设备监测对象的查询数据
	 * @param device_object
	 * @author ssh
	 * @date 2018年6月3日 下午2:26:19
	 */
	public void setDevice_object(String device_object) {
		this.device_object = device_object;
	}
}