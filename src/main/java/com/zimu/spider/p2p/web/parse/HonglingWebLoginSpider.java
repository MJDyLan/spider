package com.kingdee.finance.julicai.spider.site.p2p.hongling.web.parse;

import com.kingdee.finance.julicai.spider.base.BaseWebLoginSpider;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.constant.HonglingWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model.HonglingWebLoginModel;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model.regex.HonglingWebLoginRegexModel;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.utils.net.MyHttpGetUtils;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.utils.regex.MyRegexStringUtils;
import com.kingdee.javacorelib.utils.security.MySecurityMD5Utils;
import com.kingdee.javacorelib.utils.text.MyStringCompareUtils;
import com.kingdee.javacorelib.utils.text.MyStringUtils;

import java.io.OutputStream;
import java.util.Map;

/**
 * login
 * Created by hnusr on 2016/3/6.
 */
public final class HonglingWebLoginSpider extends BaseWebLoginSpider<HonglingWebLoginModel, HonglingWebLoginRegexModel> {

	private static final String LOGIN_PAGE_URL = HonglingWebConst.LOGIN_PAGE_URL;
	private static final String LOGIN_ACTION_URL = HonglingWebConst.LOGIN_ACTION_URL;
	private static final String LOGIN_VERIFY_CODE_CHECK_URL = HonglingWebConst.LOGIN_VERIFY_CODE_CHECK_URL;

	private static final String APP_KEY_REGEX = "(?<=app_key.{2,4}value=.{1}).{10,80}(?=\"/>)";
	private static final String REQUESTID_REGEX = "tongdunRequestID='(.*?)'";
	private static final String ERROR_MSG_REGEX = "id=\"errorMsg\".{10,20}>(.{3,30})</span>";
	private static final String IS_EXIST_VERIFY_CODE_REGEX = "请输入验证码";
	private static final String RANDOM_PAGE_ID_REGEX = "code\\?randomPageId=(.{20,50}?)\"";
	private static final String LOGIN_SUCCESS_REGEX = ">退出</a>";

	private String appKey;
	private String requestId;
	private String randomPageId;
	private boolean isNeedVerifyCode = false;

	// 判断验证码是否正确
	public boolean doCheckVerifyCodeSuccess(String verifyCode) {
		String verifyCodeCheckUrl = String.format("%s?verifyCode=%s&randomPageId=%s", LOGIN_VERIFY_CODE_CHECK_URL, verifyCode, randomPageId);
		MyLogUtils.log(verifyCodeCheckUrl, isDebug());
		String result = MyHttpGetUtils.getInstance(httpCookieStorage).httpGetString(verifyCodeCheckUrl);
		return !MyStringCompareUtils.isAnyEqualsIgnoreCase(result, "0", "2");
	}

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		String verifyCodeUrl = HonglingWebConst.BASE_PATH_URL + "/sso/code?randomPageId=" + randomPageId;
		return MyHttpGetUtils.getInstance(httpCookieStorage).httpGetOutputStream(verifyCodeUrl);
	}

	@Override
	protected void buildLoginParam(Map<String, String> requestParam, String userName, String password, String verifyCode) {
		requestParam.put("username", userName);
		requestParam.put("password", password);
		requestParam.put("md5Pwd", MySecurityMD5Utils.md5Hex(password));
		if (MyStringUtils.isNotEmpty(verifyCode)) {
			requestParam.put("checkCode", verifyCode);
		}
	}

	@Override
	protected void doPrepareLogin() {
		// 如果之前已经取过了，就不需要再取
		if (MyStringCompareUtils.isAnyEmpty(appKey, requestId)) {
			String html = MyHttpGetUtils.getInstance(httpCookieStorage).httpGetString(LOGIN_PAGE_URL);
			appKey = MyRegexStringUtils.getFirstMatch(html, APP_KEY_REGEX);
			requestId = MyRegexStringUtils.getMatchByGroupIndex(html, REQUESTID_REGEX);
			isNeedVerifyCode = MyRegexStringUtils.getMatchCount(html, IS_EXIST_VERIFY_CODE_REGEX) > 0;
			if (isNeedVerifyCode) {
				randomPageId = MyRegexStringUtils.getMatchByGroupIndex(html, RANDOM_PAGE_ID_REGEX);
			}
		}
	}

	@Override
	protected String cleanHtml(String html) {
		return html;
	}

	@Override
	protected HonglingWebLoginRegexModel queryWebParseRegexModel() {
		HonglingWebLoginRegexModel regexModel = new HonglingWebLoginRegexModel();
		regexModel.setErrorMsgRegex(ERROR_MSG_REGEX);
		regexModel.setLoginSuccessRegex(LOGIN_SUCCESS_REGEX);
		return regexModel;
	}

	@Override
	protected HonglingWebLoginModel toModel(MyHttpResponse response, HonglingWebLoginRegexModel regexModel) {
		String resultStr = response.getResponseBody();
		MyLogUtils.log(resultStr, isDebug());
		MyLogUtils.log(randomPageId, isDebug());
		randomPageId = MyRegexStringUtils.getMatchByGroupIndex(resultStr, RANDOM_PAGE_ID_REGEX);
		MyLogUtils.log(randomPageId, isDebug());
		return HonglingWebLoginModel.getInstanceBy(resultStr, regexModel);
	}

	@Override
	protected String getRequestUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {
		requestParam.put("app_key", appKey);
		requestParam.put("requestId", requestId);
		requestParam.put("back_url", "http%3A%2F%2Fwww.my089.com%2F");
		requestParam.put("session_kept", "30");
		requestParam.put("ltcc", "");
	}

	// 从登录页面查看是否需要验证码
	public boolean isNeedVerifyCodeByPullLoginPage() {
		doPrepareLogin();
		return isNeedVerifyCode;
	}
}
