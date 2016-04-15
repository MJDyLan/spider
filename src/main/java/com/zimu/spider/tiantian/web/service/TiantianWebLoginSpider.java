package com.kingdee.finance.julicai.spider.site.fund.tiantian.web.spider;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.kingdee.finance.julicai.spider.base.BaseWebLoginSpider;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.constant.TiantianWebConstants;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.model.TiantianWebLoginModel;
import com.kingdee.javacorelib.constant.net.ConstHttp;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.utils.map.MyMapConvertUtils;
import com.kingdee.javacorelib.utils.net.MyHttpGetUtils;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.utils.text.MyStringEncodeUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * 
 * @title 天天基金登陆
 * 
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月12日
 * @version 1.0
 */
public final class TiantianWebLoginSpider extends BaseWebLoginSpider<WebLoginResultVo, String> {

	private static final String LOGIN_ACTION_URL = TiantianWebConstants.LOGIN_ACTION_URL;

	private String userName = "";
	private String password = "";


	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return null;
	}

	@Override
	protected void buildLoginParam(Map<String, String> requestParam, String userName, String password, String verifyCode) {
		this.userName = userName;
		this.password = password;
		// CS: JsEncrpt.encode(encodeURIComponent(opts.TabID + "," + at + "," +
		// $.trim(name) + "," + escape($.trim(tbpwd.val())) + "," +
		// $("#hidenum").val() + "," + tbcode.val() + "," + direct))
		StringBuilder csBuilder = new StringBuilder();
		csBuilder.append("0");// TabID
		csBuilder.append(",").append(0); // ctype cert 证件类型
		csBuilder.append(",").append(userName);
		csBuilder.append(",").append(password);
		csBuilder.append(",").append(0);// hidenum 验证码类型 0不用验证码
		csBuilder.append(",").append("");// 验证码为空
		csBuilder.append(",").append("");// direct 脚本中参数，默认空
		try {
			String postData = MyStringEncodeUtils.base64Encode(URLEncoder.encode(csBuilder.toString(), "utf-8").getBytes());
			requestParam.put("CS", postData);
		} catch (UnsupportedEncodingException e) {
			MyLogUtils.log("构造天天基金登录入參编码异常");
		}
	}

	@Override
	protected void doPrepareLogin() {
		MyHttpGetUtils.getInstance(httpCookieStorage).httpGetResponseHeader(TiantianWebConstants.LOGIN_URL);
	}

	@Override
	protected String cleanHtml(String html) {
		return html;
	}

	@Override
	protected String queryWebParseRegexModel() {
		return null;
	}

	@Override
	protected WebLoginResultVo toModel(MyHttpResponse response, String regexModel) {
		String body =  response.getResponseBody();
		WebLoginResultVo vo = TiantianWebLoginModel.getInstanceByJson(body);
		if (vo.isLoginSuccess()) {
			// 成功后继续发送get请求
			Map<String, String> params = new HashMap<String, String>(3);
			params.put("callback", "callback");
			StringBuilder csBuilder = new StringBuilder();
			csBuilder.append("0");// TabID
			csBuilder.append(",").append(0); // ctype cert 证件类型
			csBuilder.append(",").append(this.userName);
			csBuilder.append(",").append(this.password);
			csBuilder.append(",").append("");// direct 脚本中参数，默认空
			try {
				params.put("CS", MyStringEncodeUtils.base64Encode(URLEncoder.encode(csBuilder.toString(), "utf-8").getBytes()));
			} catch (UnsupportedEncodingException e) {
				MyLogUtils.log("构造天天基金登录入參编码异常");
			}
			params.put("_", String.valueOf(System.currentTimeMillis()));
			// 获取路由信息（请求的域名）
			String routeDomain = vo.getTempInfo();
			String result = MyHttpGetUtils.getInstance(httpCookieStorage).httpGetString(routeDomain + "/SearchHandler/login.ashx", MyMapConvertUtils.toStringForHttpEncode(params));
			return TiantianWebLoginModel.getInstanceByString(result, routeDomain);
		}
		return vo;
	}

	@Override
	protected String getRequestUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {
	}

	@Override
	protected boolean isRedirectLocationByHttp302() {
		return true;
	}

	@Override
	protected void buildRequestHeader(Map<String, String> requestHeader) {
		requestHeader.put(ConstHttp.REFERER, "https://trade.1234567.com.cn/login");
		requestHeader.put(ConstHttp.CONTENT_TYPE, ConstHttp.CONTENT_TYPE_JSON);
	}
}
