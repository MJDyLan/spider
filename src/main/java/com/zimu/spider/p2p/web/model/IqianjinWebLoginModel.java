package com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.model;

import com.kingdee.javacorelib.helper.text.MyToStringHelper;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title 爱钱进登录model
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class IqianjinWebLoginModel {

	private int code;
	private boolean success;
	private Bean bean;
	private String message;

	public static WebLoginResultVo getInstanceByJson(String jsonStr) {

		WebLoginResultVo loginResultVo = new WebLoginResultVo();

		IqianjinWebLoginModel loginModel = MyGsonUtils.toModel(jsonStr, IqianjinWebLoginModel.class);
		if (loginModel != null) {
			// 登录成功
			if (loginModel.success) {
				loginResultVo.setLoginSuccess(true);
			} else {
				loginResultVo.setIsNeedVerifyCode(true);
				String errorMsg = MyRegexHtmlUtils.removeHtmlByEndSymbol(loginModel.message);
				loginResultVo.setErrorMessage(errorMsg);
			}
		}
		return loginResultVo;
	}

	@Override
	public String toString() {
		return MyToStringHelper.getInstance(this)
				.add("code", code)
				.add("message", message)
				.add("bean", bean)
				.add("success", success)
				.toString();
	}

	private static class Bean {
		private long id;

		@Override
		public String toString() {
			return MyToStringHelper.getInstance(this)
					.add("id", id)
					.toString();
		}
	}
}
