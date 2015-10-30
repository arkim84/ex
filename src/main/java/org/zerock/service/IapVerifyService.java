package org.zerock.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * Apple IAP 영수증 검증
 *
 */
@Service
public class IapVerifyService {
    
	private static Logger logger = Logger.getLogger(IapVerifyService.class);
    
    public static final String VERIFICATION_URL_REAL = "https://buy.itunes.apple.com/verifyReceipt";
    public static final String VERIFICATION_URL_SANDBOX = "https://sandbox.itunes.apple.com/verifyReceipt";

    public int verifyApplePurchase(String receipt, boolean isTest) throws IOException {
		String returnedString;
		if (isTest){
		    returnedString = verifyAppleReceiptData(VERIFICATION_URL_SANDBOX, receipt);
		}else {
		    returnedString = verifyAppleReceiptData(VERIFICATION_URL_REAL, receipt);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode resultObject = mapper.readTree(returnedString);
		int resultStatus = Integer.parseInt(resultObject.get("status").toString());
		
		if(resultStatus == 0){
			
		}
		
		if (resultStatus != 0){
		    try {
				String decodedText = new String(Base64.decodeBase64(receipt.getBytes()), "UTF-8");
				if (decodedText != null && decodedText.startsWith("com.urus")) {
				    return -1;
				}
		    } catch (Exception base64DecodeException) {
		    	logger.error(base64DecodeException.getMessage());
		    }
		    return 0;
		
		}
		return 1;
    }

    private static String verifyAppleReceiptData(String urladdress, String receiptData) throws IOException {
		URL url = null;
		URLConnection conn = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		
		try{
		    String jsonData = "{" +
		                    "\"receipt-data\" : \"" + receiptData + "\"," +
		                 "}";
		    
		    url = new URL(urladdress);
		    conn = url.openConnection();
		    conn.setDoOutput(true);
		    osw = new OutputStreamWriter(conn.getOutputStream());
		    osw.write(jsonData);
		    osw.flush();
		
		    // Get the response
		    br = new BufferedReader(
		    new InputStreamReader(conn.getInputStream()));
		    String line;
		    sb = new StringBuffer();
		    while ((line = br.readLine()) != null) {
				// Process line...
				sb.append(line);
			    }
		} finally {
		    if (osw != null)
		    	osw.close();
		    if (br != null)
		    	br.close();
		}
		
		return sb.toString();
    }
}