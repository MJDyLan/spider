package com.zimu.spider.yirendai.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zimu.spider.yirendai.app.service.login.LoginService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	@RequestMapping("doLogin")
	public String doLogin(){
		return "account";
	}
}
