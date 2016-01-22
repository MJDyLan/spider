/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.zimu.spider.touna.web.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.security.MD5Utils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.touna.web.constant.TounaWebConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("TounaWebLoginService")
public class TounaWebLoginServiceImpl implements TounaWebLoginService{

	
	@Override
	public String getUrl() {
		return TounaWebConstant.LOGIN_URL;
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
		String result = "";
		try {
			result = HttpPostUtils.sendPostReq(getUrl(), MapUtils.getParamStringEncoder(requestMap), true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		//6c09d194a1dbe43f4df522546e6c2d8b
		System.out.println(MD5Utils.encode("qiujisheng89"));
	}
}
