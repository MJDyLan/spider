/**
 * 
 */
package com.zimu.javacore.http;

import java.net.HttpURLConnection;


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
}
