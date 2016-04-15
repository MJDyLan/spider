package com.kingdee.finance.julicai.spider.site.p2p.hongling.web;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.finance.julicai.db.entity.site.p2p.P2PUserWholeDataEntity;
import com.kingdee.finance.julicai.spider.interfaces.spider.IP2PSiteSpider;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model.HonglingWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.parse.HonglingWebAssetsInfoSpider;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.parse.HonglingWebLoginSpider;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

import java.io.OutputStream;

/**
 * 红岭创投
 * <pre>
 * 1、不确定是否需要验证码，必须去读取网页时才知道
 * </pre>
 * Created by hnusr on 2016/3/6.
 */
public final class HonglingWebSpider implements IP2PSiteSpider {

	private final HonglingWebLoginSpider loginSpider = new HonglingWebLoginSpider();
	private final HonglingWebAssetsInfoSpider assetsInfoSpider = new HonglingWebAssetsInfoSpider();

	@Override
	public P2PUserWholeDataEntity doPullUserWholeDataEntity() {
		return null;
	}

	@Override
	public SpiderSiteAssetsInfoEntity doPullUserAssetsInfoEntity() {
		HonglingWebAssetsInfoModel assetsInfoModel = assetsInfoSpider.doParseModel();
		return HonglingWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoModel);
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password, String verifyCode) {
		return loginSpider.doLogin(userName, password, verifyCode);
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password) {
		return this.doLogin(userName, password, "");
	}

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return loginSpider.doPullVerifyCodeImageOutputStream();
	}

	@Override
	public boolean isNeedVerifyCode(String key) {
		return loginSpider.isNeedVerifyCodeByPullLoginPage();
	}

	/**
	 * 判断验证码是否正确
	 */
	public boolean doCheckVerifyCodeSuccess(String verifyCode) {
		return loginSpider.doCheckVerifyCodeSuccess(verifyCode);
	}

	@Override
	public void setIsDebug(boolean isDebug) {
		loginSpider.setIsDebug(isDebug);
	}
}
