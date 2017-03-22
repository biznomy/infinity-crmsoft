package com.ednomy.crm.util;

import java.util.List;

import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EdResponse<T> {

	private int code;
	private T result;
	private List<EdnomyErrorMessage> messages;
	private String message;
	private Long count;

	private List<T> results;

	public EdResponse() {
		code = 0;
		result = null;
		setMessages(null);
		message = null;
		count = null;
		results = null;
	}

	public int getCode() {
		return code;
	}

	public Long getCount() {
		return count;
	}

	public String getMessage() {
		return message;
	}

	public List<EdnomyErrorMessage> getMessages() {
		return messages;
	}

	public T getResult() {
		return result;
	}

	public List<T> getResults() {
		return results;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessages(List<EdnomyErrorMessage> messages) {
		this.messages = messages;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}
