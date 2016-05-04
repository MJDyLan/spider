package com.zimu.spider.gf.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.zimu.javacore.file.MyFileUtils;
import com.zimu.javacore.http.ConstHttp;
import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpCookieUtils;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.spider.base.inter.BaseLoginService;
import com.zimu.spider.gf.web.constant.GfWebConstant;

/**
 * 
 * @title 广发证券
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月27日
 * @version 1.0
 */
public final class GfWebLoginService extends BaseLoginService<String>{

	private static final String LOGIN_URL = GfWebConstant.LOGIN_URL;
	private static final String LOGIN_VERIFYCODE_URL = GfWebConstant.LOGIN_VERIFYCODE_URL;
	private static final String LOGIN_ACTION_URL = GfWebConstant.LOGIN_ACTION_URL;

	@Override
	public String getLoginUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,
			String username, String password, String authcode) {
		requestMap.put("error_url", "https://store.gf.com.cn/login.html");
		requestMap.put("login_type", "trade");
		requestMap.put("user_id", username);
		requestMap.put("password", password);
		requestMap.put("ticket", authcode);		
	}
	
	public static void main(String[] args) {
		GfWebLoginService spider = new GfWebLoginService();
		
    	String url333= "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		Map<String,Object> requestHeader21 = new HashMap<String, Object>();
		requestHeader21.put(ConstHttp.REFERER, "http://new.gf.com.cn/");
		requestHeader21.put(ConstHttp.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		HttpResponse response333= HttpGetUtils.sendGet(url333,requestHeader21);
		System.err.println(response333.getResponseBody());
		
		Map<String,Object> requestHeader22 = new HashMap<String, Object>();
		requestHeader22.put(ConstHttp.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		HttpResponse response = HttpGetUtils.sendGet(LOGIN_VERIFYCODE_URL,requestHeader22);
		MyFileUtils.saveImageToDisk(response.getIn(),"E:\\verifyCode.png");
		System.err.println("请输入验证码：");
		Scanner scanner = new Scanner(System.in);
		String verifyCode = scanner.nextLine();
		System.err.println(verifyCode);
		Map<String,Object> requestHeader = new HashMap<String, Object>();
		requestHeader.put(ConstHttp.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		spider.buildHeader(requestHeader);
		spider.doLogin("031100011802", "681376", verifyCode);
		
		String url22 = "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		Map<String,Object> requestHeader23 = new HashMap<String, Object>();
		requestHeader23.put(ConstHttp.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		spider.buildHeader(requestHeader23);
		HttpResponse response22 = HttpGetUtils.sendGet(url22,requestHeader23);
		System.err.println(response22.getResponseBody());
		
		String url33= "https://store.gf.com.cn/rest/user/asset/total_value";
		Map<String,Object> requestHeader2 = new HashMap<String, Object>();
		requestHeader2.put(ConstHttp.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		HttpResponse response33= HttpGetUtils.sendGet(url33,requestHeader2);
		System.err.println(response33.getResponseBody());
	}
}
