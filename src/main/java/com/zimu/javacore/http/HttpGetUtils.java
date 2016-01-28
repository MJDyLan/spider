package com.zimu.javacore.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.io.MyInputStreamUtils;

public class HttpGetUtils {
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
	public static String sendGetStrReq(String path,boolean isHttps,boolean isNeedCookie) throws UnsupportedEncodingException, IOException{
		
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
        if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection((HttpsURLConnection)conn);
		}
		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET,isNeedCookie);
		//保存cookie
		if(StringUtils.isEmpty(CookieManager.getCookie())){
			CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
		}
		
        String result ="";
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();   
            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
        }else if(conn.getResponseCode() > 300){
        	String location = conn.getHeaderField("Location");
        	result = sendGetStrReq(location,isHttps,true);
        }
		return result;
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
	public static String sendGetStrReq(String path) throws UnsupportedEncodingException, IOException{
		return sendGetStrReq(path,false,false);
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
		
		HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
		
		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET);
		if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection((HttpsURLConnection)conn);
		}
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
			
		}
	}
	public static void sendGetFileReq(String path) throws UnsupportedEncodingException, IOException{
		sendGetFileReq(path, false);
	}
	
	
	
}
