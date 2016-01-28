package com.zimu.spider.fbank.web.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.fbank.web.constant.FbankWebConstant;

public class FbankLoginServiceImpl implements FbankLoginService {

	@Override
	public String getUrl() {
		return FbankWebConstant.LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("userName", username);
		requestMap.put("password", password);
		requestMap.put("page", "gw");
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		String result = "";
		try {
			result = HttpPostUtils.sendPostReq(getUrl(), MapUtils.getParamStringEncoder(requestMap), true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println(result);
		return result;
	}

	@Override
	public String doLogin(String username, String password) {
		return this.doLogin(username, password, StringUtils.EMPTY);
	}

	public static void main(String[] args) {
		FbankLoginService f = new FbankLoginServiceImpl();
		f.doLogin("13349910969", "qiujisheng89");		
	}
}
