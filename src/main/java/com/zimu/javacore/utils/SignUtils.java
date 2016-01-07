/**
 * 
 */
package com.zimu.javacore.utils;

import java.util.Map;

import com.zimu.javacore.security.MD5Utils;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月7日下午7:26:41
 * @version 1.0
 */
public class SignUtils {
	
	//根据所需的入參生成签名
	public static String generateSign(Map<String, Object> requestMap){
		Map<String, Object> sortMap = MapUtils.sortMapByKey(requestMap);
		//1.现将排序好的map转成post提交数据格式字符串
		String params = MapUtils.getParamString(sortMap);
		//2.利用MD5加密字符串
		String sign = MD5Utils.encode(params);
		return sign;
	}

}
