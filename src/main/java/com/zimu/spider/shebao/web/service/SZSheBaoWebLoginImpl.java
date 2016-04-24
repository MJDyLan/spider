package com.zimu.spider.shebao.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




import com.zimu.javacore.file.MyFileUtils;
import com.zimu.javacore.http.ConstHttp;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.spider.base.inter.BaseLoginService;

public class SZSheBaoWebLoginImpl extends BaseLoginService<String>{

	@Override
	public void doBefore() {
       //访问首页
	  // HttpGetUtils.sendGet("https://wssb6.szsi.gov.cn/NetApplyWeb/personacctoutInput.jsp");
	}
	
	@Override
	public String getLoginUrl() {
		return "https://wssb6.szsi.gov.cn/NetApplyWeb/personacctoutResult.jsp";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("id", username);
		requestMap.put("bacode",password);
		requestMap.put("PSINPUT", authcode);
		requestMap.put("Submit", "");
	}
	public static void main(String[] args) {
		SZSheBaoWebLoginImpl szLogin = new SZSheBaoWebLoginImpl();
		Map<String,Object> header = new HashMap<String, Object>();
		header.put(ConstHttp.REFERER, "http://www.szsi.gov.cn/ywcq/grsbcx/");

		System.out.println(HttpGetUtils.sendGet("https://wssb6.szsi.gov.cn/NetApplyWeb/personacctoutInput.jsp"));
		//String authCode = "https://wssb6.szsi.gov.cn/NetApplyWeb/CImages";
		//HttpResponse response = HttpGetUtils.sendGet(authCode);
		//MyFileUtils.saveImageToDisk(response.getIn(), "D://sz.png");
		//System.err.println("请输入验证码");
		//Scanner sc = new Scanner(System.in);
		//String authcode = sc.next();
		//System.err.println(szLogin.doLogin("您的身份证号码", "您的电脑号", authcode));
	}

}
