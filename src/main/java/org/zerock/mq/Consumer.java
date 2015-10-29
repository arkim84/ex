package org.zerock.mq;

import org.springframework.stereotype.Service;

@Service
public class Consumer {

	//메시지를 처리한다.
    public void handleMessage(Object message) {
    	
    	System.out.println("receive from RabbitMQ :: " +message);
        //do Something
    }
}
