package com.kingdee.finance.julicai.spider.site.fund.tiantian.web;

import java.io.OutputStream;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.finance.julicai.spider.interfaces.spider.IFundSiteSpider;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.model.TiantianWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.spider.TiantianWebAssetsInfoSpider;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.spider.TiantianWebLoginSpider;
import com.kingdee.javacorelib.constant.string.ConstString;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * 
 * @title 天天基金
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月13日
 * @version 1.0
 */
public final class TiantianWebSpider implements IFundSiteSpider {

	private TiantianWebLoginSpider loginSpider = new TiantianWebLoginSpider();

	private TiantianWebAssetsInfoSpider assetsInfoSpider = null;

	@Override
	public WebLoginResultVo doLogin(String userName, String password, String verifyCode) {
		WebLoginResultVo vo = loginSpider.doLogin(userName, password, verifyCode);
		if (vo.isLoginSuccess()) {
			String routeDomain = vo.getTempInfo();
			assetsInfoSpider = new TiantianWebAssetsInfoSpider(routeDomain);
		}
		return vo;
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password) {
		return this.doLogin(userName, password, ConstString.EMPTY);
	}

	@Override
	public void setIsDebug(boolean isDebug) {
		loginSpider.setIsDebug(isDebug);
	}

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return loginSpider.doPullVerifyCodeImageOutputStream();
	}

	@Override
	public boolean isNeedVerifyCode(String key) {
		return false;
	}

	@Override
	public SpiderSiteAssetsInfoEntity doPullUserAssetsInfoEntity() {
		TiantianWebAssetsInfoModel assetsInfoModel = assetsInfoSpider.doParseModel();
		return TiantianWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoModel);
	}
}
