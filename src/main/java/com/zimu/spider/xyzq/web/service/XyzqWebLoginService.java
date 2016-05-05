package com.zimu.spider.xyzq.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.zimu.javacore.file.MyFileUtils;
import com.zimu.javacore.http.ConstHttp;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.spider.base.inter.BaseLoginService;

/**
 * 
 * @title 兴业证券
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年5月5日
 * @version 1.0
 */
public final class XyzqWebLoginService extends BaseLoginService<String> {

	private static final String LOGIN_URL = XyzqWebConstant.LOGIN_URL;
	private static final String LOGIN_ACTION_URL = XyzqWebConstant.LOGIN_ACTION_URL;
	private static final String VERIFYCODE_URL = XyzqWebConstant.VERIFYCODE_URL;

	
	@Override
	public void doBefore() {
		
	}

	@Override
	public String getLoginUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put(ConstHttp.ACCEPT, ConstHttp.CONTENT_TYPE_JSON);
		requestMap.put(ConstHttp.REFERER, LOGIN_URL);
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("acct", username);
		requestMap.put("pwd", password);
		requestMap.put("imgcode", authcode);
	}
	public static void main(String[] args) {
		XyzqWebLoginService spider = new XyzqWebLoginService();
		Map<String,Object> header = new HashMap<String, Object>();
		HttpGetUtils.sendGet("http://www.xyzq.com.cn/xyzq/index.html");
		header.put("Referer",  "http://www.xyzq.com.cn/xyzq/index.html");
	    HttpGetUtils.sendGet(LOGIN_URL,header);
		HttpResponse response = HttpGetUtils.sendGet(VERIFYCODE_URL);
	    MyFileUtils.saveImageToDisk(response.getIn(),"E:\\verifyCode.png");
		System.err.println("请输入验证码：");
		Scanner scanner = new Scanner(System.in);
		String verifyCode = scanner.nextLine();
		System.err.println(verifyCode);
		spider.doLogin("12121212", "2121212", verifyCode);
		
	}
}
