package com.ednomy.crm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;

@Component
public class ExceptionUtil {

	public List<EdnomyErrorMessage> listExceptions(
			BeanPropertyBindingResult beanPropertyBindingResult) {
		final List<EdnomyErrorMessage> messageList = new ArrayList<EdnomyErrorMessage>();
		final Iterator<FieldError> it = beanPropertyBindingResult
				.getFieldErrors().iterator();
		while (it.hasNext()) {
			final FieldError type = it.next();
			messageList.add(new EdnomyErrorMessage(type.getCode(), type
					.getField()));
		}
		return messageList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populateResponseWithException(
			EdResponse edResponse,
			EdnomyException ednomyException) {

		edResponse.setCode(ednomyException.getErrorCode());
		edResponse.setMessage(ednomyException.getMessage());
		edResponse.setMessages(ednomyException.getMessagesList());
		ednomyException.printStackTrace();
	}

}
