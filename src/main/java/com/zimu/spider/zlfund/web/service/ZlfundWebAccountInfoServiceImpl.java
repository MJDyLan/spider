package com.zimu.spider.zlfund.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;

public class ZlfundWebAccountInfoServiceImpl implements ZlfundWebAccountInfoService{
	private Map<String, Object> header = new HashMap<String, Object>();

	@Override
	public void addHeader() {
		header.put("Referer", "https://www.zlfund.cn/trade/");
	}
	@Override
	public String getAccountInfoUrl() {
		return "https://www.zlfund.cn/accounts/profile/?_=1460780588518";
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String doAccount() {
		addHeader();
		String result = HttpGetUtils.sendGetStrReq(getAccountInfoUrl(), true, true, header);		
		System.err.println(result);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
}

