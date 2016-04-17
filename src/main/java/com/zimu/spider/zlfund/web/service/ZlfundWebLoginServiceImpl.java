package com.zimu.spider.zlfund.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.HttpCookieUtils;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

public class ZlfundWebLoginServiceImpl implements ZlfundWebLoginService {
	private String csrftoken = StringUtils.EMPTY;
	private Map<String, Object> header = new HashMap<String, Object>();
	
	@Override
	public void doBefore() {
		String url = "https://www.zlfund.cn/accounts/login/";
		HttpGetUtils.sendGetStrReq(url, true, true);
		this.csrftoken = HttpCookieUtils.getCookieByKey("csrftoken");
	}
	
	@Override
	public void addHeader() {
		header.put("Referer", "https://www.zlfund.cn/");
	}

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
		requestMap.put("identity", username);
		requestMap.put("password", password);
		requestMap.put("csrfmiddlewaretoken", csrftoken);
	}
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}
	
	@Override
	public String doLogin(String username, String password, String authcode) {
		//登录之前的操作
		addHeader();
		doBefore();
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(),MapUtils.getParamStringEncoder(requestMap), true,true,header);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	public static void main(String[] args){ 
		ZlfundWebLoginService login = new ZlfundWebLoginServiceImpl();
		login.doLogin("429004198902172759", "qiujisheng89", "");
		ZlfundWebAccountInfoService account = new ZlfundWebAccountInfoServiceImpl();
		account.doAccount();
	}
}
