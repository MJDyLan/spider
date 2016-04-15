package com.kingdee.finance.julicai.spider.site.fund.zlfund.web.spider;

import java.util.Map;

import com.kingdee.finance.julicai.spider.base.BaseWebNormalSpider;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.constant.ZlfundWebConstants;
import com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model.ZlfundWebAssetsInfoModel;

/**
 * login model Created by 邱吉胜 on 2016年4月1日
 */
public final class ZlfundWebAssetsInfoSpider extends BaseWebNormalSpider<ZlfundWebAssetsInfoModel, String> {

	private static final String ASSETS_INFO_URL = ZlfundWebConstants.ASSETS_INFO_URL;

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
	public ZlfundWebAssetsInfoModel toModel(String resultStr, String regexModel) {
		System.err.println(resultStr);
		return ZlfundWebAssetsInfoModel.getInstanceByJson(resultStr);
	}

	@Override
	protected String getRequestUrl() {
		return ASSETS_INFO_URL + "?_" + System.currentTimeMillis();
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {

	}

	@Override
	protected boolean isHttpPost() {
		return false;
	}

	@Override
	protected void buildRequestHeader(Map<String, String> requestHeader) {
		requestHeader.put("Referer", "https://www.zlfund.cn/trade");
	}

}
