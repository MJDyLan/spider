package com.zimu.javacore.http;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionUtils {
	private final static Logger logger = LoggerFactory.getLogger(HttpConnectionUtils.class);
	
	public static HttpURLConnection getConnection(String path){
		try {
			URL url =new URL(path);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        return conn;
		} catch (Exception e) {
			logger.error("构建httpurlconnect失败",e);
		}
		return null;
	}
	public static void buildHeader(HttpURLConnection conn,String method){
		buildHeader(conn, method, false);
	}
	
	public static void buildHeader(HttpURLConnection conn,String method,boolean needCookie){
		
		try {
			conn.setRequestMethod(method);
		} catch (ProtocolException e) {
		   logger.error("构建请求方式失败");
		}
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        //设置cookie
        if(needCookie){
        	conn.setRequestProperty("Cookie", CookieManager.getCookie());
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
        conn.addRequestProperty("Referer", "https://www.dianrong.com/account/my-account");  
        // 必须设置false，否则会自动redirect到Location的地址  
        conn.setInstanceFollowRedirects(false);
        
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
	}
	
	
	/**
	 * 设置https相关属性
	 * @param connection
	 */
	public static void buildHttpsURLConnection(HttpsURLConnection connection) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0],
					new TrustManager[] { new DefaultTrustManager() },
					new SecureRandom());
			SSLContext.setDefault(ctx);
			connection.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
