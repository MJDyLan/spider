package com.zimu.spider.yirendai.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zimu.spider.yirendai.app.model.UserModel;
import com.zimu.spider.yirendai.app.service.account.YirendaiAppAccountService;
import com.zimu.spider.yirendai.app.service.login.YirendaiAppLoginService;
import com.zimu.spider.yirendai.app.service.myorder.YirendaiAppMyOrderListService;
import com.zimu.spider.yirendai.web.service.login.YirendaiWebLoginService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private YirendaiAppLoginService loginService;
	@Autowired
	private YirendaiAppAccountService accountInfoService;
	@Autowired
	private YirendaiAppMyOrderListService myOrderListService;
	@Autowired
	private YirendaiWebLoginService webLoginService;
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "yirendai/login";
	}
	@RequestMapping("doLogin")
	public ModelAndView doLogin(@ModelAttribute("userModel") UserModel userModel){
		String account = userModel.getAccount();
		String password = userModel.getPassword();
		
		String login_result = loginService.doLogin(account, password,StringUtils.EMPTY);
		
		ModelAndView modelAndView = new ModelAndView("yirendai/account");  
	    modelAndView.addObject("login_result", login_result);  
	    return modelAndView;  
	}
	@RequestMapping("toWebLogin")
	public ModelAndView toWebLogin(){
		ModelAndView modelAndView = new ModelAndView("yirendai/weblogin");
		//请求验证码，获取cookie，放入到本地，用于下一次请求
		webLoginService.generateAuthCode();
	    return modelAndView;  
	}
	@RequestMapping("doWebLogin")
	public ModelAndView doWebLogin(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("authcode") String authcode){
		webLoginService.doLogin(username, password, authcode);
        String cmsHeadInfo =  webLoginService.getCmsHeaderInfo();
        ModelAndView modelAndView = new ModelAndView("yirendai/webHead");
        modelAndView.addObject("cmsHeadInfo", cmsHeadInfo);
		return modelAndView;  
	}
}
