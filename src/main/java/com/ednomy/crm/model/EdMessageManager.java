package com.ednomy.crm.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ednomy.crm.util.CustomJsonDateDeserializer;
import com.ednomy.crm.util.CustomJsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
public class EdMessageManager extends Template {

	private Long id;
	
	private Long companyId;
	
	private String sender;

	private String senderName;

	private String receiver;

	private String receiverName;

	private Long type;

	private String title;

	private String content;

	private Long deliverStatus = 0L;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date deliveryDate;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date receivedDate;

	private Long retryCount = 0L;

	private Long userId;

	private String userName;

	private String parameters;

	private String eventInfo;

	private String response;

	private Long isScheduled = 0L;

	private String cc;

	private String bcc;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date createdAt;

	private String base64;

	private MultipartFile multipartFile;

	private String attachment;

	public Long getId() {
		return id;
	}
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getSender() {
		return sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public Long getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Long getDeliverStatus() {
		return deliverStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public Long getRetryCount() {
		return retryCount;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getParameters() {
		return parameters;
	}

	public String getEventInfo() {
		return eventInfo;
	}

	public String getResponse() {
		return response;
	}

	public Long getIsScheduled() {
		return isScheduled;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getBase64() {
		return base64;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDeliverStatus(Long deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public void setRetryCount(Long retryCount) {
		this.retryCount = retryCount;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setIsScheduled(Long isScheduled) {
		this.isScheduled = isScheduled;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}

class Template {
	private List<String> template;

	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}

}
