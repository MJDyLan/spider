package com.highpay.zoom.spider.site.asyn.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
