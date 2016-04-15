package com.kingdee.finance.julicai.spider.site.p2p.hongling.web.parse;

import com.kingdee.finance.julicai.spider.base.BaseWebNormalSpider;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.constant.HonglingWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model.HonglingWebAssetsInfoModel;
import com.kingdee.javacorelib.constant.string.ConstString;

import java.util.Map;

/**
 * assets info spider
 * Created by hnusr on 2016/3/10.
 */
public final class HonglingWebAssetsInfoSpider extends BaseWebNormalSpider<HonglingWebAssetsInfoModel, String> {

	private static final String ASSETS_INFO_URL = HonglingWebConst.ASSETS_INFO_URL;

	@Override
	protected void preCleanHtml(String html) {

	}

	@Override
	protected String cleanHtml(String html) {
		return html;
	}

	@Override
	protected String queryWebParseRegexModel() {
		return ConstString.EMPTY;
	}

	@Override
	protected HonglingWebAssetsInfoModel toModel(String html, String regexModel) {
		return new HonglingWebAssetsInfoModel(html);
	}

	@Override
	protected String getRequestUrl() {
		return ASSETS_INFO_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {

	}
}
