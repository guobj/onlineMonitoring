package com.nz.onlineMonitoring.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.login.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public String login(){
		return "login/login";
	}

	@RequestMapping("/loginDo")
	public String loginDo(HttpServletRequest request, Map<String, Object> map, Login login){
		HttpSession session = request.getSession();
		String url = "login/login";
		try {
			Login loginUser = loginService.login(login);
			session.setAttribute("user", loginUser);
			url = "redirect:/station/listStation";
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		return url;
	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "login/login";
	}
}
