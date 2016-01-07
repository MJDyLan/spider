package com.zimu.javacore.security.yirendai.service;

import java.util.Map;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:31
 * @version 1.0
 */
public interface LoginService {
	
	public void buildLoginParam(Map<String,Object> requestMap,String account,String password);
	
	public String doLogin(String account,String password);
	
	public void bulidRequestParam(Map<String,Object> requestMap);

}
