package com.zimu.javacore.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.zimu.javacore.io.MyInputStreamUtils;

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
		buildHeader(conn, method,new HashMap<String,Object>());
	}
	public static void buildHeader(HttpURLConnection conn,String method,Map<String,Object> header){
		
		try {
			conn.setRequestMethod(method);
		} catch (ProtocolException e) {
		   logger.error("构建请求方式失败");
		}
		if(method.equals(HttpMethod.POST)){
			conn.setDoOutput(true);
		}
        conn.setUseCaches(false);
        //设置cookie
        conn.setRequestProperty("Cookie", CookieManager.getCookie());
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
        // 必须设置false，否则会自动redirect到Location的地址  
        conn.setInstanceFollowRedirects(false);
        //自定义在请求之前指明的头参数
        buildHeader(conn,header);
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
	}
	public static void buildHeader(HttpURLConnection conn,Map<String,Object> header){
		Set<String> set = header.keySet();
		if(CollectionUtils.isEmpty(set))return;
		for (String key : set) {
			conn.addRequestProperty(key, header.get(key).toString());
		}
	}

	
	/**
	 * 设置https相关属性
	 * @param connection
	 */
	public static void buildHttpsURLConnection() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0],
					new TrustManager[] { new DefaultTrustManager() },
					new SecureRandom());
			SSLContext.setDefault(ctx);
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			HttpsURLConnection.setDefaultSSLSocketFactory(ctx  
		            .getSocketFactory()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
	/**
	 * ResponseHeader 值从HttpURLConnection取得并保存
	 */
	public static void buildHttpResponseBy(final HttpResponse response, final HttpURLConnection urlConnection) {
		if (response == null || urlConnection == null) {
			return;
		}
		response.setUrl(urlConnection.getURL().toString());
		//设置cookies
		HttpCookieUtils.mergeCookie(urlConnection);
		try {
			response.setResponseCode(urlConnection.getResponseCode());
			System.err.println(urlConnection.getContentType());
			//根据响应来处理inputstream
			response.setIn(urlConnection.getInputStream());
		} catch (final IOException e) {
			e.printStackTrace();
		}
		response.setResponseHeader(ConstHttp.CONTENT_LENGTH, urlConnection.getHeaderField(ConstHttp.CONTENT_LENGTH));
		response.setResponseHeader(ConstHttp.CONTENT_TYPE, urlConnection.getHeaderField(ConstHttp.CONTENT_TYPE));
		response.setResponseHeader(ConstHttp.EXPIRES, urlConnection.getHeaderField(ConstHttp.EXPIRES));
		response.setResponseHeader(ConstHttp.CACHE_CONTROL, urlConnection.getHeaderField(ConstHttp.CACHE_CONTROL));
		response.setResponseHeader(ConstHttp.LOCATION, urlConnection.getHeaderField(ConstHttp.LOCATION));
	}
	public static void main(String[] args) {
		System.err.println(URLEncoder.encode("aaa==ccc&aas=asa"));
	}
}
