package com.zimu.spider.niwodai.web.service;

import java.util.Map;

import com.zimu.spider.base.inter.IBaseLoginSevice;

public interface NiwodaiWebLoginService extends IBaseLoginSevice<String>{
	//发送请求时，是否特殊的请求头参数
	public void buildHeader(Map<String,Object> headerMap);

}
