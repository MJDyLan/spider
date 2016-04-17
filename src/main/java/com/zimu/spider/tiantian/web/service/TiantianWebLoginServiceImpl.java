package com.zimu.spider.tiantian.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.utils.JsonMapper;
import com.zimu.javacore.utils.MapUtils;

public class TiantianWebLoginServiceImpl implements TiantianWebLoginService {
	private Map<String,Object> header = new HashMap<String, Object>();
	
	@Override
	public void addHeader() {
		header.put("REFERER", "https://trade.1234567.com.cn/login");
		header.put("Content_Type", "application/json");
	}
	@Override
	public void doBefore() {
		String loginUrl = "https://trade4.1234567.com.cn/login";
	    HttpGetUtils.sendGetStrReq(loginUrl, true, true);	
	}
	@Override
	public String getLoginUrl() {
		return "https://trade4.1234567.com.cn/do.aspx/CheckedCS";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password) {
		// CS: JsEncrpt.encode(encodeURIComponent(opts.TabID + "," + at + "," +
		// $.trim(name) + "," + escape($.trim(tbpwd.val())) + "," +
		// $("#hidenum").val() + "," + tbcode.val() + "," + direct))
		StringBuilder csBuilder = new StringBuilder();
		csBuilder.append("0");// TabID
		csBuilder.append(",").append(0); // ctype cert 证件类型
		csBuilder.append(",").append(username);
		csBuilder.append(",").append(password);
		csBuilder.append(",").append(0);// hidenum 验证码类型 0不用验证码
		csBuilder.append(",").append("");// 验证码为空
		csBuilder.append(",").append("");// direct 脚本中参数，默认空
		try {
			String postData = Base64.encodeBase64String(URLEncoder.encode(csBuilder.toString(), "utf-8").getBytes());
			requestMap.put("CS", postData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String doLogin(String username, String password, String authcode) {
		
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,username, password);
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		String jsonStr = mapper.toJson(requestMap);
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), jsonStr, true,true,header);
		System.err.println(result);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return null;
	}
	public static void main(String[] args) {
		TiantianWebLoginService login = new TiantianWebLoginServiceImpl();
		login.doBefore();
		login.addHeader();
		login.doLogin("13728670834", "feidee123", "");
	}

}
