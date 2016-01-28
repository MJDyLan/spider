package com.zimu.spider.fbank.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.zimu.javacore.http.CookieManager;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.security.DesUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.fbank.web.constant.FbankWebConstant;

public class FbankAPPLoginServiceImpl implements FbankAPPLoginService {

	@Override
	public String getUrl() {
		return FbankWebConstant.LOGIN_APP_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> map,String username, String password, String authcode) {
		     map.put("mobile", "13349910969");
	         map.put("password", "qiujisheng89");
	         map.put("version", 241);
	         map.put("platform", "android");
	         map.put("channel", "guanwang");
	         map.put("deviceId", "0");
	         map.put("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
	         map.put("requestMs", String.valueOf(System.currentTimeMillis()));
	         map.put("td_deviceId", "unknow");
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password,authcode);
		
		String result = "";
		try {
			JSONObject object = new JSONObject(requestMap);
			String params = "cipherData="+ HttpUtils.encodeUrlProperty(DesUtils.encode(object.toString()));
			result = HttpPostUtils.sendPostReq(getUrl(), params, false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		FbankAPPLoginService f = new FbankAPPLoginServiceImpl();
		f.doLogin("13349910969", "qiujisheng89");		
	}
}
