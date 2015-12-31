package com.zimu.javacore.security;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class HttpUtils {
	public static String sendPost(String path,String params) throws UnsupportedEncodingException, IOException{
		String encoding="UTF-8";
		byte[] data = params.getBytes(encoding);
        URL url =new URL(path);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        buildHttpsURLConnection(conn);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        System.out.println(conn.getResponseCode()); //响应代码 200表示成功
        String result ="";
        //if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();   
            result=new String(stream2Byte(inStream), "UTF-8");
        //}
		return result;
	}

	 public static  byte[] stream2Byte(InputStream is) throws IOException {
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    int len = 0;
		    byte[] b = new byte[1024];
		    while ((len = is.read(b, 0, b.length)) != -1) {                     
		        baos.write(b, 0, len);
		    }
		    byte[] buffer =  baos.toByteArray();
		    return buffer;
     }
	 /**
		 * 设置https相关属性
		 */
		private static void buildHttpsURLConnection(HttpsURLConnection connection) {
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
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
