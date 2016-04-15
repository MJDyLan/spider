package com.kingdee.finance.julicai.spider.site.p2p.iqianjin.web.model;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.javacorelib.helper.text.MyToStringHelper;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;

import java.math.BigDecimal;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title 资产model
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class IqianjinWebAssetsInfoModel {

	private int code;
	private boolean success;
	private Bean bean;
	private String message;

	public static IqianjinWebAssetsInfoModel getInstanceByJson(String resultStr) {
		return MyGsonUtils.toModel(resultStr, IqianjinWebAssetsInfoModel.class);
	}

	public static SpiderSiteAssetsInfoEntity toP2PUserAssetsInfoEntity(IqianjinWebAssetsInfoModel assetsInfoModel) {
		SpiderSiteAssetsInfoEntity assetsInfoEntity = new SpiderSiteAssetsInfoEntity();
		if (assetsInfoModel.bean != null) {
			assetsInfoEntity.setFrozenAssets(assetsInfoModel.bean.freeze);
			assetsInfoEntity.setWalletBalance(assetsInfoModel.bean.balanceMoney);
			assetsInfoEntity.setUsableBalance(assetsInfoModel.bean.available);
			assetsInfoEntity.setTotalIncome(assetsInfoModel.bean.nowProfit);
			assetsInfoEntity.setTotalAssets(assetsInfoModel.bean.totalAssets);
		}
		return assetsInfoEntity;
	}

	@Override
	public String toString() {
		return MyToStringHelper.getInstance(this)
				.add("code", code)
				.add("success", success)
				.add("bean", bean)
				.add("message", message)
				.toString();
	}

	private static class Bean {
		private BigDecimal addInterestTotal = BigDecimal.ZERO;
		private BigDecimal available = BigDecimal.ZERO;
		private String availableDesc;
		private BigDecimal award = BigDecimal.ZERO;
		private String awardDesc;
		private String balanceDesc;
		private BigDecimal balanceMoney = BigDecimal.ZERO;
		private BigDecimal demandAssets = BigDecimal.ZERO;
		private String demandAssetsDesc;
		private BigDecimal demandProfit = BigDecimal.ZERO;
		private String demandProfitDesc;
		private BigDecimal flagLastProfit = BigDecimal.ZERO;
		private BigDecimal freeze = BigDecimal.ZERO;
		private String freezeDesc;
		private BigDecimal lastProfit = BigDecimal.ZERO;
		private String lastProfitDesc;
		private BigDecimal loanAssets = BigDecimal.ZERO;
		private String loanAssetsDesc;
		private BigDecimal loanProfit = BigDecimal.ZERO;
		private String loanProfitDesc;
		private BigDecimal nowProfit = BigDecimal.ZERO;
		private String nowProfitDesc;
		private BigDecimal planAssets = BigDecimal.ZERO;
		private String planAssetsDesc;
		private BigDecimal planProfit = BigDecimal.ZERO;
		private String planProfitDesc;
		private BigDecimal rechargeAssets = BigDecimal.ZERO;
		private String rechargeAssetsDesc;
		private BigDecimal redbagAmount = BigDecimal.ZERO;
		private String redbagAmountDesc;
		private BigDecimal redbagtotal = BigDecimal.ZERO;
		private BigDecimal regularAssets = BigDecimal.ZERO;
		private String regularAssetsDesc;
		private BigDecimal regularProfit = BigDecimal.ZERO;
		private String regularProfitDesc;
		private BigDecimal serviceFeeAssets = BigDecimal.ZERO;
		private String serviceFeeAssetsDesc;
		private BigDecimal totalAssets = BigDecimal.ZERO;
		private String totalAssetsDesc;
		private BigDecimal withdrawAssets = BigDecimal.ZERO;
		private String withdrawAssetsDesc;
		private BigDecimal yield = BigDecimal.ZERO;
		private String yieldDesc;

		@Override
		public String toString() {
			return "Bean [addInterestTotal=" + addInterestTotal + ", available=" + available + ", availableDesc=" + availableDesc + ", award=" + award + ", awardDesc=" + awardDesc + ", balanceDesc="
					+ balanceDesc + ", balanceMoney=" + balanceMoney + ", demandAssets=" + demandAssets + ", demandAssetsDesc=" + demandAssetsDesc + ", demandProfit=" + demandProfit
					+ ", demandProfitDesc=" + demandProfitDesc + ", flagLastProfit=" + flagLastProfit + ", freeze=" + freeze + ", freezeDesc=" + freezeDesc + ", lastProfit=" + lastProfit
					+ ", lastProfitDesc=" + lastProfitDesc + ", loanAssets=" + loanAssets + ", loanAssetsDesc=" + loanAssetsDesc + ", loanProfit=" + loanProfit + ", loanProfitDesc=" + loanProfitDesc
					+ ", nowProfit=" + nowProfit + ", nowProfitDesc=" + nowProfitDesc + ", planAssets=" + planAssets + ", planAssetsDesc=" + planAssetsDesc + ", planProfit=" + planProfit
					+ ", planProfitDesc=" + planProfitDesc + ", rechargeAssets=" + rechargeAssets + ", rechargeAssetsDesc=" + rechargeAssetsDesc + ", redbagAmount=" + redbagAmount
					+ ", redbagAmountDesc=" + redbagAmountDesc + ", redbagtotal=" + redbagtotal + ", regularAssets=" + regularAssets + ", regularAssetsDesc=" + regularAssetsDesc + ", regularProfit="
					+ regularProfit + ", regularProfitDesc=" + regularProfitDesc + ", serviceFeeAssets=" + serviceFeeAssets + ", serviceFeeAssetsDesc=" + serviceFeeAssetsDesc + ", totalAssets="
					+ totalAssets + ", totalAssetsDesc=" + totalAssetsDesc + ", withdrawAssets=" + withdrawAssets + ", withdrawAssetsDesc=" + withdrawAssetsDesc + ", yield=" + yield + ", yieldDesc="
					+ yieldDesc + "]";
		}
	}

}
