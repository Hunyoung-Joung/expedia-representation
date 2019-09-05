package com.expedia.young.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthHeaderValueSingleton {

    private static AuthHeaderValueSingleton authHeaderValueSingleton = new AuthHeaderValueSingleton();
    
    private AuthHeaderValueSingleton(){}
    
    public static AuthHeaderValueSingleton getInstance(){
        return authHeaderValueSingleton;
    }
    
    public String getAuthHeaderValue() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	Date date= new java.util.Date();
    	Long timestamp = (date.getTime() / 1000);
    	String signature = null;
    	String authHeaderValue = null;
		String toBeHashed = com.expedia.young.demo.entity.keyInfo.getApiKey() + com.expedia.young.demo.entity.keyInfo.getSecret() + timestamp;
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] bytes = md.digest(toBeHashed.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++){
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		signature = sb.toString();
		authHeaderValue = "EAN APIKey=" + com.expedia.young.demo.entity.keyInfo.getApiKey() +  ",Signature=" + signature + ",timestamp=" + timestamp;

		return authHeaderValue;

	}
    
    public String getFormattedDate(String tagetDatte) {
    	String d = tagetDatte.split("/")[2]+"-"+tagetDatte.split("/")[0]+"-"+tagetDatte.split("/")[1];
		return d;
    }

}
