package com.ednomy.crm.validator;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ednomy.crm.commons.constants.EdMessageConstants;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.model.metadatamodel.EdUserConstant;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.FileUtility;

@Component
public class EdUserValidator implements Validator, MessageSourceAware {

	MessageSource messageSource;
	
	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	FileUtility fileUtility;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass()); 

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return EdUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		 EdUser edUser = (EdUser) target;

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdUserConstant.USER_TYPE, this.messageSource.getMessage(
			 EdMessageConstants.FIELD_REQUIRED, new String[] { EdUserConstant.USER_TYPE}, null));
		 
		 if (edUser.getProfileBase64() != null && !edUser.getProfileBase64().trim().isEmpty()) {
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdUserConstant.ED_PROFILE_IMAGE, this.messageSource.getMessage(
					 EdMessageConstants.FIELD_REQUIRED, new String[] { EdUserConstant.ED_PROFILE_IMAGE}, null));
		 }
		 	
	}
	
}