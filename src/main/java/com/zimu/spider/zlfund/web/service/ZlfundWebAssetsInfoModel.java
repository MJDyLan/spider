package com.kingdee.finance.julicai.spider.site.fund.zlfund.web.model;

import java.math.BigDecimal;

import com.kingdee.finance.julicai.db.entity.site.SpiderSiteAssetsInfoEntity;
import com.kingdee.javacorelib.utils.json.MyGsonUtils;

/**
 * login model Created by 邱吉胜 on 2016年4月1日
 */
// {"has_partner": true, "has_open_pzb": false, "balance_items": [{"product":
// {"can_buy": true, "tenthou_unit_incm": 0.63249999999999995, "code": "161608",
// "name_abbr": "\u878d\u901a\u6613\u652f\u4ed8\u8d27\u5e01A", "name":
// "\u878d\u901a\u6613\u652f\u4ed8\u8d27\u5e01\u5e02\u573a\u8bc1\u5238\u6295\u8d44\u57fa\u91d1",
// "invest_type": {"id": 4, "display": "\u8d27\u5e01\u578b"}, "zltype": ["OPN",
// "MON", "FND", "MONA", "ZLPAY"], "year_yield_by7d": 2.3849999999999998},
// "can_redeem": true, "dayprofit": 0.0, "unprofit": 0.0, "market_value": 2.0,
// "can_convert": true, "cost": 5.0, "id": "1000265209161608", "tradedate":
// 1460044800, "profit": 20.0, "unit_net": 1.0, "melonmd": {"id": "0",
// "display": "\u7ea2\u5229\u518d\u6295"}, "trade_account": {"redfromzl": "Y",
// "bankname": "\u5149\u5927\u94f6\u884c", "ispartner": true, "partnerno":
// "0011", "bankacco": "6226620407396324", "id": "1000265209", "mctname":
// "\u968f\u624b\u7f51"}, "avaliable": 2.0, "balance": 2.0}, {"product":
// {"can_buy": true, "tenthou_unit_incm": 0.69240000000000002, "code": "482002",
// "name_abbr": "\u5de5\u94f6\u8d27\u5e01", "name":
// "\u5de5\u94f6\u745e\u4fe1\u8d27\u5e01\u5e02\u573a\u57fa\u91d1",
// "invest_type": {"id": 4, "display": "\u8d27\u5e01\u578b"}, "zltype": ["OPN",
// "MON", "FND"], "year_yield_by7d": 2.6389999999999998}, "can_redeem": true,
// "dayprofit": 0.0, "unprofit": 0.0, "market_value": 0.16, "can_convert": true,
// "cost": 147.0, "id": "1000265209482002", "tradedate": 1460044800, "profit":
// 0.17999999999999999, "unit_net": 1.0, "melonmd": {"id": "0", "display":
// "\u7ea2\u5229\u518d\u6295"}, "trade_account": {"redfromzl": "Y", "bankname":
// "\u5149\u5927\u94f6\u884c", "ispartner": true, "partnerno": "0011",
// "bankacco": "6226620407396324", "id": "1000265209", "mctname":
// "\u968f\u624b\u7f51"}, "avaliable": 0.16, "balance": 0.16}, {"product":
// {"can_buy": true, "tenthou_unit_incm": 0.51290000000000002, "code": "110006",
// "name_abbr": "\u6613\u65b9\u8fbe\u8d27\u5e01A", "name":
// "\u6613\u65b9\u8fbe\u8d27\u5e01\u5e02\u573a\u57fa\u91d1", "invest_type":
// {"id": 4, "display": "\u8d27\u5e01\u578b"}, "zltype": ["OPN", "MON", "FND",
// "MONA"], "year_yield_by7d": 2.4129999999999998}, "can_redeem": true,
// "dayprofit": 0.0, "unprofit": 0.01, "market_value": 1.0, "can_convert": true,
// "cost": 1.0, "id": "1000265209110006", "tradedate": 1460044800, "profit":
// 0.01, "unit_net": 1.0, "melonmd": {"id": "0", "display":
// "\u7ea2\u5229\u518d\u6295"}, "trade_account": {"redfromzl": "Y", "bankname":
// "\u5149\u5927\u94f6\u884c", "ispartner": true, "partnerno": "0011",
// "bankacco": "6226620407396324", "id": "1000265209", "mctname":
// "\u968f\u624b\u7f51"}, "avaliable": 1.0, "balance": 1.0}], "summary":
// {"MERCHANT": {"market_value": 3.1600000000000001, "item_refs":
// ["1000265209161608", "1000265209482002", "1000265209110006"], "profit":
// 20.190000000000001, "dayprofit": 0, "latest_tradedate": 1460044800,
// "unprofit": 0.01, "incomeamt": 0.01, "latest_dayprofit": 0.0, "balance":
// 3.1600000000000001}, "zyb": {"market_value": 0, "expprofit": 0, "profit": 0,
// "dayprofit": 0, "unprofit": 0, "incomeamt": 0, "avaliable": 0,
// "zlpay_compensate": 0, "latest_dayprofit": 0, "balance": 0}, "fund":
// {"market_value": 0, "profit": 0, "dayprofit": 0, "unprofit": 0, "incomeamt":
// 0, "latest_dayprofit": 0, "": 0}, "zlpay": {"market_value": 0, "profit": 0,
// "dayprofit": 0, "latest_tradedate": null, "unprofit": 0, "incomeamt": 0,
// "avaliable": 0, "latest_dayprofit": 0, "balance": 0}, "total":
// {"market_value": 3.1600000000000001, "item_refs": ["1000265209161608",
// "1000265209482002", "1000265209110006"], "profit": 20.190000000000001,
// "dayprofit": 0, "latest_tradedate": 1460044800, "unprofit": 0.01,
// "incomeamt": 0.01, "latest_dayprofit": 0.0, "balance": 3.1600000000000001},
// "cfp": {"market_value": 0, "profit": 0, "dayprofit": 0, "unprofit": 0,
// "incomeamt": 0, "latest_dayprofit": 0, "balance": 0}, "pzb": {"market_value":
// 0, "profit": 0, "dayprofit": 0, "unprofit": 0, "incomeamt": 0,
// "latest_dayprofit": 0, "balance": 0}}}
public class ZlfundWebAssetsInfoModel {

	private Summary summary;

	public ZlfundWebAssetsInfoModel() {

	}

	public static ZlfundWebAssetsInfoModel getInstanceByJson(String resultStr) {
		return MyGsonUtils.toModel(resultStr, ZlfundWebAssetsInfoModel.class);
	}

	public static SpiderSiteAssetsInfoEntity toP2PUserAssetsInfoEntity(ZlfundWebAssetsInfoModel assetsInfoModel) {
		SpiderSiteAssetsInfoEntity assetsInfoEntity = new SpiderSiteAssetsInfoEntity();
		if (assetsInfoModel != null) {
			assetsInfoEntity.setTotalAssets(assetsInfoModel.getSummary().getTotal().getBalance());
			assetsInfoEntity.setTotalIncome(assetsInfoModel.getSummary().getTotal().getProfit());
			assetsInfoEntity.setTodayIncome(assetsInfoModel.getSummary().getTotal().getDayprofit());
		}
		return assetsInfoEntity;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	class Summary {
		private Total total;

		public Total getTotal() {
			return total;
		}

		public void setTotal(Total total) {
			this.total = total;
		}

		@Override
		public String toString() {
			return "Summary [total=" + total + "]";
		}
	}

	class Total {
		private BigDecimal balance = BigDecimal.ZERO;
		private BigDecimal dayprofit = BigDecimal.ZERO;
		private BigDecimal incomeamt = BigDecimal.ZERO;
		private BigDecimal latest_dayprofit = BigDecimal.ZERO;
		private String latest_tradedate;
		private BigDecimal market_value = BigDecimal.ZERO;
		private BigDecimal profit = BigDecimal.ZERO;
		private BigDecimal unprofit = BigDecimal.ZERO;

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}

		public BigDecimal getDayprofit() {
			return dayprofit;
		}

		public void setDayprofit(BigDecimal dayprofit) {
			this.dayprofit = dayprofit;
		}

		public BigDecimal getIncomeamt() {
			return incomeamt;
		}

		public void setIncomeamt(BigDecimal incomeamt) {
			this.incomeamt = incomeamt;
		}

		public BigDecimal getLatest_dayprofit() {
			return latest_dayprofit;
		}

		public void setLatest_dayprofit(BigDecimal latest_dayprofit) {
			this.latest_dayprofit = latest_dayprofit;
		}

		public String getLatest_tradedate() {
			return latest_tradedate;
		}

		public void setLatest_tradedate(String latest_tradedate) {
			this.latest_tradedate = latest_tradedate;
		}

		public BigDecimal getMarket_value() {
			return market_value;
		}

		public void setMarket_value(BigDecimal market_value) {
			this.market_value = market_value;
		}

		public BigDecimal getProfit() {
			return profit;
		}

		public void setProfit(BigDecimal profit) {
			this.profit = profit;
		}

		public BigDecimal getUnprofit() {
			return unprofit;
		}

		public void setUnprofit(BigDecimal unprofit) {
			this.unprofit = unprofit;
		}

		@Override
		public String toString() {
			return "Total [balance=" + balance + ", dayprofit=" + dayprofit + ", incomeamt=" + incomeamt + ", latest_dayprofit=" + latest_dayprofit + ", latest_tradedate=" + latest_tradedate
					+ ", market_value=" + market_value + ", profit=" + profit + ", unprofit=" + unprofit + "]";
		}
	}
}
