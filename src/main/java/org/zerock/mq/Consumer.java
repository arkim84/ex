package org.zerock.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	//�޽����� ó���Ѵ�.
    public void handleMessage(Object message) {
    	
    	logger.info("receive from RabbitMQ :: " +message);
    }
}
