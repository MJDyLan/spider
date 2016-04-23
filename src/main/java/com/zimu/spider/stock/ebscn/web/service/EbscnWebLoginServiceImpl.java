package com.zimu.spider.stock.ebscn.web.service;

import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.file.MyFileUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.spider.base.inter.BaseLoginService;

public class EbscnWebLoginServiceImpl extends BaseLoginService<String> implements IEbscnWebLoginService<String> {

	
	
	@Override
	public void doBefore() {
		String captchCodeUrl = "https://sc.ebscn.com/thinkweixinsite/servlet/CaptchaServlet";
		HttpResponse response = HttpPostUtils.sendPost(captchCodeUrl);
		//保存验证码图片
		MyFileUtils.saveImageToDisk(response.getIn(), "D://");
	}

	@Override
	public String getLoginUrl() {
		return "";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap, String username, String password, String authcode) {
		requestMap.put("funcNo", "1000302");
		requestMap.put("accounttype", 1);
		requestMap.put("account", "22431038");
		requestMap.put("clientinfo", "");
		requestMap.put("jsessionid", "");
	}
	public static void main(String[] args) {
		EbscnWebLoginServiceImpl login = new EbscnWebLoginServiceImpl();
		Map<String,Object> map = new HashMap<String, Object>();
		login.buildLoginParam(map,"","","");
		
		HttpResponse response  = HttpPostUtils.sendPost("https://sc.ebscn.com/thinkweixinsite/servlet/json", map, true, new HashMap<String, Object>());
		System.out.println(response.getResponseBody());
		
	}
}
