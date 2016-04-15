package com.kingdee.finance.julicai.spider.site.fund.tiantian.web.spider;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.kingdee.finance.julicai.spider.base.BaseWebNormalSpider;
import com.kingdee.finance.julicai.spider.site.fund.tiantian.web.model.TiantianWebAssetsInfoModel;
import com.kingdee.javacorelib.constant.net.ConstHttp;


/**
 * login model Created by 邱吉胜 on 2016年4月1日
 */
public final class TiantianWebAssetsInfoSpider extends BaseWebNormalSpider<TiantianWebAssetsInfoModel, String> {

	private String assetsUrl = StringUtils.EMPTY;

	public TiantianWebAssetsInfoSpider(String assetsUrl){
		this.assetsUrl = assetsUrl + "MyAssets/do.aspx/GetMyAssertInfo";
	}

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
	public TiantianWebAssetsInfoModel toModel(String resultStr, String regexModel) {
		return TiantianWebAssetsInfoModel.getInstanceByJson(resultStr);
	}

	@Override
	protected String getRequestUrl() {
		return assetsUrl;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {

	}

	@Override
	protected boolean isHttpPost() {
		return true;
	}

	@Override
	protected void buildRequestHeader(Map<String, String> requestHeader) {
		requestHeader.put(ConstHttp.REFERER, "https://trade4.1234567.com.cn/MyAssets/default");
		requestHeader.put(ConstHttp.CONTENT_TYPE, ConstHttp.CONTENT_TYPE_JSON);
	}

}
