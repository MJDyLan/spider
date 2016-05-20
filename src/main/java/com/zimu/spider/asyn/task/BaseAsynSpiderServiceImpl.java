package com.zimu.spider.asyn.task;
/**
 * 异步抓取基类
 * 
 * @author jisheng
 *
 */
public abstract class BaseAsynSpiderServiceImpl implements IAsynSpiderService{

	public abstract void doBefore();
	
	@Override
	public void doSpider() {

	}
	public abstract void doAfter();

}
