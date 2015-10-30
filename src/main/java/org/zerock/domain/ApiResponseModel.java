package org.zerock.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponseModel {
    private Header header = new Header();
    private Object data = null;
    
    private Object lockObject = new Object();
    
    public ApiResponseModel() {
        header.resultCode = 0;
        header.resultMessage = "SUCCESS";
    }
    
    public ApiResponseModel(Integer resultCode, String resultMessage) {
        header.resultCode = resultCode;
        header.resultMessage = resultMessage;
    }
    
    public Header getHeader() {
        return header;
    }

    public Object getData() {
        return data;
    }
    
    public void setResultCode(Integer resultCode) {
        header.resultCode = resultCode;
    }
    
    public void setResultMessage(String resultMessage) {
        header.resultMessage = resultMessage;
    }

    public void put(Object data) {
        synchronized(lockObject){
            this.data = data;
        }
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        synchronized(lockObject){
            if(data == null || !(data instanceof Map)){
                data = new LinkedHashMap<String, Object>();
            }
            Map<String, Object> map = (Map<String, Object>)data;
            map.put(key, value);
        }
    }

    class Header {
        public Integer resultCode;
        public String resultMessage;
    }
}
