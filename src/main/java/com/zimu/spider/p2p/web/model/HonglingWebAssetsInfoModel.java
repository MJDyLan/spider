package com.kingdee.finance.julicai.spider.site.p2p.hongling.web.model;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.javacorelib.helper.text.MyToStringHelper;
import com.kingdee.javacorelib.utils.datatype.MyDecimalConvertUtils;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;

import java.math.BigDecimal;

/**
 * assets info model
 * Created by hnusr on 2016/3/10.
 */
public final class HonglingWebAssetsInfoModel {

	private BigDecimal totalAssets = BigDecimal.ZERO;

	public HonglingWebAssetsInfoModel(String jsonStr) {
		JsonClass jsonClass = MyGsonUtils.toModel(jsonStr, JsonClass.class);
		if (jsonClass != null) {
			totalAssets = MyDecimalConvertUtils.decimalByDecimalText(jsonClass.TotalAmount);
		}
	}

	public static SpiderSiteAssetsInfoEntity toP2PUserAssetsInfoEntity(HonglingWebAssetsInfoModel assetsInfoModel) {
		SpiderSiteAssetsInfoEntity assetsInfoEntity = new SpiderSiteAssetsInfoEntity();
		if (assetsInfoModel == null) {
			return assetsInfoEntity;
		}
		assetsInfoEntity.setTotalAssets(assetsInfoModel.getTotalAssets());
		return assetsInfoEntity;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	@Override
	public String toString() {
		return MyToStringHelper.getInstance(this)
				.add("totalAssets", totalAssets)
				.toString();
	}

	private static class JsonClass {

		public String TotalAmount;

		@Override
		public String toString() {
			return MyToStringHelper.getInstance(this)
					.add("TotalAmount", TotalAmount)
					.toString();
		}
	}

}
