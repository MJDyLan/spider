package com.kingdee.finance.julicai.spider.site.fund.zlfund.web;

import java.io.OutputStream;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.finance.julicai.spider.interfaces.spider.IFundSiteSpider;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model.ZlfundWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.spider.ZlfundWebAssetsInfoSpider;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.spider.ZlfundWebLoginSpider;
import com.kingdee.javacorelib.constant.string.ConstString;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

/**
 * 众禄基金web
 * <p/>
 * 
 * <pre>
 * 1、众禄基金抓取的入口类
 * 2、黑认不需要验证码
 * </pre>
 * <p/>
 * Created by 邱吉胜 on 2016年4月1日
 */
public final class ZlfundWebSpider implements IFundSiteSpider {

	private static final ZlfundWebLoginSpider loginSpider = new ZlfundWebLoginSpider();

	private static final ZlfundWebAssetsInfoSpider assetsInfoSpider = new ZlfundWebAssetsInfoSpider();

	@Override
	public WebLoginResultVo doLogin(String userName, String password, String verifyCode) {
		return loginSpider.doLogin(userName, password, verifyCode);
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
		ZlfundWebAssetsInfoModel assetsInfoModel = assetsInfoSpider.doParseModel();
		return ZlfundWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoModel);
	}
}
