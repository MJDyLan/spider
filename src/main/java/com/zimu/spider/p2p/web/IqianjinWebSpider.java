package com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.finance.julicai.db.entity.site.p2p.P2PUserWholeDataEntity;
import com.kingdee.finance.julicai.spider.interfaces.spider.IP2PSiteSpider;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.model.IqianjinWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.spider.IqianjinWebAssetsInfoSpider;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.spider.IqianjinWebLoginSpider;
import com.kingdee.javacorelib.constant.string.ConstString;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.vo.web.WebLoginResultVo;

import java.io.OutputStream;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title 爱钱进，需要验证码
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class IqianjinWebSpider implements IP2PSiteSpider {

	private IqianjinWebLoginSpider loginSpider = new IqianjinWebLoginSpider();
	private IqianjinWebAssetsInfoSpider assetsInfoSpider = new IqianjinWebAssetsInfoSpider();

	@Override
	public WebLoginResultVo doLogin(String userName, String password, String verifyCode) {
		boolean isSuccess = loginSpider.checkVerifyCodeIsSuccess(verifyCode);
		if (!isSuccess) {
			return WebLoginResultVo.getInstanceByVerifyError();
		}
		return loginSpider.doLogin(userName, password, verifyCode);
	}

	@Override
	public WebLoginResultVo doLogin(String userName, String password) {
		return this.doLogin(userName, password, ConstString.EMPTY);
	}

	@Override
	public void setIsDebug(boolean isDebug) {
		loginSpider.setIsDebug(isDebug);
		assetsInfoSpider.setIsDebug(isDebug);
	}

	@Override
	public P2PUserWholeDataEntity doPullUserWholeDataEntity() {
		IqianjinWebAssetsInfoModel assetsInfoModel = assetsInfoSpider.doParseModel();
		MyLogUtils.log(assetsInfoModel, true);
		P2PUserWholeDataEntity entity = new P2PUserWholeDataEntity();
		entity.setAssetsInfoEntity(IqianjinWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoModel));
		return entity;
	}

	@Override
	public SpiderSiteAssetsInfoEntity doPullUserAssetsInfoEntity() {
		IqianjinWebAssetsInfoModel assetsInfoModel = assetsInfoSpider.doParseModel();
		return IqianjinWebAssetsInfoModel.toP2PUserAssetsInfoEntity(assetsInfoModel);
	}

	@Override
	public OutputStream doPullVerifyCodeImageOutputStream() {
		return loginSpider.doPullVerifyCodeImageOutputStream();
	}

	@Override
	public boolean isNeedVerifyCode(String key) {
		return true;
	}

}
