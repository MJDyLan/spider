/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
package com.zimu.spider.dianrong.web.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpCookieUtils;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.dianrong.web.constant.DianrongWebConstant;
import com.zimu.spider.touna.web.constant.TounaWebConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
@Service("dianrongAccountService")
public class DianrongAccountServiceImpl implements DianrongAccountService {

	
	@Override
	public String getUrl() {
		return DianrongWebConstant.ACCOUNT_URL;
	}

	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String doAccount() {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result = "";
		try {
			result = HttpGetUtils.sendGetStrReq(getUrl(), true, true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
