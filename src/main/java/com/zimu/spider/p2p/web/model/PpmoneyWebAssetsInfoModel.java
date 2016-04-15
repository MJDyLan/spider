package com.kingdee.finance.julicai.spider.site.p2p.ppmoney.web.model;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.javacorelib.helper.text.MyToStringHelper;

import java.math.BigDecimal;

/**
 * @author 邱吉胜
 * @version 1.0
 * @title 资产info model
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月5日
 */
public final class PpmoneyWebAssetsInfoModel {

	private BigDecimal walletBalance = BigDecimal.ZERO;
	private BigDecimal usableBalance = BigDecimal.ZERO;
	private BigDecimal frozenAssets = BigDecimal.ZERO;
	private BigDecimal totalAssets = BigDecimal.ZERO;
	private BigDecimal totalIncome = BigDecimal.ZERO;
	private BigDecimal todayIncome = BigDecimal.ZERO;

	public static SpiderSiteAssetsInfoEntity toP2PUserAssetsInfoEntity(PpmoneyWebAssetsInfoModel assetsInfoModel) {
		SpiderSiteAssetsInfoEntity assetsInfoEntity = new SpiderSiteAssetsInfoEntity();
		if (assetsInfoModel == null) {
			return assetsInfoEntity;
		}
		assetsInfoEntity.setTotalAssets(assetsInfoModel.getTotalAssets());
		assetsInfoEntity.setUsableBalance(assetsInfoModel.getUsableBalance());
		assetsInfoEntity.setWalletBalance(assetsInfoModel.getWalletBalance());
		assetsInfoEntity.setTodayIncome(assetsInfoModel.getTodayIncome());
		assetsInfoEntity.setTotalIncome(assetsInfoModel.getTotalIncome());
		assetsInfoEntity.setFrozenAssets(assetsInfoModel.getFrozenAssets());
		return assetsInfoEntity;
	}

	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}

	public BigDecimal getUsableBalance() {
		return usableBalance;
	}

	public void setUsableBalance(BigDecimal usableBalance) {
		this.usableBalance = usableBalance;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTodayIncome() {
		return todayIncome;
	}

	public void setTodayIncome(BigDecimal todayIncome) {
		this.todayIncome = todayIncome;
	}

	public BigDecimal getFrozenAssets() {
		return frozenAssets;
	}

	public void setFrozenAssets(BigDecimal frozenAssets) {
		this.frozenAssets = frozenAssets;
	}

	@Override
	public String toString() {
		return MyToStringHelper.getInstance(this)
				.add("walletBalance", walletBalance)
				.add("usableBalance", usableBalance)
				.add("frozenAssets", frozenAssets)
				.add("totalAssets", totalAssets)
				.add("totalIncome", totalIncome)
				.add("todayIncome", todayIncome)
				.toString();
	}
}
