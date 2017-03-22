package com.ednomy.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ED_LOGIN_HISTORY")
public class EdHistoryEntity extends EdBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HISTORY_ID")
	private Long id;

	@Column(name = "CLIENT_ID")
	private Long clientId;

	@Column(name = "END_USER_ID")
	private Long endUserId;

	@Column(name = "USER_PLATFORM")
	private String deviceName;

	@Column(name = "USER_IP")
	private String userIp;

	@Column(name = "LOG_TYPE")
	private String logType;

	@Column(name = "LOG_TIME")
	private long timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long long1) {
		this.clientId = long1;
	}

	public Long getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(long long1) {
		this.endUserId = long1;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long l) {
		this.timestamp = l;
	}

}
