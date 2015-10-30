package org.zerock.domain;

import javax.validation.constraints.NotNull;

public class IabParamModel {
	
	@NotNull
	private String receiptData;			//������ ������
	
	@NotNull
	private String receiptEncodeData;	//Base64�� ��ȣȭ�� ������
	
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
