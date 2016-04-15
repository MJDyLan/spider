package com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model;

import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model.regex.HonglingWebLoginRegexModel;
import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexStringUtils;
import com.kingdee.javacorelib.utils.text.MyStringUtils;
import com.kingdee.javacorelib.vo.web.WebLoginErrorType;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * login result model
 * Created by hnusr on 2016/3/7.
 */
public final class HonglingWebLoginModel extends WebLoginResultVo {

	private static final String KEY_VERIFY_CODE = "验证码";
	private static final String KEY_USER_PASS = "密码";

	public static HonglingWebLoginModel getInstanceBy(String resultStr, HonglingWebLoginRegexModel regexModel) {

		HonglingWebLoginModel loginModel = new HonglingWebLoginModel();
		boolean loginSuccess = MyRegexStringUtils.getMatchCount(resultStr, regexModel.getLoginSuccessRegex()) > 0;
		if (loginSuccess) {
			loginModel.setIsLoginSuccess(true);
			return loginModel;
		}
		String errorMsg = MyRegexStringUtils.getMatchByGroupIndex(resultStr, regexModel.getErrorMsgRegex());
		errorMsg = MyRegexHtmlUtils.removeHtmlByNbsp(errorMsg);
		if (MyStringUtils.isNotEmpty(errorMsg)) {
			loginModel.setErrorMessage(errorMsg);
			// 用户名或密码错误
			if (errorMsg.contains(KEY_USER_PASS)) {
				loginModel.setErrorType(WebLoginErrorType.ERROR_USERNAME_OR_PASSWORD_TYPE);
			}
			// 请输入验证码
			else if (errorMsg.contains(KEY_VERIFY_CODE)) {
				loginModel.setErrorType(WebLoginErrorType.ERROR_VERIFY_TYPE);
				loginModel.setIsNeedVerifyCode(true);
			}
		}
		return loginModel;
	}
}
