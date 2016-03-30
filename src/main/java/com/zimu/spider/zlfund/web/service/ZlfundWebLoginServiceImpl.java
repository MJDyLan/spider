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
		String url = "https://login.yirendai.com/auth/tologin?redirectURI=http%3A%2F%2Fwww.yirendai.com%2F%3Futm_source%3Dbd-pc-pz%26utm_medium%3DSEM%26utm_campaign%3D%3F%3F%3F%3F%26utm_content%3D%3F%3F%3F%3F%26utm_term%3D%3F%3F%26utm_channel%3D1%26utm_cparameters%3D%7Bzh%3Dbaidupz%2Ctime%3D20160127%7D";
		HttpGetUtils.sendGetStrReq(url, true, true);
		System.out.println(CookieManager.getCookie());
	}
}
