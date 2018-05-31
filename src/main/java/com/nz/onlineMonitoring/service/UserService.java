package com.nz.onlineMonitoring.service;

import com.nz.onlineMonitoring.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List userList(){

		List userList = userMapper.userList();
		return userList;
	}
}
