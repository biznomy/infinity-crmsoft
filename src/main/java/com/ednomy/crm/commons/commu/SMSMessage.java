package com.ednomy.crm.commons.commu;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ednomy.crm.exception.EdnomyException;

public class SMSMessage {
	
	private Long id;
	private String url;
	private String sender;
	private String receiver;
	private String senderName;
	private String content;
	private String response;
	private String responseCode;
	private List<NameValuePair> nameValuePairs;
	
	public SMSMessage() {
		
	}
	
	public SMSMessage(Builder builder) {
		this.id 		= builder.id;
		this.url 		= builder.url;
		this.sender 	= builder.sender;
		this.receiver 	= builder.receiver;
		this.senderName = builder.senderName;
		this.content 	= builder.content;
		this.response   = builder.response;
		this.responseCode=builder.responseCode;
		this.nameValuePairs = builder.nameValuePairs;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<NameValuePair> getNameValuePairs() {
		return nameValuePairs;
	}

	public void setNameValuePairs(List<NameValuePair> nameValuePairs) {
		this.nameValuePairs = nameValuePairs;
	}

	public static class Builder {

		private Long id;
		private String url;
		private String sender;
		private String receiver;
		private String senderName;
		private String content;
		private String response;
		private String responseCode;
		private List<NameValuePair> nameValuePairs = new LinkedList<NameValuePair>();
		@SuppressWarnings("unused")
		private BasicNameValuePair basicNameValuePair;
		
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
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

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}
		
		public Builder setNameValuePairs(List<NameValuePair> nameValuePairs) {
			this.nameValuePairs = nameValuePairs;
			return this;
		}
		
		public Builder setBasicNameValuePair(BasicNameValuePair basicNameValuePair) {
			this.basicNameValuePair = basicNameValuePair;
			this.nameValuePairs.add(basicNameValuePair);
			return this;
		}

		public Builder setBasicNameValuePair(String key, String value) {
			this.nameValuePairs.add(new BasicNameValuePair(key, value));
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

		public SMSMessage build() {
			SMSMessage emailMessage = new SMSMessage(this);
            validateUserObject(emailMessage);
            return emailMessage;
        }	

		private void validateUserObject(SMSMessage smsMessage) {
			if (smsMessage.getUrl() == null || smsMessage.getUrl().trim().isEmpty()) {
				throw new EdnomyException("service provider url info is compulsory");
			}
			if (smsMessage.getReceiver() == null || smsMessage.getReceiver().trim().isEmpty()) {
				throw new EdnomyException("receiver info is compulsory");
			}
			if (smsMessage.getContent() == null || smsMessage.getContent().trim().isEmpty()) {
				throw new EdnomyException("sms content is compulsory");
			}			
		}

	}

}
