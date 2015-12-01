package org.zerock.service;

import org.springframework.scheduling.annotation.Async;

public class AsyncService {
	
	@Async
    public void print(int count){
        System.out.println("start:" + count);    
        
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("end:" + count);
    }

}
