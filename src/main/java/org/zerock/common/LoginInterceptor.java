package org.zerock.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	
		System.out.println("preHandle call..");
		
//		FacebookConnector connector = new FacebookConnector();
//		String url = connector.getAuthUrl();
//		
//		System.out.println("url.. "+url);
		return true;
	}
	
}
