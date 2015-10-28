package org.zerock.mq;

import org.springframework.stereotype.Component;

@Component
public class Consumer {

	//메시지를 처리한다.
    public void handleMessage(Object message) {
        //do Something
    }
}
