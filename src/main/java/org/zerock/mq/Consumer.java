package org.zerock.mq;

import org.springframework.stereotype.Service;

@Service
public class Consumer {

	//�޽����� ó���Ѵ�.
    public void handleMessage(Object message) {
    	
    	System.out.println("receive from RabbitMQ :: " +message);
        //do Something
    }
}
