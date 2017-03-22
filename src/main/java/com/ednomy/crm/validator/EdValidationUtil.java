package com.ednomy.crm.validator;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.ednomy.crm.commons.constants.EdMessageConstants;

@Component
public class EdValidationUtil {

	public void rejectIfEmptyOrWhitespace(Errors errors,
			MessageSource messageSource, String[] fields) {

		for (int i = 0; i < fields.length; i++) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, fields[i],
					messageSource.getMessage(
							EdMessageConstants.FIELD_REQUIRED,
							new String[] { fields[i] }, null));
		}

	}

}
