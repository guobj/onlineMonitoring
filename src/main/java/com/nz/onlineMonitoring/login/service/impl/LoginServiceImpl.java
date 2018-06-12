package com.nz.onlineMonitoring.login.service.impl;

import com.nz.onlineMonitoring.login.mapper.LoginMapper;
import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public Login login(Login login) {
		Login loginUser = loginMapper.login(login);
		if(loginUser == null){
			throw new RuntimeException("用户名或密码错误");
		}
		return loginUser;
	}
}
