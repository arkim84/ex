package org.zerock.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.ApiResponseModel;
import org.zerock.domain.IabParamModel;
import org.zerock.service.IabVerifyService;
import org.zerock.service.IapVerifyService;

@Controller
public class VerificationController {
	
	@Autowired
	private IabVerifyService iabVerifyService;
	
	@Autowired
	private IapVerifyService iapVerifyService;
	
	/**
	 * Android ���� �������� �����ϰ� ���� �̷��� �����Ѵ�.
	 */
	@RequestMapping(value="/verify/android", method=RequestMethod.POST)
	public @ResponseBody ApiResponseModel verifyAndroidPurchase(@RequestBody @Valid IabParamModel param) throws Exception{
	
		ApiResponseModel resultMap = new ApiResponseModel();
        
		iabVerifyService.verifyAndroidPurchase(param);
        
        return resultMap;
	}
	
	/**
	 * Apple ���� �������� �����ϰ� ���� �̷��� �����Ѵ�.
	 */
	@RequestMapping(value="/verify/apple", method=RequestMethod.POST)
	public @ResponseBody ApiResponseModel verifyApplePurchase(@RequestBody String receipt) throws Exception{
		
		ApiResponseModel resultMap = new ApiResponseModel();
        
		iapVerifyService.verifyApplePurchase(receipt, true);
		
        return resultMap; 
	}

}
