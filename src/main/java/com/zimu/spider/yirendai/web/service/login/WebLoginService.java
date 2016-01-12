package com.zimu.spider.yirendai.web.service.login;

import java.util.Map;

import com.zimu.spider.yirendai.app.service.base.BaseService;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:31
 * @version 1.0
 */
public interface WebLoginService extends BaseService{
	
	public String doLogin(String username,String password,String authcode);
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);


}
