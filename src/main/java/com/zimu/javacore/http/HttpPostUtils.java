package com.zimu.javacore.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zimu.javacore.io.MyInputStreamUtils;
import com.zimu.javacore.utils.RegexUtils;

public class HttpPostUtils {
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
	public static String sendPostReq(String path,String params,boolean isHttps,boolean isNeedCookie,Map<String,Object> header){
		if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection();
		}
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
       
		HttpConnectionUtils.buildHeader(conn, HttpMethod.POST,isNeedCookie,header);
		String result ="";
		try {
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
	        outStream.write(params.getBytes());
	        outStream.flush();
	        outStream.close();
			
	        if(conn.getResponseCode()==200){
	            InputStream inStream = conn.getInputStream();
	          //保存cookie
	            System.err.println("cookie list:"+conn.getHeaderFields().get("Set-Cookie").toString());
	    		CookieManager.setCookie(conn.getHeaderFields().get("Set-Cookie").toString());
	    		System.err.println("提交表单后200的cookie："+HttpCookieUtils.getCookieValue(conn));
	            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
	        }else if(conn.getResponseCode() >300){
	        	String location = conn.getHeaderField("Location");
	        	//这时location可能没有带域名
	        	//从path中提取域名
	        	String domain = RegexUtils.getDomian(path);
	        	String procol = isHttps?"https://":"http://";
	        	if(!location.contains(domain)){
	        		location = procol+domain+location;
	        	}
	        	//发现跳转重新设置cookie
	        	HttpCookieUtils.mergeCookie(HttpCookieUtils.getCookieValue(conn));
	        	
	        	System.err.println("提交表单后302重定向后的cookie="+CookieManager.getCookie());
	        	result = HttpGetUtils.sendGetStrReq(location,isHttps,true);
	        }
		} catch (Exception e) {
			logger.error("发送post请求异常", e);
		}
		return result;
	}
	public static String sendPostReq(String path,String params,boolean isHttps,boolean isNeedCookie){
		return sendPostReq(path,params, isHttps, false,new HashMap<String,Object>());

	}
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
	public static String sendPostReq(String path,String params,boolean isHttps){
		return sendPostReq(path,params, isHttps, false,new HashMap<String,Object>());
	}
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
	public static String sendPostReq(String path,String params){
		return sendPostReq(path,params,false, false,new HashMap<String,Object>());
	}
	
	public static HttpResponse sendPost(String path,String params,boolean isHttps,Map<String,Object> header){
		HttpResponse response = new HttpResponse();
		if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection();
		}
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
       
		HttpConnectionUtils.buildHeader(conn, HttpMethod.POST,header);
		String result ="";
		try {
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
	        outStream.write(params.getBytes());
	        outStream.flush();
	        outStream.close();
			
	       
	      /*  if(conn.getResponseCode()==200){
	            InputStream inStream = conn.getInputStream();
	          //保存cookie
	            System.err.println("cookie list:"+conn.getHeaderFields().get("Set-Cookie").toString());
	    		CookieManager.setCookie(conn.getHeaderFields().get("Set-Cookie").toString());
	    		System.err.println("提交表单后200的cookie："+HttpCookieUtils.getCookieValue(conn));
	            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
	        }else if(conn.getResponseCode() >300){
	        	String location = conn.getHeaderField("Location");
	        	//这时location可能没有带域名
	        	//从path中提取域名
	        	String domain = RegexUtils.getDomian(path);
	        	String procol = isHttps?"https://":"http://";
	        	if(!location.contains(domain)){
	        		location = procol+domain+location;
	        	}
	        	//发现跳转重新设置cookie
	        	HttpCookieUtils.mergeCookie(HttpCookieUtils.getCookieValue(conn));
	        	
	        	System.err.println("提交表单后302重定向后的cookie="+CookieManager.getCookie());
	        	result = HttpGetUtils.sendGetStrReq(location,isHttps,true);
	        }*/
	        HttpConnectionUtils.buildHttpResponseBy(response,conn);
	        return response;
		} catch (Exception e) {
			logger.error("发送post请求异常", e);
		}
		return response;
	}
}
