package com.zimu.javacore.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
public class LoginServiceImpl implements LoginService {
	private Map<String,Object> requestMap = new HashMap<String, Object>();

	@Override
	public String doLogin(String account, String password) {
		buildLoginParam(account, password);
		bulidRequestParam();
		String result = "";
		try {
			result = HttpUtils.sendPost(getLoginUrl(), MapUtils.getParamStringFromMap(requestMap));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getLoginUrl(){
		return YirendaiConstants.LOGIN_URL;
	}
	@Override
	public void buildLoginParam(String account, String password) {
		requestMap.put("account", CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY, account));
		requestMap.put("password",  CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY,password));
	}

	@Override
	public void bulidRequestParam() {
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("deviceNo", YirendaiConstants.KEY_DEVICE_NO);
		requestMap.put("marketId", YirendaiConstants.KEY_MARKET_ID);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);
		MapUtils.sortMapByKey(requestMap);
		requestMap.put("sigin", MD5Utils.encode(MapUtils.getParamStringFromMap(requestMap)));
		MapUtils.encodeValues(requestMap);
	}

}
