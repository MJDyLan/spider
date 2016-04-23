package com.zimu.spider.stock.ebscn.web.service;

import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.spider.base.inter.BaseLoginService;

public class EbscnWebLoginServiceImpl extends BaseLoginService<String> implements IEbscnWebLoginService<String> {

	
	
	@Override
	public void doBefore() {
	}

	@Override
	public String getLoginUrl() {
		return "";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap, String username, String password, String authcode) {

	}
	public static void main(String[] args) {
	
	}
}
