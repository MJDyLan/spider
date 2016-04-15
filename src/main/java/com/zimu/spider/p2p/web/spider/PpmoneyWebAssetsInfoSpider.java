package com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.spider;

import com.kingdee.finance.julicai.spider.base.BaseWebNormalSpider;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.constant.PpmoneyWebConst;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model.PpmoneyWebAssetsInfoModel;
import com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model.regex.PpmoneyWebAssetsInfoRegexModel;
import com.kingdee.javacorelib.utils.datatype.MyDecimalConvertUtils;
import com.kingdee.javacorelib.utils.log.MyLogUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexHtmlUtils;
import com.kingdee.javacorelib.utils.regex.MyRegexStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.Map;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title ppmoney资产
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class PpmoneyWebAssetsInfoSpider extends BaseWebNormalSpider<PpmoneyWebAssetsInfoModel, PpmoneyWebAssetsInfoRegexModel> {

	private static final String ASSETS_INFO_URL = PpmoneyWebConst.ASSETS_INFO_URL;

	private static final String TOTAL_ASSETS_REGEX = "个人净资产.元.(.*?)待";
	private static final String TOTAL_INCOME_REGEX = "累计收益.元.(.*?)我";
	private static final String WALLET_BALANCE_REGEX = "账户余额.(.*?)赚";
	private static final String[] REMOVE_TAGS = new String[]{"a", "b", "p", "div", "span", "li"};

	@Override
	protected void preCleanHtml(String html) {

	}

	@Override
	protected String cleanHtml(String html) {
		Whitelist whitelist = Whitelist.none();
		whitelist.removeTags(REMOVE_TAGS);
		html = Jsoup.clean(html, whitelist);
		html = MyRegexHtmlUtils.removeHtmlByBlank(html);
		return html;
	}

	@Override
	protected PpmoneyWebAssetsInfoRegexModel queryWebParseRegexModel() {
		PpmoneyWebAssetsInfoRegexModel regexModel = new PpmoneyWebAssetsInfoRegexModel();
		regexModel.setTotalIncomeRegex(TOTAL_INCOME_REGEX);
		regexModel.setWalletBalanceRegex(WALLET_BALANCE_REGEX);
		regexModel.setTotalAssetsRegex(TOTAL_ASSETS_REGEX);
		return regexModel;
	}

	@Override
	protected PpmoneyWebAssetsInfoModel toModel(String html, PpmoneyWebAssetsInfoRegexModel regexModel) {

		MyLogUtils.log(html, isDebug());

		String totalIncomeStr = MyRegexStringUtils.getFirstMatch(html, regexModel.getTotalIncomeRegex());
		String walletBalanceStr = MyRegexStringUtils.getFirstMatch(html, regexModel.getWalletBalanceRegex());
		String totalAssetsStr = MyRegexStringUtils.getFirstMatch(html, regexModel.getTotalAssetsRegex());

		PpmoneyWebAssetsInfoModel model = new PpmoneyWebAssetsInfoModel();
		model.setWalletBalance(MyDecimalConvertUtils.decimalByDecimalText(walletBalanceStr));
		model.setTotalAssets(MyDecimalConvertUtils.decimalByDecimalText(totalAssetsStr));
		model.setTotalIncome(MyDecimalConvertUtils.decimalByDecimalText(totalIncomeStr));

		return model;
	}

	@Override
	protected String getRequestUrl() {
		return ASSETS_INFO_URL;
	}

	@Override
	protected void buildRequestParam(Map<String, String> requestParam) {

	}
}
