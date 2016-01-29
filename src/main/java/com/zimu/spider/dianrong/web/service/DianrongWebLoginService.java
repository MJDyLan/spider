/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:22
 * @version 1.0
 */
package com.zimu.spider.dianrong.web.service;

import java.util.Map;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:22
 * @version 1.0
 */
public interface DianrongWebLoginService {
	
	public String getUrl();
	
	public void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);

	public String doLogin(String username,String password);
	public String doLogin(String username,String password,String authcode);

}
