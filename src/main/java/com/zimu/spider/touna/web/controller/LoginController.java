/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:28:19
 * @version 1.0
 */
package com.zimu.spider.touna.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zimu.spider.touna.web.service.TounaWebLoginService;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:28:19
 * @version 1.0
 */
@Controller
@RequestMapping("touna")
public class LoginController {
	@Autowired
	private TounaWebLoginService tounaWebLoginService;
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "touna/login";
	}
	@RequestMapping("doLogin")
	public ModelAndView doLogin(@RequestParam("username") String username,@RequestParam("password") String password,
			@RequestParam("authcode") String authcode){
		ModelAndView model = new ModelAndView("touna/account");
		String result = tounaWebLoginService.doLogin(username, password, authcode);
		model.addObject("login_result", result);
		return model;
	}
}
