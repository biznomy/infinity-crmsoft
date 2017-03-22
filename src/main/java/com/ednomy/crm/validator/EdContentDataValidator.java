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
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.model.metadatamodel.EdContentDataConstant;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.FileUtility;

@Component
public class EdContentDataValidator implements Validator, MessageSourceAware {

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
		return EdContentData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		EdContentData edContentData = (EdContentData) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.LANGUAGE,
						this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
										new String[] { EdContentDataConstant.LANGUAGE }, null));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.END_USER,
				this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
								new String[] { EdContentDataConstant.END_USER }, null));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.TYPE,
				this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
								new String[] { EdContentDataConstant.TYPE }, null));


		if (edContentData.getBase64_1() != null && !edContentData.getBase64_1().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA1,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA1}, null));
		}
		if (edContentData.getBase64_2() != null && !edContentData.getBase64_2().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA2,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA2}, null));
		}
		if (edContentData.getBase64_3() != null && !edContentData.getBase64_3().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA3,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA3}, null));
		}
		if (edContentData.getBase64_4() != null && !edContentData.getBase64_4().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA4,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA4}, null));
		}
		if (edContentData.getBase64_5() != null && !edContentData.getBase64_5().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA5,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA5}, null));
		}
		if (edContentData.getBase64_6() != null && !edContentData.getBase64_6().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA6,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA6}, null));
		}
		if (edContentData.getBase64_7() != null && !edContentData.getBase64_7().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA7,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA7}, null));
		}
		if (edContentData.getBase64_8() != null && !edContentData.getBase64_8().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA8,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA8}, null));
		}
		if (edContentData.getBase64_9() != null && !edContentData.getBase64_9().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA9,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA9}, null));
		}
		if (edContentData.getBase64_10() != null && !edContentData.getBase64_10().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.MEDIA10,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.MEDIA10}, null));
		}
		
		if (edContentData.getContentBase64() != null && !edContentData.getContentBase64().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,EdContentDataConstant.CONTENT,
					this.messageSource.getMessage(EdMessageConstants.FIELD_REQUIRED,
									new String[] { EdContentDataConstant.CONTENT}, null));
		}

	}
	
}
