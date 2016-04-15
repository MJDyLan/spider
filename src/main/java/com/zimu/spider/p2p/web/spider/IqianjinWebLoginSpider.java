package com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.spider;

import com.kingdee.finance.julicai.spider.base.BaseWebLoginSpider;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.constant.IqianjinWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.model.IqianjinWebLoginModel;
import com.kingdee.javacorelib.utils.json.MyGsonGetUtils;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.utils.net.MyHttpGetUtils;
import com.kingdee.javacorelib.utils.net.MyHttpPostUtils;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.utils.text.MyStringEncodeUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 爱钱进 login spider
 * <p/>
 * <pre>
 * 1.登录
 * </pre>
 * <p/>
 * Created by 邱吉胜 on 2016-04-05.
 */
public final class IqianjinWebLoginSpider extends BaseWebLoginSpider<WebLoginResultVo, String> {

	private static final String LOGIN_ACTION_URL = IqianjinWebConst.LOGIN_ACTION_URL;
	private static final String LOGIN_AUTHCODE_URL = IqianjinWebConst.LOGIN_AUTHCODE_URL;
	private static final String LOGIN_CHECKCODE_URL = IqianjinWebConst.LOGIN_CHECKCODE_URL;

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		String verifyCodeUrl = LOGIN_AUTHCODE_URL + "?v=" + System.currentTimeMillis();
		return MyHttpGetUtils.getInstance(httpCookieStorage).httpGetOutputStream(verifyCodeUrl);
	}

	@Override
	protected void buildLoginParam(Map<String, String> requestParam, String userName, String password, String verifyCode) {
		requestParam.put("name", userName);
		requestParam.put("password", MyStringEncodeUtils.base64Encode(password));
		requestParam.put("code", verifyCode);
	}

	@Override
	public boolean checkVerifyCodeIsSuccess(String verifyCode) {
		Map<String, String> requestParam = new HashMap<String, String>();
		requestParam.put("name", verifyCode);
		requestParam.put("code", verifyCode);
		String jsonStr = MyHttpPostUtils.getInstance(httpCookieStorage).httpPostString(LOGIN_CHECKCODE_URL, requestParam);
		return MyGsonGetUtils.getBoolean(jsonStr, "success");
	}

	@Override
	protected void doPrepareLogin() {

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
		MyLogUtils.log(response.getResponseBody(), isDebug());
		return IqianjinWebLoginModel.getInstanceByJson(response.getResponseBody());
	}

	@Override
	protected String getRequestUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {
		requestParam.put("rememberMe", "on");
	}
}
