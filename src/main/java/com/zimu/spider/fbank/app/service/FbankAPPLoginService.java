package com.zimu.spider.fbank.app.service;

import java.util.Map;

public interface FbankAPPLoginService {
	
    public String getUrl();
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);

	public String doLogin(String username,String password,String authcode);
	
	public String doLogin(String username,String password);

}
