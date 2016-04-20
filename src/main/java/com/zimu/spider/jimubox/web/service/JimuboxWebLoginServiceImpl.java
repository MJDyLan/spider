package com.zimu.spider.jimubox.web.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.spider.base.inter.BaseLoginService;

public class JimuboxWebLoginServiceImpl extends BaseLoginService<String> implements JimuboxWebLoginService {
	private String site = StringUtils.EMPTY;

	@Override
	public void doBefore() {
	    String LOGIN_URL = "https://www.jimu.com/User/Login";
	    String loginHtml = HttpGetUtils.sendGet(LOGIN_URL).getResponseBody();
	    //取得登录表单url
	    //取得site值
	}

	@Override
	public String getLoginUrl() {
		return "https://passport.jimubox.com/authentication/login?redirectUrl=https://www.jimu.com/User/AssetOverview";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("username", username);
		requestMap.put("password", password);
		requestMap.put("agreeContract", "on");
		requestMap.put("site", site);
	}

	public static void main(String[] args)  {
		JimuboxWebLoginServiceImpl s = new JimuboxWebLoginServiceImpl();
		s.doLogin("feidee", "feidee", "");
	}
}
