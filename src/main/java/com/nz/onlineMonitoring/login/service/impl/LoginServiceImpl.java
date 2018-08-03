package com.nz.onlineMonitoring.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nz.onlineMonitoring.login.mapper.LoginMapper;
import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.login.service.LoginService;

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

    @Override
    public String mdiPassword(Login login) {
        int n = loginMapper.mdiPassword(login);
        if (n < 0) {
            throw new RuntimeException("密码修改失败");
        }
        return "密码修改成功";
    }
}
