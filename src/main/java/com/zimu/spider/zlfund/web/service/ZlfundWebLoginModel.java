package com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model;

import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.constant.ZlfundWebConstants;
import com.kingdee.javacorelib.constant.net.ConstHttp;
import com.kingdee.javacorelib.utils.net.MyHttpResponse;
import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;


/**
 * 
 * @title 登录model
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月11日
 * @version 1.0
 */
public class ZlfundWebLoginModel {
	private static final String LOGIN_SUCCESS_FLAG = "accounts/profile";
	// 登录错误提取信息正则表达式
	private static final String LOGIN_USER_PASSWORD_LOCKED = "5次输错密码";

	public static WebLoginResultVo getInstanceByResponse(MyHttpResponse response) {
		WebLoginResultVo loginResultVo = new WebLoginResultVo();
		int responseCode = response.getResponseCode();
		String location = response.getResponseHeaderByLocation();
		String result = response.getResponseBodyByCleanHtml();
		if ((responseCode == ConstHttp.STATUS_CODE_REDIRECT_302 && location.contains(ZlfundWebConstants.LOGIN_SUCCESS_REDIRECT_URL)) || result.contains(LOGIN_SUCCESS_FLAG)) {
			loginResultVo.setIsLoginSuccess(true);
		} else {
			String errorMessage = MyRegexHtmlUtils.getTagInnerText(response.getResponseBody(), "div", "class", "zltipbox zltipbox-danger");
			errorMessage = MyRegexHtmlUtils.removeHtmlBy(errorMessage);
			// 错误信息中包含
			if (errorMessage.contains(";*")) {
				errorMessage = errorMessage.split("\\;\\*")[1];
			}
			if (errorMessage.contains(LOGIN_USER_PASSWORD_LOCKED)) {
				loginResultVo.setErrorMessage(" 您已经连续5次输错密码，请您到众禄基金官网尝试“找回密码”功能重新设置你的密码。如有疑问请致电客服热线：4006-788-887。");
			} else {
				loginResultVo.setErrorMessage(errorMessage);
			}
		}
		return loginResultVo;
	}
}
