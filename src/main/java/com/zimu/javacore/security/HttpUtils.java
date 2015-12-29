package com.zimu.javacore.security;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class HttpUtils {
	public static String sendPost(String path,String params) throws UnsupportedEncodingException, IOException{
		String encoding="UTF-8";
		byte[] data = params.getBytes(encoding);
        URL url =new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="+ encoding);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        
        conn.setRequestProperty("networkType", UUID.randomUUID().toString());

        conn.setRequestProperty("location", "");
        
        conn.setRequestProperty("operateId", UUID.randomUUID().toString());

        conn.setRequestProperty("screenSize", "480X854");

        conn.setRequestProperty("clientVersion", "2.6.0");

        conn.setRequestProperty("deviceId", "866977025217383");

        conn.setRequestProperty("clientDatetime", "2015-12-29 23:53:52.642");

        conn.setRequestProperty("clientType", "4.4.4");

        conn.setRequestProperty("channelId", "4C9EF1676B564240DF6AA684F968E85A");

        conn.setRequestProperty("tokenId", "");

        conn.setRequestProperty("clientIp", "192.168.1.3");
        
        conn.setConnectTimeout(5*1000);
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
}
