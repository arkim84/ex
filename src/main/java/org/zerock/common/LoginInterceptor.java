package org.zerock.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zerock.common.annotation.LoginCheck;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	
		System.out.println("test");
		LoginCheck usingAuth = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);
		if(usingAuth != null){
			
		}
		
		return true;
		
	}
	
}
