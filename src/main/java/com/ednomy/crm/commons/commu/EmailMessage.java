package com.ednomy.crm.commons.commu;

import org.springframework.web.multipart.MultipartFile;

import com.ednomy.crm.exception.EdnomyException;

public class EmailMessage {

	private Long id;
	private String sender;
	private String receiver;
	private String senderName;
	private String subject;
	private String body;
	private String response;
	private String responseCode;
	private MultipartFile multipartFile;

	
	public EmailMessage() {
		
	}
	
	public EmailMessage(Builder builder) {
		this.id = builder.id;
		this.sender = builder.sender;
		this.receiver = builder.receiver;
		this.senderName = builder.senderName;
		this.subject = builder.subject;
		this.body = builder.body;
		this.response = builder.response;
		this.responseCode = builder.responseCode;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}



	public static class Builder {

		private Long id;
		private String sender;
		private String receiver;
		private String senderName;
		private String subject;
		private String body;
		private String response;
		private String responseCode;
		private MultipartFile multipartFile;
		
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setSender(String sender) {
			this.sender = sender;
			return this;
		}

		public Builder setReceiver(String receiver) {
			this.receiver = receiver;
			return this;
		}

		public Builder setSenderName(String senderName) {
			this.senderName = senderName;
			return this;
		}

		public Builder setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder setBody(String body) {
			this.body = body;
			return this;
		}
		
		public Builder setResponse(String response) {
			this.response = response;
			return this;
		}

		public Builder setResponseCode(String responseCode) {
			this.responseCode = responseCode;
			return this;
		}
		
		public Builder setMultipartFile(MultipartFile multipartFile) {
			this.multipartFile = multipartFile;
			return this;
		}

		public EmailMessage build() {
			EmailMessage emailMessage = new EmailMessage(this);
            validateUserObject(emailMessage);
            return emailMessage;
        }

		private void validateUserObject(EmailMessage emailMessage) {
			if (emailMessage.getSender() == null || emailMessage.getSender().isEmpty()) {
				throw new EdnomyException("sender email is compulsory");
			}
			if (emailMessage.getReceiver() == null || emailMessage.getReceiver().isEmpty()) {
				throw new EdnomyException("receiver email is compulsory");
			}
			if (emailMessage.getSubject()== null || emailMessage.getSubject().isEmpty()) {
				throw new EdnomyException("email subject is compulsory");
			}
			if (emailMessage.getBody()== null || emailMessage.getBody().isEmpty()) {
				throw new EdnomyException("email content (body) is compulsory");
			}
			
		}

	}

}
