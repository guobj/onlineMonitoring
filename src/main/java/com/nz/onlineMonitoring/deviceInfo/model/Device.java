/**
 * Device.java
 * ©2006-2016 四海兴唐科技有限公司 
 * All rights reserved.
 * <link>胖先生作品</link>
 * 创建于: 2018-06-01 11:08:33
 **/
package com.nz.onlineMonitoring.deviceInfo.model;

import com.nz.onlineMonitoring.dict.model.Dict;
import com.nz.onlineMonitoring.stationInfo.model.Station;

/**
 * t_device 类
 * @Description : 
 * 
 * 文件创建于: 2018-06-01 11:08:33
 **/
public class Device {

    /**序号-主键、自增、界面不可见,所属表字段为 t_device.id  */
    private Integer id;
    /**监测站名称-与t_station ms_name关联,所属表字段为 t_device.ms_name  */
    private String ms_code;
    /**设备编码-由网关设备写入数据库,所属表字段为 t_device.dev_code  */
    private String dev_code;
    /**设备类型-关联表，见数据字典，由网关设备写入数据库，ps：101，201,202,所属表字段为 t_device.dev_type  */
    private Integer dev_type;
    /**设备厂家-关联表，见数据字典，由网关设备写入数据库,所属表字段为 t_device.dev_factory  */
    private Integer dev_factory;
    /**通信协议-关联表，见数据字典,所属表字段为 t_device.dev_protocol  */
    private Integer dev_protocol;
    /**通讯接口-关联表，见数据字典,所属表字段为 t_device.dev_interface  */
    private Integer dev_interface;
    /**从机地址-手动输入,所属表字段为 t_device.dev_regad  */
    private Integer dev_regad;
    /**ip地址-设备ip地址,所属表字段为 t_device.dev_ip  */
    private String dev_ip;
    /**通信端口,所属表字段为 t_device.dev_port  */
    private Integer dev_port;
    /**质保期限-年,所属表字段为 t_device.dev_warraty  */
    private Integer dev_warraty;
    /**设备厂家、品牌、型号、服务电话,所属表字段为 t_device.dev_mfrs  */
    private String dev_mfrs;
    /**设备描述,所属表字段为 t_device.dev_desc  */
    private String dev_desc;


	//是否删除 1删除0未删除
	private Boolean dr = false;

	//存储站点信息 与t_station关联
	private Station station;

	//存储数据字典data_type="dev_protocol"的相关信息
	private Dict dataProtocol;

	//存储数据字典data_type="dev_interface"的相关信息
	private Dict dataInterface;

	//存储数据字典data_type="dev_type"的相关信息 设备类型
	private Dict dataType;

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Dict getDataProtocol() {
		return dataProtocol;
	}

	public void setDataProtocol(Dict dataProtocol) {
		this.dataProtocol = dataProtocol;
	}

	public Dict getDataInterface() {
		return dataInterface;
	}

	public void setDataInterface(Dict dataInterface) {
		this.dataInterface = dataInterface;
	}

	public Boolean getDr() {
		return dr;
	}

	public void setDr(Boolean dr) {
		this.dr = dr;
	}
    /**
     * 获取 序号-主键、自增、界面不可见 字段:t_device.id
     *
     * @return  t_device.id  ,序号-主键、自增、界面不可见
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号-主键、自增、界面不可见 字段:t_device.id
     *
     * @param id  t_device.id,序号-主键、自增、界面不可见
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 获取 监测站编码-与t_station ms_code关联 字段:t_device.ms_code
     *
     * @return  t_device.ms_code  ,监测站编码-与t_station ms_code关联
     */
    public String getMs_code() {
        return ms_code;
    }

    /**
     * 设置 监测站编码-与t_station ms_code关联 字段:t_device.ms_code
     *
     * @param ms_code  t_device.ms_code,监测站编码-与t_station ms_code关联
     */
    public void setMs_code(String ms_code) {
        this.ms_code = ms_code == null ? null : ms_code.trim();
    }
    /**
     * 
     * 方法描述：设备类型-关联表，见数据字典，由网关设备写入数据库，ps：101，201,202,所属表字段为 t_device.dev_type
     * @return
     * @author ssh
     * @date 2018年6月9日 下午9:19:39
     */
    public Integer getDev_type() {
        return dev_type;
    }
    /**
     * 
     * 方法描述：设备类型-关联表，见数据字典，由网关设备写入数据库，ps：101，201,202,所属表字段为 t_device.dev_type
     * @return
     * @author ssh
     * @date 2018年6月9日 下午9:19:39
     */
    public void setDev_type(Integer dev_type) {
        this.dev_type = dev_type;
    }
    /**
     * 
     * 方法描述：通信协议-关联表，见数据字典,所属表字段为 t_device.dev_protocol 
     * @return
     * @author ssh
     * @date 2018年6月9日 下午9:19:58
     */
    public Integer getDev_factory() {
        return dev_factory;
    }
    /**
     * 
     * 方法描述：通信协议-关联表，见数据字典,所属表字段为 t_device.dev_protocol 
     * @return
     * @author ssh
     * @date 2018年6月9日 下午9:19:58
     */
    public void setDev_factory(Integer dev_factory) {
        this.dev_factory = dev_factory;
    }

    /**
     * 获取 设备编码-由网关设备写入数据库 字段:t_device.dev_code
     *
     * @return  t_device.dev_code  ,设备编码-由网关设备写入数据库
     */
    public String getDev_code() {
        return dev_code;
    }

    /**
     * 设置 设备编码-由网关设备写入数据库 字段:t_device.dev_code
     *
     * @param dev_code  t_device.dev_code,设备编码-由网关设备写入数据库
     */
    public void setDev_code(String dev_code) {
        this.dev_code = dev_code == null ? null : dev_code.trim();
    }

    /**
     * 获取 通信协议-关联表，见数据字典 字段:t_device.dev_protocol
     *
     * @return  t_device.dev_protocol  ,通信协议-关联表，见数据字典
     */
    public Integer getDev_protocol() {
        return dev_protocol;
    }

    /**
     * 设置 通信协议-关联表，见数据字典 字段:t_device.dev_protocol
     *
     * @param dev_protocol  t_device.dev_protocol,通信协议-关联表，见数据字典
     */
    public void setDev_protocol(Integer dev_protocol) {
        this.dev_protocol = dev_protocol;
    }

    /**
     * 获取 通讯接口-关联表，见数据字典 字段:t_device.dev_interface
     *
     * @return  t_device.dev_interface  ,通讯接口-关联表，见数据字典
     */
    public Integer getDev_interface() {
        return dev_interface;
    }

    /**
     * 设置 通讯接口-关联表，见数据字典 字段:t_device.dev_interface
     *
     * @param dev_interface  t_device.dev_interface,通讯接口-关联表，见数据字典
     */
    public void setDev_interface(Integer dev_interface) {
        this.dev_interface = dev_interface;
    }

    /**
     * 获取 从机地址-手动输入 字段:t_device.dev_regad
     *
     * @return  t_device.dev_regad  ,从机地址-手动输入
     */
    public Integer getDev_regad() {
        return dev_regad;
    }

    /**
     * 设置 从机地址-手动输入 字段:t_device.dev_regad
     *
     * @param dev_regad  t_device.dev_regad,从机地址-手动输入
     */
    public void setDev_regad(Integer dev_regad) {
        this.dev_regad = dev_regad;
    }

    /**
     * 获取 ip地址-设备ip地址 字段:t_device.dev_ip
     *
     * @return  t_device.dev_ip  ,ip地址-设备ip地址
     */
    public String getDev_ip() {
        return dev_ip;
    }

    /**
     * 设置 ip地址-设备ip地址 字段:t_device.dev_ip
     *
     * @param dev_ip  t_device.dev_ip,ip地址-设备ip地址
     */
    public void setDev_ip(String dev_ip) {
        this.dev_ip = dev_ip == null ? null : dev_ip.trim();
    }

    /**
     * 获取 通信端口-关联表，见数据字典 字段:t_device.dev_port
     *
     * @return  t_device.dev_port  ,通信端口-关联表，见数据字典
     */
    public Integer getDev_port() {
        return dev_port;
    }

    /**
     * 设置 通信端口-关联表，见数据字典 字段:t_device.dev_port
     *
     * @param dev_port  t_device.dev_port,通信端口-关联表，见数据字典
     */
    public void setDev_port(Integer dev_port) {
        this.dev_port = dev_port;
    }

    /**
     * 获取 质保期限-年 字段:t_device.dev_warraty
     *
     * @return  t_device.dev_warraty  ,质保期限-年
     */
    public Integer getDev_warraty() {
        return dev_warraty;
    }

    /**
     * 设置 质保期限-年 字段:t_device.dev_warraty
     *
     * @param dev_warraty  t_device.dev_warraty,质保期限-年
     */
    public void setDev_warraty(Integer dev_warraty) {
        this.dev_warraty = dev_warraty;
    }

    /**
     * 获取 设备厂家、品牌、型号、服务电话 字段:t_device.dev_mfrs
     *
     * @return  t_device.dev_mfrs  ,设备厂家、品牌、型号、服务电话
     */
    public String getDev_mfrs() {
        return dev_mfrs;
    }

    /**
     * 设置 设备厂家、品牌、型号、服务电话 字段:t_device.dev_mfrs
     *
     * @param dev_mfrs  t_device.dev_mfrs,设备厂家、品牌、型号、服务电话
     */
    public void setDev_mfrs(String dev_mfrs) {
        this.dev_mfrs = dev_mfrs == null ? null : dev_mfrs.trim();
    }

    /**
     * 获取 设备描述 字段:t_device.dev_desc
     *
     * @return  t_device.dev_desc  ,设备描述
     */
    public String getDev_desc() {
        return dev_desc;
    }

    /**
     * 设置 设备描述 字段:t_device.dev_desc
     *
     * @param dev_desc  t_device.dev_desc,设备描述
     */
    public void setDev_desc(String dev_desc) {
        this.dev_desc = dev_desc == null ? null : dev_desc.trim();
    }
}