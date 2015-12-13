package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * QQ OAuth 서비스
 * @author 김아름
 *
 */
@Service
public class QQAuthService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void getUserInfo() {
		
		String url = "https://graph.qq.com/user/get_user_info?";
		String param = "oauth_consumer_key=100330589&access_token=37AB5D0F8DC5794234FD159DB007E977&openid=6253C692118780C4142B1206E2C5101B&format=json";
		
		Object test = new Object();
		test = restTemplate.getForObject(url+param, Object.class);
		System.out.println(test);
	}

}
