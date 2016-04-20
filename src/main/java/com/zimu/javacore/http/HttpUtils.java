package com.zimu.javacore.http;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import com.zimu.javacore.io.MyInputStreamUtils;


/** 
 * @title 一个简单的http工具类 后面要抽象和封装 
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:20
 * @version 1.0
 */
public class HttpUtils {
	public static String encodeUrlProperty(String value) {
		String result = "";
		try {
			result = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encodeUrlProperty("account=x3zMUeaBrazoJK1Iqq+zxA=="
						+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&"
						+ "deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ=="
						+ "&secret=yingonline"));
	}
}
