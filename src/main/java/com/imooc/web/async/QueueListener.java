package com.imooc.web.async;


import static org.mockito.Matchers.endsWith;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferrendResultHolder deferrendResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNumber = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果：" + orderNumber);
					deferrendResultHolder.getMap().get(orderNumber).setResult("place order success");
					System.out.println("ApplicationListener中的deferrendResultHolder" + deferrendResultHolder.hashCode());
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();;
	}
}
