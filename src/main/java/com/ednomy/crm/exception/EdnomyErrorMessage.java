package com.ednomy.crm.exception;

public class EdnomyErrorMessage {

	private String message;
	private String field;

	public EdnomyErrorMessage(String message, String field) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
