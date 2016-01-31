package com.zimu.spider.yirendai.app.service.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.security.CryptAES;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.javacore.utils.SignUtils;
import com.zimu.spider.base.constant.AppUrlConstant;
import com.zimu.spider.yirendai.app.constant.YirendaiConstants;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
@Service("yirendaiLoginService")
public class YirendaiAppLoginServiceImpl implements YirendaiAppLoginService {
	@Override
	public String getLoginUrl() {
		return AppUrlConstant.YIRENDAI_LOGIN_URL;
	}

	/**
	 * {"result":"success","errorCode":"","msg":"","data":{"passportId":"","token":"c86a33c8b1594a1aa6fc4cd7572a3e17","name":"*吉胜","accountStatus":"1","accountType":"1","accountTypeChangeable":"","accountBalance":"0","totalAmount":"","accumulatedIncome":"","rates":"0%","isNew":"","level":"000","yrbRatio":"","inviteCodeRewardCount":"","newUserRewardCount":""}}
	 */
	public String doLogin(String username, String password) {
		return this.doLogin(username, password, StringUtils.EMPTY);
	}

	@Override
	public void buildLoginParam(Map<String,Object> requestMap,String username, String password) {
		this.buildLoginParam(requestMap, username, password, StringUtils.EMPTY);
	}
	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("deviceNo", YirendaiConstants.KEY_DEVICE_NO);
		requestMap.put("marketId", YirendaiConstants.KEY_MARKET_ID);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);
		requestMap.put("sign", SignUtils.generateSign(requestMap));		
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("account", CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY, username));
		requestMap.put("password",  CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY,password));
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password);
		buildRequestParam(requestMap);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), MapUtils.getParamStringEncoder(requestMap),true);
	
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	
	public static void main(String[] args) {
		YirendaiAppLoginServiceImpl login  = new YirendaiAppLoginServiceImpl();
		String account = "13509692759";
		String password = "qiujisheng89";
		
		System.out.println(login.doLogin(account, password));
	}


}
