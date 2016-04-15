package com.kingdee.finance.julicai.spider.site.fund.tiantian.web.model;

import com.google.gson.JsonObject;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * 
 * @title 天天基金登录model
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月12日
 * @version 1.0
 */
// d={'num':0,'msg':'ok','risk':-1,'route':'https://trade4.1234567.com.cn/'}
public class TiantianWebLoginModel {
	private static String LOGIN_SUCCESS_FLAG = "ok";
	private String routeDomain = "";

	private String num;
	private String msg;
	private String risk;
	private String route;

	public static WebLoginResultVo getInstanceByJson(String jsonStr) {
		JsonObject loginJson = MyGsonUtils.jsonObjectBy(jsonStr);
		JsonObject loginJson2 = MyGsonUtils.jsonObjectBy(loginJson.get("d").getAsString());
		WebLoginResultVo loginResultVo = new WebLoginResultVo();
		if (loginJson2.get("msg").getAsString().equals(LOGIN_SUCCESS_FLAG)) {
			loginResultVo.setIsLoginSuccess(true);
			// 放置路由信息
			String routeDomain = loginJson2.get("route").getAsString();
			loginResultVo.setTempInfo(routeDomain);
			
		} else {
			String errorMessage = loginJson2.get("msg").getAsString();
			errorMessage = MyRegexHtmlUtils.removeHtmlBy(errorMessage);
			loginResultVo.setErrorMessage(errorMessage);
		}
		return loginResultVo;
	}

	public static WebLoginResultVo getInstanceByString(String result, String routeDomain) {
		String jsonStr = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
		WebLoginResultVo loginResultVo = new WebLoginResultVo();
		if (MyGsonUtils.isGoodJson(jsonStr)) {
			JsonObject model = MyGsonUtils.jsonObjectBy(jsonStr);
			if (model.get("msg").getAsString().equals(LOGIN_SUCCESS_FLAG)) {
				loginResultVo.setIsLoginSuccess(true);
				loginResultVo.setTempInfo(routeDomain);
			} else {
				String errorMessage = model.get("msg").getAsString();
				errorMessage = MyRegexHtmlUtils.removeHtmlBy(errorMessage);
				loginResultVo.setErrorMessage(errorMessage);
			}
		}
		return loginResultVo;
	}

	public String getNum() {
		return num;
	}

	public String getMsg() {
		return msg;
	}

	public String getRisk() {
		return risk;
	}

	public String getRoute() {
		return route;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "TiantianWebLoginModel [num=" + num + ", msg=" + msg + ", risk=" + risk + ", route=" + route + "]";
	}
}
