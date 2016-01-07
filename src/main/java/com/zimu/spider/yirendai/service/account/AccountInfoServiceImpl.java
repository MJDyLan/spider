/**
*@author  jisheng
*@time 2016年1月7日下午10:42:35
*/
package com.zimu.spider.yirendai.service.account;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.spider.yirendai.constant.YirendaiConstants;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日下午10:42:35
 * @version 1.0
 */
public class AccountInfoServiceImpl implements AccountInfoService {

	
	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.account.AccountInfoService#doAccountInfo()
	 */
	@Override
	public String doAccountInfo() {
		Map<String, Object> requestMap  = new HashMap<String, Object>();
		bulidRequestParam(requestMap);
		String result = "";
		try {
			result = HttpUtils.sendPost(getUrl(), MapUtils.getParamStringEncoder(requestMap));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.account.AccountInfoService#bulidRequestParam(java.util.Map)
	 */
	@Override
	public void bulidRequestParam(Map<String, Object> requestMap) {
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);	
		//这里再请求其他接口的时候，是否需要重新请求登录接口，获取最新的token
		requestMap.put("token", "c86a33c8b1594a1aa6fc4cd7572a3e17");
		requestMap.put("sign", "40a689feb44a7b4a5b5383c647fbe96e");
	}
	public static void main(String[] args) {
		AccountInfoServiceImpl accountService = new AccountInfoServiceImpl();
		System.out.println(accountService.doAccountInfo());
	}

	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.base.BaseService#getUrl()
	 */
	@Override
	public String getUrl() {
		return YirendaiConstants.ACCOUNT_URL;
	}
}
