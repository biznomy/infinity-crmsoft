package com.ednomy.crm.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.ednomy.crm.commons.constants.EdErrorCode;

public class EdnomyException extends RuntimeException {

	private static final long serialVersionUID = 2972341244097486469L;

	public static List<EdnomyErrorMessage> listExceptions(
			BeanPropertyBindingResult beanPropertyBindingResult) {
		final List<EdnomyErrorMessage> messageList = new ArrayList<EdnomyErrorMessage>();
		final Iterator<FieldError> it = beanPropertyBindingResult
				.getFieldErrors().iterator();
		while (it.hasNext()) {
			final FieldError type = it.next();
			messageList.add(new EdnomyErrorMessage(type.getCode(), type
					.getField()));
		}
		final Iterator<ObjectError> iteratorObjectError = beanPropertyBindingResult
				.getGlobalErrors().iterator();
		while (iteratorObjectError.hasNext()) {
			final ObjectError type = iteratorObjectError.next();
			messageList.add(new EdnomyErrorMessage(type.getCode(),
					"general"));
		}

		return messageList;
	}

	private List<EdnomyErrorMessage> messagesList = null;

	private int errorCode = EdErrorCode.DEFAULT_ERROR_CODE;

	public EdnomyException() {

	}

	public EdnomyException(Exception exception) {
		super(exception);
		addMessageToList(exception.getMessage(), "");

	}

	public EdnomyException(Exception exception, String message) {
		super(exception);
	}

	public EdnomyException(String message) {
		super(message);
	}

	public EdnomyException(String message,
			List<EdnomyErrorMessage> messagesList) {
		super(message);
		setMessagesList(messagesList);
	}

	public EdnomyException(String message, String cause) {
		super(message);
		addMessageToList(message, cause);
	}

	public EdnomyException(String message, Throwable cause) {
		super(message, cause);

	}

	public EdnomyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);

	}

	public EdnomyException(Throwable cause) {
		super(cause);

	}

	public void addMessageToList(EdnomyErrorMessage ednomyErrorMessage) {
		if (messagesList == null) {
			messagesList = new ArrayList<EdnomyErrorMessage>();
		}
		this.messagesList.add(ednomyErrorMessage);
	}

	public void addMessageToList(String errorMessage, String errorField) {
		if (messagesList == null) {
			messagesList = new ArrayList<EdnomyErrorMessage>();
		}
		this.messagesList.add(new EdnomyErrorMessage(errorMessage,
				errorField));
	}

	public int getErrorCode() {
		return errorCode;
	}

	public List<EdnomyErrorMessage> getMessagesList() {
		return messagesList;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessagesList(List<EdnomyErrorMessage> messages) {
		this.messagesList = messages;
	}
}
