/**
*@author  jisheng
*@time 2016年1月8日上午12:14:41
*/
package com.zimu.spider.yirendai.app.service.myorder;

import java.util.Map;

import com.zimu.spider.yirendai.app.service.base.BaseService;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月8日上午12:14:41
 * @version 1.0
 */
public interface MyOrderListService extends BaseService {
	public String doMyOrderList();
	public void bulidRequestParam(Map<String,Object> requestMap);

}
