package com.highpay.zoom.spider.site.asyn.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @title 这里最好使用有界的线程池，防止出现内存溢出
 * 
 * @author jsonChiu
 * @time 2016年5月21日 下午10:31:51
 * @version 1.0
 */
public class AsynSpiderExecutorService {
	private final ExecutorService executor = Executors.newCachedThreadPool();
	
	public void doSpider(final IAsynSpiderService spider){
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				spider.doSpider();
			}
		};
		executor.execute(task);
	}
}
