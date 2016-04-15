package com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model;

import com.kingdee.javacorelib.helper.text.MyToStringHelper;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;
import com.kingdee.javacorelib.vo.web.WebLoginErrorType;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * login model Created by 邱吉胜 on 2016年4月1日
 */
// {"band_profit": null, "risklevel": "\u4fdd\u5b88\u578b", "band_displayst":
// null, "needcomplete": false, "has_consultant": false, "band_person": null,
// "id": "1000431895", "custno_base64": "MTAwMDQzMTg5NQ==", "name":
// "\u90b1\u5409\u80dc", "band_bonus": null, "bankcards_count": 1, "logged":
// true, "mobile": "13349910969", "last_login": 1459491457, "security_level": 3,
// "mobile_verified": true}
public class ZlfundWebUserInfoModel {
	private String band_profit;
	private String risklevel;
	private String band_displayst;
	private String needcomplete;
	private boolean has_consultant;
	private String band_person;
	private String id;
	private String custno_base64;
	private String name;
	private String band_bonus;
	private String bankcards_count;
	private boolean logged;
	private String mobile;
	private String last_login;
	private String security_level;
	private String mobile_verified;

	public ZlfundWebUserInfoModel() {

	}

	public static ZlfundWebUserInfoModel getInstanceByJson(String resultStr, String test) {
		ZlfundWebUserInfoModel model = MyGsonUtils.toModel(resultStr, ZlfundWebUserInfoModel.class);
		return model;
	}
	public static WebLoginResultVo getInstanceByJson(String resultStr) {
		WebLoginResultVo loginResultVo = new WebLoginResultVo();

		ZlfundWebUserInfoModel model = MyGsonUtils.toModel(resultStr, ZlfundWebUserInfoModel.class);
		if (model == null) {
			return loginResultVo;
		}
		// 登录成功
		if (model.logged) {
			loginResultVo.setIsLoginSuccess(true);
			loginResultVo.setToken(model.name);
		}
		// 登录失败
		else {
			loginResultVo.setIsLoginSuccess(false);
			loginResultVo.setErrorType(WebLoginErrorType.ERROR_USERNAME_OR_PASSWORD_TYPE);
			loginResultVo.setErrorMessage(WebLoginErrorType.ERROR_USERNAME_OR_PASSWORD_TYPE_TEXT);
		}
		return loginResultVo;
	}

	public String getBand_profit() {
		return band_profit;
	}

	public String getRisklevel() {
		return risklevel;
	}

	public String getBand_displayst() {
		return band_displayst;
	}

	public String getNeedcomplete() {
		return needcomplete;
	}

	public boolean isHas_consultant() {
		return has_consultant;
	}

	public String getBand_person() {
		return band_person;
	}

	public String getId() {
		return id;
	}

	public String getCustno_base64() {
		return custno_base64;
	}

	public String getName() {
		return name;
	}

	public String getBand_bonus() {
		return band_bonus;
	}

	public String getBankcards_count() {
		return bankcards_count;
	}

	public boolean isLogged() {
		return logged;
	}

	public String getMobile() {
		return mobile;
	}

	public String getLast_login() {
		return last_login;
	}

	public String getSecurity_level() {
		return security_level;
	}

	public String getMobile_verified() {
		return mobile_verified;
	}

	public void setBand_profit(String band_profit) {
		this.band_profit = band_profit;
	}

	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
	}

	public void setBand_displayst(String band_displayst) {
		this.band_displayst = band_displayst;
	}

	public void setNeedcomplete(String needcomplete) {
		this.needcomplete = needcomplete;
	}

	public void setHas_consultant(boolean has_consultant) {
		this.has_consultant = has_consultant;
	}

	public void setBand_person(String band_person) {
		this.band_person = band_person;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCustno_base64(String custno_base64) {
		this.custno_base64 = custno_base64;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBand_bonus(String band_bonus) {
		this.band_bonus = band_bonus;
	}

	public void setBankcards_count(String bankcards_count) {
		this.bankcards_count = bankcards_count;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public void setSecurity_level(String security_level) {
		this.security_level = security_level;
	}

	public void setMobile_verified(String mobile_verified) {
		this.mobile_verified = mobile_verified;
	}

	@Override
	public String toString() {
		return MyToStringHelper.getInstance(this)
				.add("band_profit", band_profit)
				.add("risklevel", risklevel)
				.add("band_displayst", band_displayst)
				.add("needcomplete", needcomplete)
				.add("has_consultant", has_consultant)
				.add("band_person", band_person)
				.add("id", id)
				.add("custno_base64", custno_base64)
				.add("name", name)
				.add("band_bonus", band_bonus)
				.add("bankcards_count", bankcards_count)
				.add("logged", logged)
				.add("mobile", mobile)
				.add("last_login", last_login)
				.add("security_level", security_level)
				.add("mobile_verified", mobile_verified)
				.toString();
	}

}
