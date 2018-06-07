/**
 * Station.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.stationInfo.model;

import com.nz.onlineMonitoring.data.model.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * t_station 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class Station {

    /**序号-主键、自增,所属表字段为 t_station.id  */
    private Integer id;
    /**监测站名称,所属表字段为 t_station.ms_name  */
    private String ms_name;
    /**监测站编码-按照规范命名,所属表字段为 t_station.ms_code  */
    private String ms_code;
    /**建设时间,所属表字段为 t_station.ms_date  */
    private Date ms_date;
    /**建设内容-关联表，见数据字典(设备类型，包含哪些设备),所属表字段为 t_station.ms_dev  */
    private String ms_dev;
    /**监测站类型-关联表，见数据字典,所属表字段为 t_station.ms_type  */
    private Integer ms_type;
    /**资金来源-关联表，见数据字典,所属表字段为 t_station.ms_fp  */
    private Integer ms_fp;
    /**监测站位置-手动输入位置信息,所属表字段为 t_station.ms_place  */
    private String ms_place;
    /**经纬度-5位小数，由c/s系统写入,所属表字段为 t_station.ms_position  */
    private BigDecimal ms_position;
    /**使用单位、联系人、联系方式-直接输入，用逗号隔开,所属表字段为 t_station.ms_user  */
    private String ms_user;
    /**施工单位、联系人、联系方式-直接输入，用逗号隔开,所属表字段为 t_station.ms_builder  */
    private String ms_builder;
    /**网络类型-关联表，见数据字典,所属表字段为 t_station.ms_net  */
    private Integer ms_net;
    /**网关类型-关联表，见数据字典,所属表字段为 t_station.ms_gate  */
    private Integer ms_gate;
    /**监测站描述,所属表字段为 t_station.ms_desc  */
    private String ms_desc;


	//是否删除 1删除0未删除
	private Boolean dr = false;

	public Boolean getDr() {
		return dr;
	}

	public void setDr(Boolean dr) {
		this.dr = dr;
	}

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	/**与ms_dev关联，是数据字典中的值 */
	private String ms_dev_value;
	/**与ms_type关联，是数据字典中的值 */
	private Data ms_type_value;
	/**与ms_fp关联，是数据字典中的值 */
	private Data ms_fp_value;
	/**与ms_net关联，是数据字典中的值 */
	private Data ms_net_value;
	/**与ms_gate关联，是数据字典中的值 */
	private Data ms_gate_value;
    /**
     * 获取 序号-主键、自增 字段:t_station.id
     *
     * @return  t_station.id  ,序号-主键、自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增 字段:t_station.id
     *
     * @param id  t_station.id,序号-主键、自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 监测站名称 字段:t_station.ms_name
     *
     * @return  t_station.ms_name  ,监测站名称
     */
    public String getMs_name() {
        return ms_name;
    }

    /**
     * 设置 监测站名称 字段:t_station.ms_name
     *
     * @param ms_name  t_station.ms_name,监测站名称
     */
    public void setMs_name(String ms_name) {
        this.ms_name = ms_name == null ? null : ms_name.trim();
    }

    /**
     * 获取 监测站编码-按照规范命名 字段:t_station.ms_code
     *
     * @return  t_station.ms_code  ,监测站编码-按照规范命名
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-按照规范命名 字段:t_station.ms_code
     *
     * @param ms_code  t_station.ms_code,监测站编码-按照规范命名
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }

    /**
     * 获取 建设时间 字段:t_station.ms_date
     *
     * @return  t_station.ms_date  ,建设时间
     */
    public Date getMs_date() {
        return ms_date;
    }

    /**
     * 设置 建设时间 字段:t_station.ms_date
     *
     * @param ms_date  t_station.ms_date,建设时间
     */
    public void setMs_date(Date ms_date) {
        this.ms_date = ms_date;
    }

    /**
     * 获取 建设内容-关联表，见数据字典(设备类型，包含哪些设备) 字段:t_station.ms_dev
     *
     * @return  t_station.ms_dev  ,建设内容-关联表，见数据字典(设备类型，包含哪些设备)
     */
    public String getMs_dev() {
        return ms_dev;
    }

    /**
     * 设置 建设内容-关联表，见数据字典(设备类型，包含哪些设备) 字段:t_station.ms_dev
     *
     * @param ms_dev  t_station.ms_dev,建设内容-关联表，见数据字典(设备类型，包含哪些设备)
     */
    public void setMs_dev(String ms_dev) {
        this.ms_dev = ms_dev == null ? null : ms_dev.trim();
    }

    /**
     * 获取 监测站类型-关联表，见数据字典 字段:t_station.ms_type
     *
     * @return  t_station.ms_type  ,监测站类型-关联表，见数据字典
     */
    public Integer getMs_type() {
        return ms_type;
    }

    /**
     * 设置 监测站类型-关联表，见数据字典 字段:t_station.ms_type
     *
     * @param ms_type  t_station.ms_type,监测站类型-关联表，见数据字典
     */
    public void setMs_type(Integer ms_type) {
        this.ms_type = ms_type;
    }

    /**
     * 获取 资金来源-关联表，见数据字典 字段:t_station.ms_fp
     *
     * @return  t_station.ms_fp  ,资金来源-关联表，见数据字典
     */
    public Integer getMs_fp() {
        return ms_fp;
    }

    /**
     * 设置 资金来源-关联表，见数据字典 字段:t_station.ms_fp
     *
     * @param ms_fp  t_station.ms_fp,资金来源-关联表，见数据字典
     */
    public void setMs_fp(Integer ms_fp) {
        this.ms_fp = ms_fp;
    }

    /**
     * 获取 监测站位置-手动输入位置信息 字段:t_station.ms_place
     *
     * @return  t_station.ms_place  ,监测站位置-手动输入位置信息
     */
    public String getMs_place() {
        return ms_place;
    }

    /**
     * 设置 监测站位置-手动输入位置信息 字段:t_station.ms_place
     *
     * @param ms_place  t_station.ms_place,监测站位置-手动输入位置信息
     */
    public void setMs_place(String ms_place) {
        this.ms_place = ms_place == null ? null : ms_place.trim();
    }

    /**
     * 获取 经纬度-5位小数，由c/s系统写入 字段:t_station.ms_position
     *
     * @return  t_station.ms_position  ,经纬度-5位小数，由c/s系统写入
     */
    public BigDecimal getMs_position() {
        return ms_position;
    }

    /**
     * 设置 经纬度-5位小数，由c/s系统写入 字段:t_station.ms_position
     *
     * @param ms_position  t_station.ms_position,经纬度-5位小数，由c/s系统写入
     */
    public void setMs_position(BigDecimal ms_position) {
        this.ms_position = ms_position;
    }

    /**
     * 获取 使用单位、联系人、联系方式-直接输入，用逗号隔开 字段:t_station.ms_user
     *
     * @return  t_station.ms_user  ,使用单位、联系人、联系方式-直接输入，用逗号隔开
     */
    public String getMs_user() {
        return ms_user;
    }

    /**
     * 设置 使用单位、联系人、联系方式-直接输入，用逗号隔开 字段:t_station.ms_user
     *
     * @param ms_user  t_station.ms_user,使用单位、联系人、联系方式-直接输入，用逗号隔开
     */
    public void setMs_user(String ms_user) {
        this.ms_user = ms_user == null ? null : ms_user.trim();
    }

    /**
     * 获取 施工单位、联系人、联系方式-直接输入，用逗号隔开 字段:t_station.ms_builder
     *
     * @return  t_station.ms_builder  ,施工单位、联系人、联系方式-直接输入，用逗号隔开
     */
    public String getMs_builder() {
        return ms_builder;
    }

    /**
     * 设置 施工单位、联系人、联系方式-直接输入，用逗号隔开 字段:t_station.ms_builder
     *
     * @param ms_builder  t_station.ms_builder,施工单位、联系人、联系方式-直接输入，用逗号隔开
     */
    public void setMs_builder(String ms_builder) {
        this.ms_builder = ms_builder == null ? null : ms_builder.trim();
    }

    /**
     * 获取 网络类型-关联表，见数据字典 字段:t_station.ms_net
     *
     * @return  t_station.ms_net  ,网络类型-关联表，见数据字典
     */
    public Integer getMs_net() {
        return ms_net;
    }

    /**
     * 设置 网络类型-关联表，见数据字典 字段:t_station.ms_net
     *
     * @param ms_net  t_station.ms_net,网络类型-关联表，见数据字典
     */
    public void setMs_net(Integer ms_net) {
        this.ms_net = ms_net;
    }

    /**
     * 获取 网关类型-关联表，见数据字典 字段:t_station.ms_gate
     *
     * @return  t_station.ms_gate  ,网关类型-关联表，见数据字典
     */
    public Integer getMs_gate() {
        return ms_gate;
    }

    /**
     * 设置 网关类型-关联表，见数据字典 字段:t_station.ms_gate
     *
     * @param ms_gate  t_station.ms_gate,网关类型-关联表，见数据字典
     */
    public void setMs_gate(Integer ms_gate) {
        this.ms_gate = ms_gate;
    }

    /**
     * 获取 监测站描述 字段:t_station.ms_desc
     *
     * @return  t_station.ms_desc  ,监测站描述
     */
    public String getMs_desc() {
        return ms_desc;
    }

    /**
     * 设置 监测站描述 字段:t_station.ms_desc
     *
     * @param ms_desc  t_station.ms_desc,监测站描述
     */
    public void setMs_desc(String ms_desc) {
        this.ms_desc = ms_desc == null ? null : ms_desc.trim();
    }

	/**
	 *
	 * 方法描述：与ms_dev关联，是数据字典中的值
	 * @return
	 * @date 2018年6月2日 下午7:03:45
	 */
	public String getMs_dev_value() {
		return ms_dev_value;
	}
	/**
	 *
	 * 方法描述：与ms_dev关联，是数据字典中的值
	 * @param ms_dev_value
	 * @date 2018年6月2日 下午7:05:10
	 */
	public void setMs_dev_value(String ms_dev_value) {
		this.ms_dev_value = ms_dev_value;
	}
	/**
	 *
	 * 方法描述：与ms_type关联，是数据字典中的值
	 * @return
	 * @date 2018年6月2日 下午7:05:22
	 */
	public Data getMs_type_value() {
		return ms_type_value;
	}
	/**
	 *
	 * 方法描述：与ms_type关联，是数据字典中的值
	 * @param ms_type_value
	 * @date 2018年6月2日 下午7:05:30
	 */
	public void setMs_type_value(Data ms_type_value) {
		this.ms_type_value = ms_type_value;
	}
	/**
	 *
	 * 方法描述：与ms_fp关联，是数据字典中的值
	 * @return
	 * @date 2018年6月2日 下午7:05:34
	 */
	public Data getMs_fp_value() {
		return ms_fp_value;
	}
	/**
	 *
	 * 方法描述：与ms_fp关联，是数据字典中的值
	 * @param ms_fp_value
	 * @date 2018年6月2日 下午7:05:37
	 */
	public void setMs_fp_value(Data ms_fp_value) {
		this.ms_fp_value = ms_fp_value;
	}
	/**
	 *
	 * 方法描述：与ms_net关联，是数据字典中的值
	 * @return
	 * @date 2018年6月2日 下午7:05:41
	 */
	public Data getMs_net_value() {
		return ms_net_value;
	}
	/**
	 *
	 * 方法描述：与ms_net关联，是数据字典中的值
	 * @param ms_net_value
	 * @date 2018年6月2日 下午7:05:45
	 */
	public void setMs_net_value(Data ms_net_value) {
		this.ms_net_value = ms_net_value;
	}
	/**
	 *
	 * 方法描述：与ms_gate关联，是数据字典中的值
	 * @return
	 * @date 2018年6月2日 下午7:05:48
	 */
	public Data getMs_gate_value() {
		return ms_gate_value;
	}
	/**
	 *
	 * 方法描述：与ms_gate关联，是数据字典中的值
	 * @param ms_gate_value
	 * @date 2018年6月2日 下午7:05:54
	 */
	public void setMs_gate_value(Data ms_gate_value) {
		this.ms_gate_value = ms_gate_value;
	}
}