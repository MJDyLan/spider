package com.zimu.javacore.http;

import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpCookieUtils {

	public static String getCookie(HttpURLConnection conn){
		//获取cookie
		Map<String,List<String>> map=conn.getHeaderFields();
		List<String> cookies = map.get("Set-Cookie");
		System.out.println("cookies="+cookies.toString());
		return cookies.toString();		
	}
}
