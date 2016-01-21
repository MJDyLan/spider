package com.zimu.javacore.http;

public class CookieManager {

	private static final ThreadLocal<String> cookieThreadLocal = new ThreadLocal<String>();
	
	public static void setCookie(String cookie){
		cookieThreadLocal.set(cookie);
	}
	
	public static String getCookie(){
		return cookieThreadLocal.get();
	}
}
