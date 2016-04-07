package com.zimu.javacore.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zimu.javacore.io.MyInputStreamUtils;

public class HttpGetUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpPostUtils.class);

	/**
	 * 
	 * @author jasonChiu
	 * 
	 * @title 增加https的参数
	 * @param path
	 * @param isHttps
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @time 2016年1月22日上午11:08:26
	 * @version 1.0
	 */
	public static String sendGetStrReq(String path,boolean isHttps,boolean isNeedCookie,Map<String,Object> header){
        if(isHttps){
		  HttpConnectionUtils.buildHttpsURLConnection();
		}
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);

		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET,isNeedCookie,header);
		//保存cookie
		if(StringUtils.isEmpty(CookieManager.getCookie())){
			CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
		}
        String result ="";
        try {
			System.err.println(conn.getResponseCode());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		   if(conn.getResponseCode()==200){
	            InputStream inStream = conn.getInputStream();   
	            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
	        }else if(conn.getResponseCode() > 300){
	        	String location = conn.getHeaderField("Location");
	        	result = sendGetStrReq(location,isHttps,true);
	        }
		} catch (Exception e) {
			logger.error("发送get请求失败",e);
		}
       
		return result;
	}
	public static String sendGetStrReq(String path,boolean isHttps,boolean isNeedCookie){
		return sendGetStrReq(path, isHttps, isNeedCookie, new HashMap<String, Object>());
	}
	/**
	 * 
	 * @title 发送get请求，返回字符串
	 * 
	 * @author JasonChiu
	 * @time 2016年1月21日下午11:46:31
	 * @version 1.0
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendGetStrReq(String path){
		return sendGetStrReq(path,false,false,new HashMap<String,Object>());
	}
	/**
	 * 
	 * @title 发送get请求，返回字符串
	 * 
	 * @author JasonChiu
	 * @time 2016年1月21日下午11:46:31
	 * @version 1.0
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void sendGetFileReq(String path,boolean isHttps) {
		if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection();
		}
		HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET);
		
		//保存cookie
		if(StringUtils.isEmpty(CookieManager.getCookie())){
			CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
		}
        try {
			if(conn.getResponseCode()==200){
			    InputStream inStream = conn.getInputStream();   
			    MyInputStreamUtils.stream2File(inStream, "", "");
			}
		} catch (IOException e) {
			logger.error("发送get请求失败",e);
		}
	}
	public static void sendGetFileReq(String path){
		sendGetFileReq(path, false);
	}
	
	
	
}
