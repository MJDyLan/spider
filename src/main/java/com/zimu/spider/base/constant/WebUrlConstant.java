package com.zimu.spider.base.constant;

public class WebUrlConstant {
	//=============================点融===========================//
	private static final String DIANRONG_BASE_URL = "https://www.dianrong.com";
	
	public static final String DIANRONG_LOGIN_URL = DIANRONG_BASE_URL+"/api/v2/users/loginsmart";
	
	public static final String DIANRONG_ACCOUNT_URL = DIANRONG_BASE_URL +"/api/v2/user/accountprofile";
 
	//=============================玖富============================//
	private static final String FBANK_BASE_URL= "https://8.9fbank.com";
	
	public static final String FBANK_LOGIN_URL = FBANK_BASE_URL + "/wlc2/doLogin.html";
	
	//==============================投哪网==========================//
	private static String TOUNA_BASE_URL = "https://www.touna.cn";
	
	public static String TOUNA_LOGIN_URL = TOUNA_BASE_URL + "/auth.do";
	
	public static String TOUNA_ONESHOP_URL = TOUNA_BASE_URL+ "/account.oneshop.do";

	//===============================宜人贷========================//
	//验证码url：https://login.yirendai.com/auth/captcha.random 
	private static String YIRENDAI_BASE_LOGIN_URL = "https://login.yirendai.com";
	public static String YIRENDAI_AUTH_CODE_URL = YIRENDAI_BASE_LOGIN_URL +"/auth/captcha.random";
	public static String YIRENDAI_LOGIN__URL = YIRENDAI_BASE_LOGIN_URL+ "/auth/loginsubmit";
	
	private static String YIRENDAI_BASE_URL = "https://www.yirendai.com";
	public static String YIRENDAI_CMSHEADER_URL = YIRENDAI_BASE_URL+"/home/cmsHeaderInfo";
}
