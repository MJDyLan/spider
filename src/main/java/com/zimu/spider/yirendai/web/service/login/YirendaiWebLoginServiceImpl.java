package com.zimu.spider.yirendai.web.service.login;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.spider.base.constant.WebUrlConstant;
import com.zimu.spider.base.inter.BaseLoginService;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
@Service("yirendaiWebLoginService")
public class YirendaiWebLoginServiceImpl extends BaseLoginService<String> implements YirendaiWebLoginService {

	private static final Logger logger = LoggerFactory.getLogger(YirendaiWebLoginServiceImpl.class);
	
	@Override
	public String getLoginUrl() {
		return WebUrlConstant.YIRENDAI_LOGIN__URL;
	}

	@Override
	public void generateAuthCode(){
		String authcode_url = WebUrlConstant.YIRENDAI_AUTH_CODE_URL;
		HttpGetUtils.sendGetFileReq(authcode_url, true);
	}
	
	@Override
	public String getCmsHeaderInfo() {
		return HttpGetUtils.sendGetStrReq(WebUrlConstant.YIRENDAI_CMSHEADER_URL, false, true);
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("username", username);
	    requestMap.put("password", password);
	    requestMap.put("authcode", authcode);
		requestMap.put("fromSite", "YRD");
	    requestMap.put("redirectURI", "https://www.yirendai.com/");
	    requestMap.put("rememberMe", "0");		
	}
}
