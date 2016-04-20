/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
package com.zimu.spider.touna.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.base.constant.WebUrlConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
@Service("tounaWebAccountService")
public class TounaWebAccountServiceImpl implements TounaWebAccountService {

	@Override
	public String getAccountInfoUrl() {
		return WebUrlConstant.TOUNA_ONESHOP_URL;
	}

	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("method", "loadAccountInfo");
		requestMap.put("subtime", new Date().getTime());
	}

	@Override
	public String doAccount() {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result  = HttpPostUtils.sendPostReq(getAccountInfoUrl(), MapUtils.getParamStringEncoder(requestMap));

		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}

}
