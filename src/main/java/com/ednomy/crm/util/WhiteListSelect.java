package com.ednomy.crm.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WhiteListSelect {
	
	/**
	 * copy single level whitelist using String with nested json String
	 * @param source
	 * @param target
	 * @param fileData
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public void extractNested(Object source, Object target, String fileData) throws IOException, JsonProcessingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(fileData);
		
		JsonNode whiteJson = jsonNode.findValue("whitelist");
		
		BeanWrapper src = new BeanWrapperImpl(source);
		BeanWrapper trg = new BeanWrapperImpl(target);
		
		Iterator<String> properties = whiteJson.fieldNames();
		while (properties.hasNext()) {
			String propertyName = (String) properties.next();

			JsonNode nestedJson = whiteJson.findPath(propertyName);

			if (nestedJson.isObject()) {
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
				if (pd != null && pd.getPropertyType().isInterface()) {
					trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
					Set nestedSetSource = (Set) src.getPropertyValue(propertyName);
					Iterator it1 = nestedSetSource.iterator();
					TypeDescriptor typeDescriptor =  src.getPropertyTypeDescriptor(propertyName);
					Set nestedSetTarget = new HashSet();
					while (it1.hasNext()) {
						Object object = (Object) it1.next();
						Object dest = Class.forName(typeDescriptor.getElementType().getCanonicalName()).newInstance();
						extractNested(Class.forName(typeDescriptor.getElementType().getCanonicalName()).cast(object), dest, nestedJson.toString());
						nestedSetTarget.add(dest);
					}
					trg.setPropertyValue(propertyName, nestedSetTarget);
				}else{
					Iterator<String> it = nestedJson.fieldNames();
					while (it.hasNext()) {
						String nestedProperty = (String) it.next();

						JsonNode json = nestedJson.findPath(nestedProperty);

						if (!json.isObject()) {
							PropertyDescriptor  pd2 = BeanUtils.getPropertyDescriptor(pd.getPropertyType(), nestedProperty);
							
							if (pd2 != null) {
								trg.setPropertyValue(nestedProperty, src.getPropertyValue(nestedProperty));
							}
						}
					}
				}
			}else{
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
				if (pd != null) {
					trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
				}				
			}
		}
	}
	
	/**
	 * copy single level white List using jsonNode
	 * @param source : Object to copy
	 * @param target : Object from copy
	 * @param propertyList : list of properties
	 */
	public void copyBeanProperties(final Object source,final Object target, final JsonNode propertyList){
		copyBeanProperties(source, target, propertyList.fieldNames());
	}
	
	
	/**
	 * copy single level whitelist using list
	 * @param source : Object to copy
	 * @param target : Object from copy
	 * @param propertyList : list of properties
	 */
	public void copyBeanProperties(final Object source,final Object target, final List<String> propertyList){
		Iterator<String> iterator = propertyList.iterator();
		copyBeanProperties(source, target, iterator);
	}
	
	/**
	 * copy single level whitelist using String array
	 * @param source : Object to copy
	 * @param target : Object from copy
	 * @param propertyList : list of properties
	 */
	public void copyBeanProperties(final Object source,final Object target, final String[] properties){
		List<String>  propertyList = new ArrayList<String>(Arrays.asList(properties));
		Iterator<String> iterator = propertyList.iterator();
		copyBeanProperties(source, target, iterator);
	}
	
	
	public void copyBeanProperties(Object source, Object target, Iterator<String> properties){

	    final BeanWrapper src = new BeanWrapperImpl(source);
	    final BeanWrapper trg = new BeanWrapperImpl(target);

	    while (properties.hasNext()) {
			String propertyName = (String) properties.next();
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
			if (pd != null) {
				trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
			}else if(propertyName.indexOf(".") != -1){
				
				String [] nestedProperty =  StringUtils.split(propertyName, "\\.");
				PropertyDescriptor pd2 = BeanUtils.getPropertyDescriptor(source.getClass(), nestedProperty[0]);
				
				if (pd2 != null && pd2.getPropertyType().isInterface()) {
					//TODO update for nested Set or List										
				}else if (pd2 != null) {
					pd2 = BeanUtils.getPropertyDescriptor(pd2.getPropertyType(), nestedProperty[1]);
					
					if (pd2 != null) {
						trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
					}
				}
							
			}
		}	    
	}

	

}
