package com.nz.onlineMonitoring.view.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nz.onlineMonitoring.dict.model.Dict;

public class VDevStatus {

	private String ms_name;
	private String ms_code;
	private String dev_code;
	private Integer dev_type;
	private Integer dev_factory;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date data_time;
	private Integer dev_status;

	private Dict dataDevFactory;
	private Dict dataDevType;

	private Dict dataDevStatus;
	/**解析监测站编码的具体值*/
	private String ms_code_value;
	/** 通过这个是否为空来判断进入设备时，显示的界面  */
	private String controller_ms_code;
	
	public Dict getDataDevStatus() {
		return dataDevStatus;
	}

	public void setDataDevStatus(Dict dataDevStatus) {
		this.dataDevStatus = dataDevStatus;
	}

	public Dict getDataDevFactory() {
		return dataDevFactory;
	}

	public void setDataDevFactory(Dict dataDevFactory) {
		this.dataDevFactory = dataDevFactory;
	}

	public Dict getDataDevType() {
		return dataDevType;
	}

	public void setDataDevType(Dict dataDevType) {
		this.dataDevType = dataDevType;
	}

	private VRealData vRealData;

	public VRealData getvRealData() {
		return vRealData;
	}

	public void setvRealData(VRealData vRealData) {
		this.vRealData = vRealData;
	}

	public String getMs_name() {
		return ms_name;
	}

	public void setMs_name(String ms_name) {
		this.ms_name = ms_name;
	}

	public String getMs_code() {
		return ms_code;
	}

	public void setMs_code(String ms_code) {
		this.ms_code = ms_code;
	}

	public String getDev_code() {
		return dev_code;
	}

	public void setDev_code(String dev_code) {
		this.dev_code = dev_code;
	}

	public Integer getDev_type() {
		return dev_type;
	}

	public void setDev_type(Integer dev_type) {
		this.dev_type = dev_type;
	}

	public Integer getDev_factory() {
		return dev_factory;
	}

	public void setDev_factory(Integer dev_factory) {
		this.dev_factory = dev_factory;
	}

	public Date getData_time() {
		return data_time;
	}

	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}

	public Integer getDev_status() {
		return dev_status;
	}

	public void setDev_status(Integer dev_status) {
		this.dev_status = dev_status;
	}

    public String getMs_code_value() {
        return ms_code_value;
    }

    public void setMs_code_value(String ms_code_value) {
        this.ms_code_value = ms_code_value;
    }

    public String getController_ms_code() {
        return controller_ms_code;
    }

    public void setController_ms_code(String controller_ms_code) {
        this.controller_ms_code = controller_ms_code;
    }
	
}
