package com.zimu.spider.tiantian.web.service;

import com.zimu.spider.base.inter.IBaseLoginSevice;

public interface TiantianWebLoginService extends IBaseLoginSevice<String>{
	void addHeader();
	void doBefore();

}
