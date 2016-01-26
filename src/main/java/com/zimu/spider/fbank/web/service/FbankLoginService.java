package com.zimu.spider.fbank.web.service;

import java.util.Map;

public interface FbankLoginService {
	
    public String getUrl();
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);

	public String doLogin(String username,String password,String authcode);
	
	public String doLogin(String username,String password);

}
