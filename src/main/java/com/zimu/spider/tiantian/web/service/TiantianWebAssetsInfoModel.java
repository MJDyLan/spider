package com.kingdee.finance.julicai.spider.site.fund.tiantian.web.model;

import java.math.BigDecimal;

import com.google.gson.JsonObject;
import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;

//d={'CashBagVol':'16.78','CashBagDailyIncome':'0.00','CashBagFloatingIncome':'14.79','FixedBagVol':'0.00',
//'FixedBagFloatingIncome':'0.00','IndexBagVol':'0.00','IndexBagFloatingIncome':'0.00','NormalFundVol':'0.00',
//'NormalFundFloatingIncome':'0.00','HighEndProductVol':'0.00','TotalAssetVol':'16.78','Loan':'0.00'}
public class TiantianWebAssetsInfoModel {

	private BigDecimal totalAssetVol = BigDecimal.ZERO;

	public static TiantianWebAssetsInfoModel getInstanceByJson(String jsonStr) {
		TiantianWebAssetsInfoModel model = new TiantianWebAssetsInfoModel();
		JsonObject loginJson = MyGsonUtils.jsonObjectBy(jsonStr);
		JsonObject loginJson2 = MyGsonUtils.jsonObjectBy(loginJson.get("d").getAsString());
		BigDecimal totalAssetVol = loginJson2.get("TotalAssetVol").getAsBigDecimal();
		model.setTotalAssetVol(totalAssetVol);
		return model;
	}

	public static SpiderSiteAssetsInfoEntity toP2PUserAssetsInfoEntity(TiantianWebAssetsInfoModel assetsInfoModel) {
		SpiderSiteAssetsInfoEntity assetsInfoEntity = new SpiderSiteAssetsInfoEntity();
		assetsInfoEntity.setTotalAssets(assetsInfoModel.getTotalAssetVol());
		return assetsInfoEntity;
	}

	public BigDecimal getTotalAssetVol() {
		return totalAssetVol;
	}

	public void setTotalAssetVol(BigDecimal totalAssetVol) {
		this.totalAssetVol = totalAssetVol;
	}
}
