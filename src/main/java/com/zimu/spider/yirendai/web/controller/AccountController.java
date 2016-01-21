package com.zimu.spider.yirendai.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zimu.spider.yirendai.app.model.UserModel;
import com.zimu.spider.yirendai.app.service.account.AccountInfoService;
import com.zimu.spider.yirendai.app.service.login.LoginService;
import com.zimu.spider.yirendai.app.service.myorder.MyOrderListService;
import com.zimu.spider.yirendai.web.constant.YirendaiWebConstants;
import com.zimu.spider.yirendai.web.service.login.WebLoginService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private MyOrderListService myOrderListService;
	@Autowired
	private WebLoginService webLoginService;
	
	
	private  static String cookieStr = "";
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	@RequestMapping("doLogin")
	public ModelAndView doLogin(@ModelAttribute("userModel") UserModel userModel){
		String account = userModel.getAccount();
		String password = userModel.getPassword();
		
		String login_result = loginService.doLogin(account, password);
		
		//String account_result = accountInfoService.doAccountInfo();
		ModelAndView modelAndView = new ModelAndView("account");  
	    modelAndView.addObject("login_result", login_result);  
	    return modelAndView;  
	}
	@RequestMapping("toWebLogin")
	public ModelAndView toWebLogin(){
		ModelAndView modelAndView = new ModelAndView("login2");  
		//生成验证码图片
		//String authImage = YirendaiWebConstants.AUTH_CODE_URL;
		//modelAndView.addObject("authImage", authImage); 
		//String result = webLoginService.toLogin();
		cookieStr = webLoginService.toLogin();
		modelAndView.addObject("cookieStr", cookieStr);  
	    return modelAndView;  
	}
	@RequestMapping("doWebLogin")
	public String doWebLogin(@RequestParam("username") String username,@RequestParam("password") String password,
			@RequestParam("authcode") String authcode){
		System.out.println(webLoginService.doLogin2(username, password, authcode,cookieStr));
	    return "account";  
	}
}
