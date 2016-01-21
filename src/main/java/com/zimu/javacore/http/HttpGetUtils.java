package com.zimu.javacore.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.zimu.javacore.io.MyInputStreamUtils;

public class HttpGetUtils {
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
		
		HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
		
		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET);
		//保存cookie
		CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
	    
        String result ="";
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();   
            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
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
	public static void sendGetFileReq(String path) throws UnsupportedEncodingException, IOException{
		
		HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
		
		HttpConnectionUtils.buildHeader(conn, HttpMethod.GET);
		//保存cookie
		CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
	    
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();   
            MyInputStreamUtils.stream2File(inStream, "", "");
       
        }
	}
	
}
