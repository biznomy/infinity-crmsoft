package com.ednomy.crm.validator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.ednomy.crm.commons.constants.EdMessageConstants;
import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.ednomy.crm.model.EdMessageManager;
import com.ednomy.crm.model.metadatamodel.EdMessageManagerConstant;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.AwsS3Box;

@Component
public class EdMessageManagerValidator implements Validator, MessageSourceAware {

	private static final String ATTACHMENT = "attachment/";

	MessageSource messageSource;
	
	@Autowired
	AwsS3Box awsS3Box; 
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass()); 

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return EdMessageManager.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		EdMessageManager edMessageManager = (EdMessageManager) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.eventInfo, this.messageSource.getMessage(
				EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.eventInfo}, null));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.sender, this.messageSource.getMessage(
				EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.sender}, null));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.receiver, this.messageSource.getMessage(
				EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.receiver}, null));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.content, this.messageSource.getMessage(
				EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.content}, null));

		if (edMessageManager.getIsScheduled() == EdnomyConstants.TRUE_INT) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.deliveryDate, this.messageSource.getMessage(
							EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.deliveryDate}, null));
		}
		
		if (edMessageManager.getType() == EdnomyConstants.TYPE_EMAIL_INT) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.senderName, this.messageSource.getMessage(
							EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.senderName}, null));
		}
		
		if (edMessageManager.getType() == EdnomyConstants.TYPE_SMS_INT) {
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, EdMessageManagerConstant.deliveryDate, this.messageSource.getMessage(
				//			EdMessageConstants.FIELD_REQUIRED, new String[] { EdMessageManagerConstant.deliveryDate}, null));
		}
		

		

	}

	/**
	 * Upload attachment to s3
	 * @param string
	 * @throws IOException 
	 * @throws AmazonClientException 
	 * @throws AmazonServiceException 
	 */
	public void saveAttachment(EdMessageManager edMessageManager) throws AmazonServiceException, AmazonClientException, IOException {
		if (edMessageManager.getMultipartFile() != null && edMessageManager.getMultipartFile().getSize() > 0) {
			String fileName = applicationProperties.getMediaLinkConst()+applicationProperties.getBucketName()+"/"+ATTACHMENT + edMessageManager.getMultipartFile().getOriginalFilename();
			awsS3Box.uploadS3(applicationProperties.getBucketName(), ATTACHMENT + edMessageManager.getMultipartFile().getOriginalFilename(), new ByteArrayInputStream(edMessageManager.getMultipartFile().getBytes()));
			edMessageManager.setAttachment(fileName);
		}
		
		if (edMessageManager.getBase64() != null && edMessageManager.getBase64().length() > 0 && edMessageManager.getAttachment() != null && !edMessageManager.getAttachment().isEmpty()) {
			String fileName = applicationProperties.getMediaLinkConst()+applicationProperties.getBucketName()+"/"+ATTACHMENT + edMessageManager.getAttachment();
			awsS3Box.uploadS3(applicationProperties.getBucketName(), ATTACHMENT + edMessageManager.getBase64());
			edMessageManager.setAttachment(fileName);
		}
	}
	
	
	
}