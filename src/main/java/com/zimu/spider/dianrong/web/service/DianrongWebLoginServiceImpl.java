/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.zimu.spider.dianrong.web.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.spider.base.constant.WebUrlConstant;
import com.zimu.spider.base.inter.BaseLoginService;
import com.zimu.spider.dianrong.web.model.DianrongWebLoginModel;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("dianrongWebLoginService")
public class DianrongWebLoginServiceImpl extends BaseLoginService<DianrongWebLoginModel> implements DianrongWebLoginService{
	@Override
	public String getLoginUrl() {
		return WebUrlConstant.DIANRONG_LOGIN_URL;
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public DianrongWebLoginModel toModel(String resultStr) {
		return DianrongWebLoginModel.getInstanceByJson(resultStr);

	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("identity", username);
		requestMap.put("password", password);	
	}
	
	public static void main(String[] args) {
		DianrongWebLoginServiceImpl loginService = new DianrongWebLoginServiceImpl();
		System.err.println(loginService.doLogin("13349910969", "qiujisheng89", ""));
		String url ="https://www.dianrong.com/api/v2/user/profile";
		System.out.println(HttpGetUtils.sendGetStrReq(url, true, true));
		
	}
}
