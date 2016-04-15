package com.kingdee.finance.julicai.spider.site.fund.zlfund.web.spider;

import java.io.OutputStream;
import java.util.Map;

import com.kingdee.finance.julicai.spider.base.BaseWebLoginSpider;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.constant.ZlfundWebConstants;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model.ZlfundWebLoginModel;
import com.kingdee.javacorelib.constant.net.ConstHttp;
import com.kingdee.javacorelib.utils.net.MyHttpCookieUtils;
import com.kingdee.javacorelib.utils.net.MyHttpGetUtils;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * login model Created by 邱吉胜 on 2016年4月1日
 */
public final class ZlfundWebLoginSpider extends BaseWebLoginSpider<WebLoginResultVo, String> {

	private static final String LOGIN_URL = ZlfundWebConstants.LOGIN_URL;


	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return null;
	}

	@Override
	protected void buildLoginParam(Map<String, String> requestParam, String userName, String password, String verifyCode) {
		requestParam.put("identity", userName);
		requestParam.put("password", password);
		// 在建立连接时拿到cookie,解析cookie，可以考虑放到底层
		String token = MyHttpCookieUtils.getCookieValueByKey(httpCookieStorage, "csrftoken");
		requestParam.put("csrfmiddlewaretoken", token);
	}

	@Override
	protected void doPrepareLogin() {
		MyHttpGetUtils.getInstance(httpCookieStorage).httpGetResponseHeader(LOGIN_URL);
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
		return ZlfundWebLoginModel.getInstanceByResponse(response);
	}

	@Override
	protected String getRequestUrl() {
		return LOGIN_URL;
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
		requestHeader.put(ConstHttp.REFERER, " https://www.zlfund.cn/");
	}

	public static void main(String[] args) {
		ZlfundWebLoginSpider spider = new ZlfundWebLoginSpider();
		spider.setIsDebug(true);
		System.err.println(spider.doLogin("429004198902172759", "qiujisheng89", ""));
	}
}
