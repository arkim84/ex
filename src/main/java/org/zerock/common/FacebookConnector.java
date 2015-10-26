package org.zerock.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.zerock.FacebookUserVO;

public class FacebookConnector {

	private static final Logger logger = LoggerFactory.getLogger(FacebookConnector.class);

	@Value("${facebook.redirect.uri}")
	private String redirectUri;
	
	@Autowired
	private FacebookConnectionFactory factory;
	
	@Autowired
	private OAuth2Parameters param;
	
	public String getAuthUrl(){
		OAuth2Operations operations = factory.getOAuthOperations();
		
		String authUrl = operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, param);
		
		return authUrl;
	}
	
	 public FacebookUserVO getFacebookUserInfo(String code) {
			OAuth2Operations oauthOperations = factory.getOAuthOperations();
			
			AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, redirectUri, null);
			String accessToken = accessGrant.getAccessToken();
			Long expireTime =  accessGrant.getExpireTime();
			if (expireTime != null && expireTime < System.currentTimeMillis()) {
				accessToken = accessGrant.getRefreshToken();
				logger.info("accessToken is expired. refresh token = {}" , accessToken);
			}
			
			Connection<Facebook> connection = factory.createConnection(accessGrant);
			Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();
			
			UserOperations userOperations = facebook.userOperations();
			FacebookProfile facebookProfile = null;
			try {
				facebookProfile = userOperations.getUserProfile();
			} catch (MissingAuthorizationException   e) {
				e.printStackTrace();
				// TO DO throw custom exception...
			} catch (ApiException  e) {
				e.printStackTrace();
				// TO DO throw custom exception...
			}
			
			FacebookUserVO vo = new FacebookUserVO(
					facebookProfile.getId()
					, facebookProfile.getName()
					, facebookProfile.getFirstName()
					, facebookProfile.getLastName());
			
			logger.info("Facebook user login is success. {}", vo.toString());
			return vo;
		}

}
