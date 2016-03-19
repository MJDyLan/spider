package com.zimu.spider.niwodai.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

public class NiwodaiWebLoginServiceImpl implements NiwodaiWebLoginService {

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.NIWODAI_LOGIN__URL;
	}
	
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		this.buildLoginParam(requestMap, username, password,StringUtils.EMPTY);
		
	}
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("username", username);
		requestMap.put("pwd", password);
		requestMap.put("codeType", "0");
		requestMap.put("geetestCode", "geetest");
		requestMap.put("geetest_challenge", "");
		requestMap.put("geetest_validate", "");
		requestMap.put("geetest_seccode", "");
		requestMap.put("channel", "3");
		requestMap.put("imgCode", "");
		requestMap.put("phoneCode", "");
	}
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}
	
	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		Map<String,Object> header = new HashMap<String, Object>();
		buildHeader(header);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), MapUtils.getParamStringEncoder(requestMap), true,false,header);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	public static void main(String[] args) throws Exception {
		NiwodaiWebLoginService login = new NiwodaiWebLoginServiceImpl();
		
		System.err.println(login.doLogin("13349910969", "qiujisheng89", ""));
		
		
		String url = "https://member.niwodai.com/member/investorsAjax.do";
		System.err.println(HttpGetUtils.sendGetStrReq(url, true, true));
	}

	@Override
	public void buildHeader(Map<String, Object> headerMap) {
		headerMap.put("Referer", "https://member.niwodai.com/login.html");
	}
}
