/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.zimu.spider.touna.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.security.MD5Utils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("tounaWebLoginService")
public class TounaWebLoginServiceImpl implements TounaWebLoginService{

	
	@Override
	public String getLoginUrl() {
		return WebUrlConstant.TOUNA_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,
			String username, String password, String authcode) {
		requestMap.put("method", "login");
		requestMap.put("username", username);
		requestMap.put("md5Pwd", MD5Utils.encode(password));
		requestMap.put("valicode", authcode);
		requestMap.put("subtime", new Date().getTime());
	}

	@Override
	public String doLogin(String username,String password,String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), MapUtils.getParamStringEncoder(requestMap), true);
		return result;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
}
