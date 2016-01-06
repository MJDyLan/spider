package com.zimu.javacore.security;

public interface LoginService {
	
	public void buildLoginParam(String account,String password);
	
	public String doLogin(String account,String password);
	
	public void bulidRequestParam();

}
