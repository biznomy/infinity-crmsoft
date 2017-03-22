package com.ednomy.crm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ED_USER")
public class EdUserEntity extends EdBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_FIRSTNAME")
	private String firstName;

	@Column(name = "USER_LASTNAME")
	private String lastName;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_MOBILE")
	private String mobile;

	@Column(name = "USER_PASSWORD")
	private String password;

	@Column(name = "USER_DEVICE")
	private String deviceName;

	@Column(name = "USER_REGID")
	private String deviceRegId;

	@Column(name = "USER_DEVICE_TYPE")
	private String deviceType;

	@Column(name = "USER_IS_THIRD_PARTY")
	private String isThirdPartyLogin;

	@Column(name = "USER_THIRD_PARTY")
	private String nameOfThirdParty;

	@Column(name = "USER_AUTH_TOKEN")
	private String authToken;

	@Column(name = "USER_DEVICE_UUID")
	private String deviceUUID;

	@Column(name = "USER_DEVICE_VERSION")
	private String deviceVersion;

	@Column(name = "USER_TYPE")
	private String userType;

	@Column(name = "OTP_NUMBER")
	private String otp;

	@Column(name = "OTP_EXPIRY")
	private Date otpExpiry;

	@Column(name = "USER_PROFILE_IMAGE")
	private String profileImage;

	@Column(name = "USER_GROUP_ID")
	private String clientGroupId;

	@Column(name = "USER_BUSINESS_TYPE")
	private String clientBusinessType;

	@Column(name = "USER_NOTIFICATION", columnDefinition = "int default 0")
	private int notification;

	@Column(name = "CLIENT_SUPPORT_VERSION")
	private String cliSupportedVer;

	@Column(name = "CLIENT_LATEST_VERSION")
	private String cliLatestVer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "CLIENT_USER_ID", referencedColumnName = "USER_ID") })
	@Fetch(FetchMode.JOIN)
	private EdUserEntity clientUser;

	@OneToMany(mappedBy = "clientUser", fetch = FetchType.LAZY)
	private Set<EdContentCollectionEntity> edContentCollections = new HashSet<EdContentCollectionEntity>();

	@OneToMany(mappedBy = "endUser", fetch = FetchType.LAZY)
	private Set<EdContentDataEntity> edContentDatas = new HashSet<EdContentDataEntity>();

	@Column(name = "STATUS_1", columnDefinition = "int default 0")
	private Long status1 = 0L;

	@Column(name = "STATUS_2", columnDefinition = "int default 0")
	private Long status2 = 0L;

	@Column(name = "STATUS_3", columnDefinition = "int default 0")
	private Long status3 = 0L;

	@Column(name = "SETTING_1")
	private String setting1;

	@Column(name = "SETTING_2")
	private String setting2;

	@Column(name = "SETTING_3")
	private String setting3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceRegId() {
		return deviceRegId;
	}

	public void setDeviceRegId(String deviceRegId) {
		this.deviceRegId = deviceRegId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getIsThirdPartyLogin() {
		return isThirdPartyLogin;
	}

	public void setIsThirdPartyLogin(String isThirdPartyLogin) {
		this.isThirdPartyLogin = isThirdPartyLogin;
	}

	public String getNameOfThirdParty() {
		return nameOfThirdParty;
	}

	public void setNameOfThirdParty(String nameOfThirdParty) {
		this.nameOfThirdParty = nameOfThirdParty;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(Date otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(String clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public String getClientBusinessType() {
		return clientBusinessType;
	}

	public void setClientBusinessType(String clientBusinessType) {
		this.clientBusinessType = clientBusinessType;
	}

	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	public String getCliSupportedVer() {
		return cliSupportedVer;
	}

	public void setCliSupportedVer(String cliSupportedVer) {
		this.cliSupportedVer = cliSupportedVer;
	}

	public String getCliLatestVer() {
		return cliLatestVer;
	}

	public void setCliLatestVer(String cliLatestVer) {
		this.cliLatestVer = cliLatestVer;
	}

	public EdUserEntity getClientUser() {
		return clientUser;
	}

	public void setClientUser(EdUserEntity clientUser) {
		this.clientUser = clientUser;
	}

	public Set<EdContentCollectionEntity> getEdContentCollections() {
		return edContentCollections;
	}

	public void setEdContentCollections(
			Set<EdContentCollectionEntity> edContentCollections) {
		this.edContentCollections = edContentCollections;
	}

	public Set<EdContentDataEntity> getEdContentDatas() {
		return edContentDatas;
	}

	public void setEdContentDatas(Set<EdContentDataEntity> edContentDatas) {
		this.edContentDatas = edContentDatas;
	}

	public Long getStatus1() {
		return status1;
	}

	public void setStatus1(Long status1) {
		this.status1 = status1;
	}

	public Long getStatus2() {
		return status2;
	}

	public void setStatus2(Long status2) {
		this.status2 = status2;
	}

	public Long getStatus3() {
		return status3;
	}

	public void setStatus3(Long status3) {
		this.status3 = status3;
	}

	public String getSetting1() {
		return setting1;
	}

	public void setSetting1(String setting1) {
		this.setting1 = setting1;
	}

	public String getSetting2() {
		return setting2;
	}

	public void setSetting2(String setting2) {
		this.setting2 = setting2;
	}

	public String getSetting3() {
		return setting3;
	}

	public void setSetting3(String setting3) {
		this.setting3 = setting3;
	}
	
	
}
