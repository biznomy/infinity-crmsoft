package com.ednomy.crm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ED_MESSAGE_MANAGER")
public class EdMessageManagerEntity extends EdBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
 
	@Column(name = "COMPANY_ID")
	private Long companyId;

	@Column(name = "SENDER")
	private String sender;

	@Column(name = "SENDER_NAME")
	private String senderName;

	@Column(name = "RECEIVER")
	private String receiver;

	@Column(name = "RECEIVER_NAME")
	private String receiverName;

	@Column(name = "TYPE")
	private Long type;

	@Lob
	@Column(name = "TITLE")
	private String title;

	@Lob
	@Column(name = "CONTENT")
	private String content;

	@Column(name = "DELEIVER_STATUS", columnDefinition = "int default 0")
	private Long deliverStatus;

	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name = "RECEIVED_DATE")
	private Date receivedDate;

	@Column(name = "RETRY_COUNT", columnDefinition = "int default 0")
	private Long retryCount;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "REFERENCE_NAME")
	private String userName;

	@Lob
	@Column(name = "PARAMETERS")
	private String parameters;

	@Column(name = "EVENT_INFO")
	private String eventInfo;

	@Lob
	@Column(name = "RESPONSE")
	private String response;

	@Column(name = "IS_SCHEDULED", columnDefinition = "int default 0")
	private Long isScheduled;

	@Lob
	@Column(name = "CC")
	private String cc;

	@Lob
	@Column(name = "BCC")
	private String bcc;

	@Lob
	@Column(name = "ATTACHEMENT")
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

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	

}