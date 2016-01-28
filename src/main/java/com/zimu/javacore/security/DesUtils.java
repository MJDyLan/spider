/**
 * @author jasonChiu
 * @title
 * @time 2016年1月28日下午7:33:24
 * @version 1.0
 */
package com.zimu.javacore.security;

import java.util.HashMap;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月28日下午7:33:24
 * @version 1.0
 */
public class DesUtils {
	  private static final String encoding = "utf-8";
	  private static final String iv = "01234567";
	  private static final String secretKey = "9fbank_welicai_1437102829831";

	  public static String decode(String paramString)throws Exception
	  {
	    DESedeKeySpec localDESedeKeySpec = new DESedeKeySpec("9fbank_welicai_1437102829831".getBytes());
	    SecretKey localSecretKey = SecretKeyFactory.getInstance("desede").generateSecret(localDESedeKeySpec);
	    Cipher localCipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
	    localCipher.init(2, localSecretKey, new IvParameterSpec("01234567".getBytes()));
	    return new String(localCipher.doFinal(Base64.decodeBase64(paramString)), "utf-8");
	  }

	  public static String encode(String paramString) throws Exception
	  {
	    DESedeKeySpec localDESedeKeySpec = new DESedeKeySpec("9fbank_welicai_1437102829831".getBytes());
	    SecretKey localSecretKey = SecretKeyFactory.getInstance("desede").generateSecret(localDESedeKeySpec);
	    Cipher localCipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
	    localCipher.init(1, localSecretKey, new IvParameterSpec("01234567".getBytes()));
	    return Base64.encodeBase64String(localCipher.doFinal(paramString.getBytes("utf-8")));
	  }

	  public static void main(String[] args) throws Exception
	  {
         HashMap<String, Object> map = 	 new HashMap<String, Object>();
         map.put("mobile", "13349910969");
         map.put("password", "qiujisheng89");
         map.put("version", 241);
         map.put("platform", "android");
         map.put("channel", "guanwang");
         map.put("deviceId", "0");
         map.put("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
         map.put("requestMs", String.valueOf(System.currentTimeMillis()));
         map.put("td_deviceId", "unknow");
         JSONObject object = new JSONObject(map);
         System.out.println(object.toString());
         //oVcx6uJlwOlpi/6TPWErtwtK9BFGt2v7zy/J8UUTfLv6WJ14N6jU5aFxYoza 
         //LqmEsLgpaVjpQUxSxvXlCsxqeOsJIH6wq8coJwxcCnhSJ77bmrK3FBluR2r1 
         //lTVMDOWU41K2ER6xAVbnCSxh9eaYc9rM1m7mqkZRzIdsGqrNdwbRu+hy0qVH 
         //otxVqVZ4wqVmtxU7OIqq2DdyLpFWwZXHrxzGOHXizxh9bnMbAZXGhPtWk92E 
         //2o1Qw8CFdBGKtbxuRjwlc2MPqe+KecKmbSyYnL8DEfs2LNEHp7YVJYBB+PvD 
         //HcbSy0VMe3mNefvFXuERabIYSWYiKYCF/qi6AjrmaUyNzz2dOosm7576fYNV 
         //rHmeCR0UIIVv2A1RnxZ7XQKRD08hDegwrtyr1nZ3rNloU4qCSNGc/WTd
         
     
		 System.out.println(DesUtils.encode(object.toString()));
		  
	  }

}
