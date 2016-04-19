package com.zimu.spider.base.inter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public interface IBaseLoginSevice<T> {
	
	public void doBefore();
	
	public void buildHeader(Map<String,Object> header);
	
	public T toModel(String resultStr);
	
	public boolean isHttps();
	
	public boolean isRedirect302();
}
