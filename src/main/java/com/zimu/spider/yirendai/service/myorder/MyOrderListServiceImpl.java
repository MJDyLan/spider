/**
*@author  jisheng
*@time 2016年1月8日上午12:15:05
*/
package com.zimu.spider.yirendai.service.myorder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.javacore.utils.SignUtils;
import com.zimu.spider.yirendai.constant.YirendaiConstants;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月8日上午12:15:05
 * @version 1.0
 */
public class MyOrderListServiceImpl implements MyOrderListService {

	
	@Override
	public String getUrl() {
		return YirendaiConstants.MYORDER_URL;
	}

	/**
	 * {"result":"success","errorCode":"","msg":"","data":{"updateStatus":"","allCount":"1","pageTotal":"1","pageNo":"1","p2pserviceList":[{"finOrderNo":"15e415af661a45769e56bc163b41cd17","finOrderStatus":"已结束","productId":"0031","productType":"0","p2pserviceName":"宜定盈1月期","p2pserviceNo":"13","expectedIncome":"10","investDate":"2015-09-02","investAmount":"100","startCalcDate":"2015-09-05","incomeAmount":"0.83","expectedIncomeAmount":"0.83","expectedIncomeAmountNow":"0.00","frozenDate":"2015-10-05","frozenTime":"1","exitType":"退回到划扣银行卡","days":"0","exitNextOpt":"0","canApply":"2","nowDate":"2016-01-08"}]}}
	 */
	@Override
	public String doMyOrderList() {
		Map<String, Object> requestMap = new HashMap<String, Object>();
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

	
	@Override
	public void bulidRequestParam(Map<String, Object> requestMap) {
		requestMap.put("token", "c86a33c8b1594a1aa6fc4cd7572a3e17");
		requestMap.put("pageNo", "1");
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);

		//发现这个接口的签名与登录时的签名不一致，重新生成调用该接口的签名
		requestMap.put("sign", SignUtils.generateSign(requestMap));
	}

	public static void main(String[] args) {
		System.out.println(new MyOrderListServiceImpl().doMyOrderList());
		
	}
}
