package com.zimu.javacore.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import com.zimu.javacore.io.MyInputStreamUtils;

public class HttpPostUtils {
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
	public static String sendPostReq(String path,String params,boolean isHttps,boolean isNeedCookie) throws UnsupportedEncodingException, IOException{
		
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
        if(isHttps){
			HttpConnectionUtils.buildHttpsURLConnection((HttpsURLConnection)conn);
		}
		HttpConnectionUtils.buildHeader(conn, HttpMethod.POST,isNeedCookie);

		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(params.getBytes());
        outStream.flush();
        outStream.close();
		String result ="";
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();
          //保存cookie
    		CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
            result=new String(MyInputStreamUtils.stream2Byte(inStream), "UTF-8");
        }else if(conn.getResponseCode() >300){
        	String location = conn.getHeaderField("Location");
        	//发现跳转重新设置cookie
        	CookieManager.setCookie(HttpCookieUtils.getCookieValue(conn));
        	System.err.println("提交表单后302重定向后的cookie="+CookieManager.getCookie());
        	result = HttpGetUtils.sendGetStrReq(location,isHttps,true);
        }
		return result;
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
	public static String sendPostReq(String path,String params,boolean isHttps) throws UnsupportedEncodingException, IOException{
		return sendPostReq(path,params, isHttps, false);
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
	public static String sendPostReq(String path,String params) throws UnsupportedEncodingException, IOException{
		return sendPostReq(path,params,false, false);
	}
}
