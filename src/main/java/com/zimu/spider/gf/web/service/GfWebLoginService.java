package com.zimu.spider.gf.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.zimu.javacore.file.MyFileUtils;
import com.zimu.javacore.http.ConstHttp;
import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpCookieUtils;
import com.zimu.javacore.http.HttpGetUtils;
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
		HttpResponse response = HttpGetUtils.sendGet(LOGIN_VERIFYCODE_URL);
		MyFileUtils.saveImageToDisk(response.getIn(),"E:\\verifyCode.png");
		System.err.println("请输入验证码：");
		Scanner scanner = new Scanner(System.in);
		String verifyCode = scanner.nextLine();
		System.err.println(verifyCode);
		Map<String,Object> requestHeader = new HashMap<String, Object>();
		requestHeader.put(ConstHttp.REFERER, "https://store.gf.com.cn/portal/index?_gfsrc=newgfw_x_x_home");
		requestHeader.put(ConstHttp.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		spider.buildHeader(requestHeader);
		spider.doLogin("031100011802", "681376", verifyCode);
		
		String url = "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		HttpResponse response2 = HttpGetUtils.sendGet(url,requestHeader);
		
		System.err.println(response2.getResponseBody());
		Map<String,Object> requestHeader2 = new HashMap<String, Object>();
		requestHeader2.put(ConstHttp.REFERER, "https://store.gf.com.cn/mygf/asset");
		requestHeader2.put(ConstHttp.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		String url3= "https://store.gf.com.cn/rest/user/asset/total_value";
		HttpResponse response3 = HttpGetUtils.sendGet(url3,requestHeader2);
		
		System.err.println(response3.getResponseBody());
	}
}
