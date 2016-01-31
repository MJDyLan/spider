package com.zimu.spider.base.inter;

import java.util.Map;

public interface IBaseLoginSevice<T> {
	
	public String getLoginUrl();
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password);
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);
	
	public void buildRequestParam(Map<String,Object> requestMap);
	
	public String doLogin(String username,String password,String authcode);
	
	public T toModel(String resultStr);
}
