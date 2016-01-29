/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.zimu.spider.dianrong.web.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.security.MD5Utils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.dianrong.web.constant.DianrongWebConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("dianrongWebLoginService")
public class DianrongWebLoginServiceImpl implements DianrongWebLoginService{

	private static  DianrongAccountService dianrongAccountService;
	
	@Override
	public String getUrl() {
		return DianrongWebConstant.LOGIN_URL;
	}


	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("identity", username);
		requestMap.put("password", password);
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
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		
		DianrongWebLoginService dianrong = new DianrongWebLoginServiceImpl();
		System.err.println(dianrong.doLogin("13349910969", "qiujisheng89"));
		String url = "https://www.dianrong.com/api/v2/user/profile";
		System.err.println(HttpGetUtils.sendGetStrReq(url, true, true));
	}

	@Override
	public String doLogin(String username, String password) {
		return this.doLogin(username, password,"");
	}
}
