package com.nz.onlineMonitoring.view.model;

import com.nz.onlineMonitoring.dict.model.Dict;

import java.util.Date;

public class VRealData {

	private String ms_code;
	private String dev_code;
	private Date data_time;
	private Integer dev_status;

	private Dict dataDevStatus;

	public Dict getDataDevStatus() {
		return dataDevStatus;
	}

	public void setDataDevStatus(Dict dataDevStatus) {
		this.dataDevStatus = dataDevStatus;
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
}
