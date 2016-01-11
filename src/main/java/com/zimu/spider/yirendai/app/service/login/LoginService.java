package com.zimu.spider.yirendai.app.service.login;

import java.util.Map;

import com.zimu.spider.yirendai.app.service.base.BaseService;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:31
 * @version 1.0
 */
public interface LoginService extends BaseService{
	
	public void buildLoginParam(Map<String,Object> requestMap,String account,String password);
	
	public String doLogin(String account,String password);
	
	public void bulidRequestParam(Map<String,Object> requestMap);

}
