package com.zimu.spider.gf.web.service;

import java.util.Map;
import java.util.Scanner;

import com.zimu.javacore.file.MyFileUtils;
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
		
		spider.doLogin("031100011802", "681376", verifyCode);
		String url = "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		HttpResponse response2 = HttpGetUtils.sendGet(url);
		
		System.err.println(response2.getResponseBody());
	}
}
