package org.zerock.service;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.IabParamModel;
import org.zerock.persistence.ReceiptDao;

/**
 * Android IAB 영수증 검증
 *
 */
@Service
public class IabVerifyService {
	
	private static Logger logger = Logger.getLogger(IabVerifyService.class);

    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String base64PublicKey = "testKey";
    
    @Autowired
    ReceiptDao receiptDao;

    public void verifyAndroidPurchase(IabParamModel param) {
    	
    	// 1. 영수증 검증
        boolean verified = false;
        if (!StringUtils.isEmpty(param.getReceiptEncodeData())) {
            PublicKey key = generatePublicKey(base64PublicKey);
            verified = verifyAndroidSignedData(key, param.getReceiptData(), param.getReceiptEncodeData());
            if (!verified) {
            	// TODO 검증 실패 에러코드 정의 및 Exception 발생
            }
        }
        
        // 2. 결제 이력 저장.
        receiptDao.insertReceiptData();
    }
    
    private PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] decodedKey = Base64.decodeBase64(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private boolean verifyAndroidSignedData(PublicKey publicKey, String receiptData, String receiptEncodeData) {
        Signature sig;
        try {
            sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(receiptData.getBytes());
            if (!sig.verify(Base64.decodeBase64(receiptEncodeData))) {
                return false;
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (InvalidKeyException e) {
            logger.error(e.getMessage());
        } catch (SignatureException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
