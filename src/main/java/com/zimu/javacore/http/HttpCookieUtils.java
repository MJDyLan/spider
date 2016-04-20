/**
 * 
 */
package com.zimu.javacore.http;

import java.net.HttpURLConnection;

import org.apache.commons.lang3.StringUtils;


/**
 * @author jasonChiu
 * @title
 * @time 2016年1月14日下午3:57:59
 * @version 1.0
 */
public class HttpCookieUtils {
 
	/**
	 * 从urlConnection请求中取得cookie值，多个cookie会合并，主要用于登录后请求
	 */
	public static String getCookieValue(final HttpURLConnection urlConnection) {
		String cookieStr = "";
		String key;
		for (int i = 1; (key = urlConnection.getHeaderFieldKey(i)) != null; i++) {
			if (key.equalsIgnoreCase("Set-cookie")) {
				final String cookieVal = urlConnection.getHeaderField(i);
				cookieStr += cookieVal + ";";
			}
		}
		return cookieStr;
	}
	public static String getCookieByKey(String key){
		String cookieValue = "";
		String cookies = CookieManager.getCookie();
		String[] cookieArr = cookies.split(";");
		for (int i = 0; i < cookieArr.length; i++) {
			if(cookieArr[i].startsWith(key)){
				cookieValue = cookieArr[i].split("=")[1];
			}
		}
		return cookieValue;
	}
	public static void mergeCookie(String cookie){
		if(StringUtils.isEmpty(cookie))return;
		String source = CookieManager.getCookie();
		if(StringUtils.isNotEmpty(source)){
			source = ";"+cookie;
		}else{
			source = cookie;
		}
		CookieManager.setCookie(source);
		System.err.println("合并cookie"+source);
	}
}
