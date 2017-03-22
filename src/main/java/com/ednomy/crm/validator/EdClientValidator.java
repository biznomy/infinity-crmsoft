package com.ednomy.crm.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ednomy.crm.commons.constants.EdMessageConstants;
import com.ednomy.crm.model.EdClient;
import com.ednomy.crm.model.metadatamodel.EdClientConstant;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.FileUtility;

@Component
public class EdClientValidator implements Validator, MessageSourceAware {

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
		return EdClient.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				EdClientConstant.APP_NAME, this.messageSource.getMessage(
						EdMessageConstants.FIELD_REQUIRED,
						new String[] { EdClientConstant.APP_NAME}, null));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				EdClientConstant.MOBILE, this.messageSource.getMessage(
						EdMessageConstants.FIELD_REQUIRED,
						new String[] { EdClientConstant.MOBILE}, null));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				EdClientConstant.FIRST_NAME, this.messageSource.getMessage(
						EdMessageConstants.FIELD_REQUIRED,
						new String[] { EdClientConstant.FIRST_NAME}, null));

		

	}
	
}