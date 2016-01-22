/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
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
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.touna.web.constant.TounaWebConstant;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
@Service("tounaAccountService")
public class TounaAccountServiceImpl implements TounaAccountService {

	
	@Override
	public String getUrl() {
		return TounaWebConstant.ONESHOP_URL;
	}

	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("method", "loadAccountInfo");
		requestMap.put("subtime", new Date().getTime());
	}

	@Override
	public String doOneshop() {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result = "";
		try {
			result = HttpPostUtils.sendPostReq(getUrl(), MapUtils.getParamStringEncoder(requestMap), true, true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
