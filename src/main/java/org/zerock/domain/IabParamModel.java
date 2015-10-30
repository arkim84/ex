package org.zerock.domain;

import javax.validation.constraints.NotNull;

public class IabParamModel {
	
	@NotNull
	private String receiptData;			//영수증 데이터
	
	@NotNull
	private String receiptEncodeData;	//Base64로 암호화된 데이터
	
	public String getReceiptData() {
		return receiptData;
	}
	public void setReceiptData(String receiptData) {
		this.receiptData = receiptData;
	}
	public String getReceiptEncodeData() {
		return receiptEncodeData;
	}
	public void setReceiptEncodeData(String receiptEncodeData) {
		this.receiptEncodeData = receiptEncodeData;
	}

}
