package com.zimu.spider.yirendai.web.service.login;

import com.zimu.spider.base.inter.IBaseLoginSevice;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:31
 * @version 1.0
 */
public interface YirendaiWebLoginService extends IBaseLoginSevice<String>{
	
	public void generateAuthCode();
	
	public String getCmsHeaderInfo();
}