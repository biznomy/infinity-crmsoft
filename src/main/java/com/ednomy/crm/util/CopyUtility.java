package com.ednomy.crm.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import com.ednomy.crm.exception.EdnomyException;

/**
 * @author technolabs1
 *
 */
@Component
public class CopyUtility {

	private String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public void copyUtil(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public void copyUtil(Object src, Object target, String[] ignoreFileds) {
		BeanUtils.copyProperties(src, target, ignoreFileds);		
	}

	public boolean isNullProperties(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		for (java.beans.PropertyDescriptor pd : pds) {
			if ("class".equalsIgnoreCase(pd.getName())) {
				continue;
			}
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue != null)
				return false;
		}
		return true;
	}

	/**
	 * @param source
	 * @param target
	 * @param fields
	 * @return
	 */
	public HashMap<String, Object> getPropertieValue(Object source, String[] fields) {

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		String fieldString = (String) Arrays.toString(fields);
		for (java.beans.PropertyDescriptor pd : pds) {

			if (fieldString.contains(pd.getName())) {
				Object srcValue = src.getPropertyValue(pd.getName());
				valueMap.put(pd.getName(), srcValue);
			}
		}
		return valueMap;
	}


	/**
	 * Use in EdcontentDataUpdateField Update Field of Object using field name
	 * and value
	 * 
	 * @param source
	 * @param fieldName
	 * @param fieldValue
	 * @return source updated object
	 */
	public Object getPropertieValue(Object source, String fieldName,
			String fieldValue) {

		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		for (java.beans.PropertyDescriptor pd : pds) {

			if (fieldName.equalsIgnoreCase(pd.getName())) {
				Object srcValue = src.getPropertyValue(pd.getName());
				try {
					if (srcValue instanceof String) {
						String value = (String) srcValue;
						if (value.contains(fieldValue)) {
							value = value.replace(fieldValue + ",", "");
						} else {
							value = value.concat(fieldValue + ",");
						}
						src.setPropertyValue(fieldName, value);
					} else if (srcValue == null) {
						src.setPropertyValue(fieldName, "," + fieldValue + ",");
					}
				} catch (ClassCastException castException) {
					throw new EdnomyException(castException);
				}
			}
		}
		
		return source;
		
	}
}
