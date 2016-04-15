package com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model;

import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexStringUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * login model
 * <pre>
 * class intro
 * </pre>
 * <p/>
 * Created by hnusr on 2016-04-07.
 */
public final class PpmoneyWebLoginModel {

	private static final String LOGIN_ERROR_REGEX = "firstErrorMsg='(.*?)'";
	private static final String LOGIN_SUCCESS_FLAG = "setTimeout(\"redirect()\",1)";

	public static WebLoginResultVo getInstanceByHtml(String html) {
		html = MyRegexHtmlUtils.removeHtmlByBlank(html);
		WebLoginResultVo loginResultVo = new WebLoginResultVo();
		if (html.contains(LOGIN_SUCCESS_FLAG)) {
			loginResultVo.setLoginSuccess(true);
		} else {
			String errorMsg = MyRegexStringUtils.getMatchByGroupIndex(html, LOGIN_ERROR_REGEX);
			loginResultVo.setErrorMessage(errorMsg);
		}
		return loginResultVo;
	}
}
