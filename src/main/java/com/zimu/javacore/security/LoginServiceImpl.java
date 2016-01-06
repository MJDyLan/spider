package com.zimu.javacore.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
		return YirendaiConstants.URL+YirendaiConstants.LOGIN;
	}
	@Override
	public void buildLoginParam(String account, String password) {
		requestMap.put("account", CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY, account));
		requestMap.put("password",  CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY,password));
	}

	@Override
	public void bulidRequestParam() {
		requestMap.put("channelId", YirendaiConstants.CHANNELID);
		requestMap.put("clientType", YirendaiConstants.clientType);
		requestMap.put("deviceNo", YirendaiConstants.deviceNo);
		requestMap.put("marketId", YirendaiConstants.marketId);
		requestMap.put("secret", YirendaiConstants.secret);
		MapUtils.sortMapByKey(requestMap);
		requestMap.put("sigin", MD5Utils.encode(MapUtils.getParamStringFromMap(requestMap)));
		MapUtils.encodeValues(requestMap);
	}

}
