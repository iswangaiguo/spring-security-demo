package com.imooc.web.async;

import java.util.Random;
import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferrendResultHolder deferrendResultHolder; 
	
	@RequestMapping("/order")
	public DeferredResult<String> order() throws InterruptedException {
		logger.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferrendResultHolder.getMap().put(orderNumber, result);
		System.out.println("controller中的deferrendResultHolder" + deferrendResultHolder.hashCode());
//		Callable<String> result = new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程结束");
//				return "success";
//			}
//		};
		logger.info("主线程返回");
		return result;
	}
	
}
