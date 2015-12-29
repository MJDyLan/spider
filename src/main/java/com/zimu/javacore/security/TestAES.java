package com.zimu.javacore.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.message.BasicNameValuePair;


public class TestAES {
	


	public static void main(String[] args) {
		String account = "13509692759";
		String password = "qiujisheng89";
		String message = "x3zMUeaBrazoJK1Iqq+zxA==";
		String key = "0C5E75A210884F61";
		
		String sign_right = "e9fcfb3f0a740a754af7c56de98af645";
		
		String domain = "https://yingapi.yirendai.com";
		String loginUrl = "/p2puserController/p2puserLogin.action"; 
		
		ArrayList localArrayList = new ArrayList();
	    localArrayList.add(new BasicNameValuePair("account", CryptAES.AES_Encrypt(key, account)));
	    localArrayList.add(new BasicNameValuePair("clientType", "2"));
	    localArrayList.add(new BasicNameValuePair("deviceNo", "e205fa700a1211854044887930b5c68bf1f65c45"));
	    localArrayList.add(new BasicNameValuePair("marketId", "374"));
	    localArrayList.add(new BasicNameValuePair("password", CryptAES.AES_Encrypt(key, password)));
	    localArrayList.add(new BasicNameValuePair("secret", "yingonline"));

	    StringBuilder sb = new StringBuilder("account=x3zMUeaBrazoJK1Iqq+zxA=="
	    		+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ=="
	    		+ "&secret=yingonline");
	    String sign = MD5Utils.encode(sb.toString());	    
	
	    System.out.println(sign);

	}

}
