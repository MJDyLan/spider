package com.zimu.spider.yirendai.web.constant;

/**
 * 
 * @title 宜人贷理财官网抓取常量
 * 
 * @author JasonChiu
 * @time 2016年1月11日下午10:52:36
 * @version 1.0
 */
public final class YirendaiWebConstants {
	//验证码url：https://login.yirendai.com/auth/captcha.random 
	public static String BASE_LOGIN_URL = "https://login.yirendai.com";
	public static String AUTH_CODE_URL = BASE_LOGIN_URL +"/auth/captcha.random";
	public static String LOGIN_SUBMIT_URL = BASE_LOGIN_URL+ "/auth/loginsubmit";
	
	public static String BASE_URL = "https://www.yirendai.com";
	public static String CMSHEADER_URL = BASE_URL+"/home/cmsHeaderInfo";
	
	
	
}
