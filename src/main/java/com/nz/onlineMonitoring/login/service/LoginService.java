package com.nz.onlineMonitoring.login.service;

import com.nz.onlineMonitoring.login.model.Login;

public interface LoginService {
    /**
     * 
     * 方法描述：登陆
     * @param login
     * @return
     * @author ssh
     * @date 2018年8月3日 下午2:42:35
     */
	Login login(Login login);
	
	/**
     * 
     * 方法描述：修改密码
     * @param login
     * @return
     * @author ssh
     * @date 2018年8月3日 下午2:41:48
     */
    String mdiPassword(Login login);
}
