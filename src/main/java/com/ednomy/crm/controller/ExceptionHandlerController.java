package com.ednomy.crm.controller;

import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.util.EdResponse;

@ControllerAdvice
public class ExceptionHandlerController {

	@Autowired
	MessageSource messageSource;

	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler({Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public EdResponse<String> genericExceptionHandling(Exception exception) {
		final EdResponse<String> response = new EdResponse<String>();
		response.setCode(1);
		response.setMessage(exception.getMessage());
		LOGGER.error(exception.getMessage(), exception);
		return response;
	}

	@ExceptionHandler(EdnomyException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public EdResponse<EdnomyException> requestDocHandlerException(
			EdnomyException exception) {
		LOGGER.error(exception.getMessage(), exception);
		final EdResponse<EdnomyException> response = new EdResponse<EdnomyException>();
		response.setCode(1);
		response.setMessage(exception.getMessage());
		response.setMessages(exception.getMessagesList());
		return response;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public EdResponse<String> requestHandlingMethodNotSupported(
			HttpRequestMethodNotSupportedException exception) {
		LOGGER.error(exception.getMessage(), exception);
		final EdResponse<String> response = new EdResponse<String>();
		response.setCode(1);
		response.setMessage(messageSource.getMessage(
				"default.exception.methodnotallowed", null, null));

		return response;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public EdResponse<String> requestHandlingNoHandlerFound(NoHandlerFoundException exception) {
		LOGGER.error(exception.getMessage(), exception);
		final EdResponse<String> response = new EdResponse<String>();
		response.setCode(1);
		response.setMessage(messageSource.getMessage(
				"default.exception.notfound", null, null));

		return response;

	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ResponseBody
	public EdResponse<String> requestMethodMediaNotSupported(
			HttpRequestMethodNotSupportedException exception) {
		LOGGER.error(exception.getMessage(), exception);
		final EdResponse<String> response = new EdResponse<String>();
		response.setCode(1);
		response.setMessage(messageSource.getMessage(
				"default.exception.unsupportedmediatype", null, null));

		return response;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public EdResponse<String> requestMethodNotSupported(
			HttpRequestMethodNotSupportedException exception) {
		LOGGER.error(exception.getMessage(), exception);
		final EdResponse<String> response = new EdResponse<String>();
		response.setCode(1);
		response.setMessage(messageSource.getMessage(
				"default.exception.badrequest", null, null));
		return response;
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
	@ResponseBody
	public EdResponse<String> requestNotSupported(NoSuchRequestHandlingMethodException exception) {
		final EdResponse<String> response = new EdResponse<String>();
		LOGGER.error(exception.getMessage(), exception);
		response.setCode(1);
		response.setMessage(messageSource.getMessage(
				"default.exception.notsupported", null, null));

		return response;
	}

	@ExceptionHandler({SQLException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ResponseBody
	public EdResponse<String> sqlExceptionHandling(SQLException exception) {
		final EdResponse<String> response = new EdResponse<String>();
		LOGGER.error(exception.getMessage(), exception);
		response.setCode(1);
		response.setMessage(exception.getMessage());
		return response;
	}
	
	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public EdResponse<ServletRequestBindingException> authenticationError(ServletRequestBindingException exception) {
		final EdResponse<ServletRequestBindingException> response = new EdResponse<ServletRequestBindingException>();
		LOGGER.error(exception.getMessage(), exception);
		response.setCode(1);
		response.setMessage(exception.getMessage());
		return response;
	}

}