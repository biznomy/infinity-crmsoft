package com.ednomy.crm.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ednomy.crm.model.EdCrm;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.FileUtility;

@Component
public class EdCrmValidator implements Validator, MessageSourceAware {

	MessageSource messageSource;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Autowired
	FileUtility fileUtility;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass()); 

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return EdCrm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
	}
	
}