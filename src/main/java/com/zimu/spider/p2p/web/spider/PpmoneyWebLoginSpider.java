package com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.spider;

import com.kingdee.finance.julicai.spider.base.BaseWebLoginSpider;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.constant.PpmoneyWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model.PpmoneyWebLoginModel;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.utils.net.MyHttpCookieUtils;
import com.kingdee.javacorelib.utils.net.MyHttpGetUtils;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

import java.io.OutputStream;
import java.util.Map;

/**
 * pp money login spider
 * <p/>
 * <pre>
 * 1.目前是写死的cookie，很容易被校验成过期的请求
 * </pre>
 * <p/>
 * Created by 邱吉胜 on 2016-04-05.
 */
public final class PpmoneyWebLoginSpider extends BaseWebLoginSpider<WebLoginResultVo, String> {

	private static final String LOGIN_ACTION_URL = PpmoneyWebConst.LOGIN_ACTION_URL;
	private static final String VERIFY_CODE_URL = PpmoneyWebConst.VERIFY_CODE_URL;

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return MyHttpGetUtils.getInstance(httpCookieStorage).httpGetOutputStream(VERIFY_CODE_URL);
	}

	@Override
	protected void buildLoginParam(Map<String, String> requestParam, String userName, String password, String verifyCode) {
		requestParam.put("Phone", userName);
		requestParam.put("Password", password);
		requestParam.put("RandCode", verifyCode);
	}

	@Override
	protected void doPrepareLogin() {
		MyHttpCookieUtils.addCookieByKeyValue(httpCookieStorage, "nTalk_CACHE_DATA", "{uid:kf_9150_ISME9754_guestTEMP3EEE-A351-39,tid:1458639476933988}");
		MyHttpCookieUtils.addCookieByKeyValue(httpCookieStorage, "InitUser", "%7B%22value%22%3A%7B%22PayDate%22%3Anull%2C%22Amount%22%3Anull%2C%22NickName%22%3Anull%2C%22NotReadLetterCount%22%3A0%2C%22IsAdmin%22%3Afalse%2C%22AdminUrl%22%3Anull%2C%22NowTime%22%3A8492%7D%2C%22expires%22%3A1458650451307%7D");
		MyHttpGetUtils.getInstance(httpCookieStorage).httpGetResponseHeader(LOGIN_ACTION_URL);
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
		return PpmoneyWebLoginModel.getInstanceByHtml(response.getResponseBody());
	}

	@Override
	protected String getRequestUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {
		requestParam.put("returnUrl", "");
		requestParam.put("RandCode", "");
	}
}
