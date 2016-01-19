package com.zimu.spider.yirendai.web.service.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.utils.JsonMapper;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.yirendai.app.constant.YirendaiConstants;
import com.zimu.spider.yirendai.app.model.AccountUserInfoResp;
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

	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.base.BaseService#getUrl()
	 */
	@Override
	public String getUrl() {
		return YirendaiWebConstants.LOGIN_SUBMIT_URL;
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap, username, password, authcode);
		String result = "";
		try {
			result = HttpUtils.sendPost(getUrl(), MapUtils.getParamStringEncoder(requestMap));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap, String username,
			String password,String authcode) {
		requestMap.put("username", username);
	    requestMap.put("password", password);
	    requestMap.put("authcode", authcode);
	    requestMap.put("fromSite", "YRD");
	    requestMap.put("redirectURI", "http://www.yirendai.com/");
	    requestMap.put("rememberMe", "rememberMe");
	}
}
