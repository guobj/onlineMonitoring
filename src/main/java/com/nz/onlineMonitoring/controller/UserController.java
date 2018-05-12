package com.nz.onlineMonitoring.controller;

import com.nz.onlineMonitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user")
	public String userList(){

		List list = userService.userList();

		System.out.println(list);
		
		System.out.println("测试");
		return "user";
	}
}
