package com.nz.onlineMonitoring.login.model;

public class Login {
	//账号
	private Integer account;
	//密码
	private String password;
	//城市名字
	private String data_name;
	//是否删除；
	private Boolean dr;

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getData_name() {
		return data_name;
	}

	public void setData_name(String data_name) {
		this.data_name = data_name;
	}

	public Boolean getDr() {
		return dr;
	}

	public void setDr(Boolean dr) {
		this.dr = dr;
	}
}
