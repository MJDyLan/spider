package com.zimu.spider.fbank.web.service;

import java.util.Map;

import com.zimu.spider.base.constant.WebUrlConstant;
import com.zimu.spider.base.inter.BaseLoginService;

public class FbankWebLoginServiceImpl extends BaseLoginService<String> implements FbankWebLoginService {

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.FBANK_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("userName", username);
		requestMap.put("password", password);
		requestMap.put("returnUrl", "");		
	}
}
