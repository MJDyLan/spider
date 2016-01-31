package com.zimu.spider.fbank.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.security.DesUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.AppUrlConstant;
import com.zimu.spider.fbank.app.constant.FBankAppConstant;

public class FbankAPPLoginServiceImpl implements FbankAPPLoginService {
	private static Logger logger = LoggerFactory.getLogger(FbankAPPLoginServiceImpl.class);

	@Override
	public String getLoginUrl() {
		return AppUrlConstant.FBANK_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		this.buildLoginParam(requestMap, username, password);
		
	}
	
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mobile", username);
	    map.put("password", password);
	    map.put("version", 241);
	    map.put("platform", "android");
	    map.put("channel", "guanwang");
	    map.put("deviceId", "0");
	    map.put("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
	    map.put("requestMs", String.valueOf(System.currentTimeMillis()));
	    map.put("td_deviceId", "unknow");
	    
		JSONObject object = new JSONObject(map);
		String desStr ="";
		try {
			 desStr =  HttpUtils.encodeUrlProperty(DesUtils.encode(object.toString(),FBankAppConstant.iv,FBankAppConstant.secretKey));
		} catch (Exception e) {
			logger.error("DES加密APP登录参数失败",e);
		}
		requestMap.put("cipherData", desStr);
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

}
