package com.ednomy.crm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.service.EdUserService;

@Component
public class UtilityClass {
	
	@Autowired
	private EdUserService edUserService;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	public boolean isNull(Object object) {
		return object == null;
	}

	public boolean isNotNull(Object object) {
		return object != null;
	}

	public boolean isEmpty(String string) {
		return string.trim().isEmpty();
	}

	public boolean isNotEmpty(String string) {
		return !string.trim().isEmpty();
	}

	public boolean isEmptyORNull(String string) {
		return (string == null || string.trim().isEmpty());
	}
	
	public void sendNullPointer(Object [] objects, String [] fieldNames) {
		int count = 0;
		for (Object object : objects) {
			if (object == null) {
				throw new EdnomyException(fieldNames[count] +" not found");
			}count++;
		}
	}
	
	public void sendNullPointer(Object object) {
		if (object == null) {throw new NullPointerException();}		
	}

	@SuppressWarnings("rawtypes")
	public boolean isLazy(Class myClass) {
		for (Field field : myClass.getDeclaredFields()) {
			Class type = field.getType();

			if (type.toString().contains("com.ednomy.crm")) {
				Annotation[] annotation = field.getAnnotations();
				String annotationString = Arrays.toString(annotation);
				if (annotationString.contains("fetch=LAZY")) {
					return true;
				}
			}
		}
		return false;
	}

	public String avoidDuplicate(String[] stringArray) {
		Set<String> set = new java.util.HashSet<String>();
		Collections.addAll(set, stringArray);
		return StringUtils.collectionToDelimitedString(set, ",", "", "");
	}
	
	public String md5(String value){
		return DigestUtils.md5Hex(value);
	}
	
	public String sha(String value){
//		return value;
		return DigestUtils.shaHex(value);
	}
	
	public String stringToBase64(String[] values){
		String complete = "";
		for (String value : values) {
			if (complete.equals("")) {
				complete +=value;
			}else{
				complete +=":"+value;
			}
		}
		return Base64Utils.encodeToString(complete.getBytes());
	}
	
	public String base64ToString(String value){
		byte[] bytes = Base64Utils.decodeFromString(value);
		return new String(bytes);
	}
	
	public String[] base64ToStrArr(String value){
		byte[] bytes = Base64Utils.decodeFromString(value);
		return StringUtils.delimitedListToStringArray(new String(bytes), ":");
	}

}
