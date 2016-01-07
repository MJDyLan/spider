/**
*@author  jisheng
*@time 2016年1月7日下午10:42:17
*/
package com.zimu.spider.yirendai.service.account;

import java.util.Map;

import com.zimu.spider.yirendai.service.base.BaseService;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日下午10:42:17
 * @version 1.0
 */
public interface AccountInfoService extends BaseService{
	
	public String doAccountInfo();
	
	public void bulidRequestParam(Map<String,Object> requestMap);
}
