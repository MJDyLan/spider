/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:01
 * @version 1.0
 */
package com.zimu.spider.dianrong.web.service;

import java.util.Map;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:01
 * @version 1.0
 */
public interface DianrongAccountService {
	
	public String getUrl();
	
	public void buildRequestParam(Map<String,Object> requestMap);
	
	public String doAccount();

}
