package org.zerock.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Object message) {
		
		System.out.println("send to RabbitMQ :: " +message);
	    
		rabbitTemplate.convertAndSend(message);
	}
}
