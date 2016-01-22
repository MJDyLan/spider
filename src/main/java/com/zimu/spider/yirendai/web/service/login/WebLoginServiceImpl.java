package com.zimu.spider.yirendai.web.service.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.yirendai.web.constant.YirendaiWebConstants;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
@Service("webLoginService")
public class WebLoginServiceImpl implements WebLoginService {

	private static final Logger logger = LoggerFactory.getLogger(WebLoginServiceImpl.class);
	
	@Override
	public String getUrl() {
		return YirendaiWebConstants.LOGIN_SUBMIT_URL;
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		return doLogin(username, password, authcode, false);
	}

	@Override
	public void generateAuthCode(){
		String authcode_url = YirendaiWebConstants.AUTH_CODE_URL;
		HttpGetUtils.sendGetFileReq(authcode_url, true);
        System.err.println("请求完验证码的cookie="+CookieManager.getCookie());
	}
	@Override
	public void buildLoginParam(Map<String, Object> requestMap, String username,
			String password,String authcode) {
		requestMap.put("username", username);
	    requestMap.put("password", password);
	    requestMap.put("authcode", authcode);
	    requestMap.put("fromSite", "YRD");
	    requestMap.put("redirectURI", "https://www.yirendai.com/");
	    requestMap.put("rememberMe", "0");
	}
	
	@Override
	public String doLogin(String username, String password, String authcode,boolean needCookie) {
		
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap, username, password, authcode);
		String result = "";
		try {
			result = HttpPostUtils.sendPostReq(getUrl(), MapUtils.getParamStringEncoder(requestMap), true, true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public String getCmsHeaderInfo() {
		try {
			return HttpGetUtils.sendGetStrReq(YirendaiWebConstants.CMSHEADER_URL, false, true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
