package com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.spider;

import com.kingdee.finance.julicai.spider.base.BaseWebNormalSpider;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.constant.IqianjinWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.model.IqianjinWebAssetsInfoModel;

import java.util.Map;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title 爱钱进资产抓取
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class IqianjinWebAssetsInfoSpider extends BaseWebNormalSpider<IqianjinWebAssetsInfoModel, String> {

	private static final String ASSETS_INFO_URL = IqianjinWebConst.ASSETS_INFO_URL;

	@Override
	protected void preCleanHtml(String html) {
	}

	@Override
	protected String cleanHtml(String html) {
		return html;
	}

	@Override
	protected String queryWebParseRegexModel() {
		return null;
	}

	@Override
	public IqianjinWebAssetsInfoModel toModel(String resultStr, String regexModel) {
		return IqianjinWebAssetsInfoModel.getInstanceByJson(resultStr);
	}

	@Override
	protected String getRequestUrl() {
		return ASSETS_INFO_URL + "?_" + System.currentTimeMillis();
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {

	}

}
