package com.zimu.spider.jimubox.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

public class JimuboxWebLoginServiceImpl implements JimuboxWebLoginService {

	@Override
	public String getLoginUrl() {
		return "https://passport.jimubox.com/authentication/login?redirectUrl=https://www.jimu.com/User/AssetOverview";
	}
	
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		this.buildLoginParam(requestMap, username, password,StringUtils.EMPTY);
		
	}
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("username", username);
		requestMap.put("password", password);
		requestMap.put("agreeContract", "on");
		requestMap.put("site", "B662B0F090BE31C1DCB6A13D70E81429");
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
	public static void main(String[] args) throws UnsupportedEncodingException {
		JimuboxWebLoginService s = new JimuboxWebLoginServiceImpl();
		s.doLogin("feidee", "feidee", "");
		System.err.println(HttpGetUtils.sendGetStrReq("https://www.jimu.com/User/AssetOverview/Revenues?_=1460029385260",true,true));
	}
}
