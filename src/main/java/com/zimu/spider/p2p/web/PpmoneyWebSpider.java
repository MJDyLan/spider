package com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.finance.julicai.db.entity.site.p2p.P2PUserWholeDataEntity;
import com.kingdee.finance.julicai.spider.interfaces.spider.IP2PSiteSpider;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model.PpmoneyWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.spider.PpmoneyWebAssetsInfoSpider;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.spider.PpmoneyWebLoginSpider;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

import java.io.OutputStream;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title ppmoney
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月6日
 */
public final class PpmoneyWebSpider implements IP2PSiteSpider {

	private PpmoneyWebLoginSpider loginSpider = new PpmoneyWebLoginSpider();
	private PpmoneyWebAssetsInfoSpider assetsInfoSpider = new PpmoneyWebAssetsInfoSpider();

	@Override
	public P2PUserWholeDataEntity doPullUserWholeDataEntity() {
		return null;
	}

	@Override
	public SpiderSiteAssetsInfoEntity doPullUserAssetsInfoEntity() {
		return PpmoneyWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoSpider.doParseModel());
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password, String verifyCode) {
		return loginSpider.doLogin(userName, password, verifyCode);
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password) {
		return this.doLogin(userName, password, null);
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
	public void setIsDebug(boolean isDebug) {
		loginSpider.setIsDebug(isDebug);
		assetsInfoSpider.setIsDebug(isDebug);
	}
}
