package com.zimu.spider.zlfund.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

public class ZlfundWebLoginServiceImpl implements ZlfundWebLoginService {

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.ZLFUND_LOGIN_URL;
	}
	
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		this.buildLoginParam(requestMap, username, password,StringUtils.EMPTY);
		
	}
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("userName", username);
		requestMap.put("password", password);
		requestMap.put("returnUrl", "");
	}
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}
	
	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), MapUtils.getParamStringEncoder(requestMap), true);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	public static void main(String[] args) {
		String url = "https://www.zlfund.cn/accounts/login/";
		HttpGetUtils.sendGetStrReq(url, true, true);
		System.out.println(CookieManager.getCookie());
		String token = "";
		String cookie = CookieManager.getCookie();
		String[] cookieArr = cookie.split(";");
		for (int i = 0; i < cookieArr.length; i++) {
			if(cookieArr[i].contains("token")){
				token = cookieArr[i].split("=")[1];
			}
		}
		Map<String,Object> requestMap = new HashMap<String, Object>();
		requestMap.put("identity", "429004198902172759");
		requestMap.put("password", "qiujisheng89");
		requestMap.put("csrfmiddlewaretoken", token);
		String result = HttpPostUtils.sendPostReq(url, MapUtils.getParamStringEncoder(requestMap), true);
		String url2 = "https://www.zlfund.cn/accounts/profile/?_=1459346893323";
		String result2 = HttpGetUtils.sendGetStrReq(url2, true, true);
		System.out.println(result2);
	}
}
