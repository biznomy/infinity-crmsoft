package com.ednomy.crm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.ednomy.crm.util.CustomJsonDateDeserializer;
import com.ednomy.crm.util.CustomJsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
public class EdUser {

	private Long id;

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String mobile;

	private String password;

	private String deviceName;

	private String deviceRegId;

	private String deviceType;

	private String isThirdPartyLogin;

	private String nameOfThirdParty;

	private String authToken;

	private String deviceUUID;

	private String deviceVersion;

	private String userType;

	private String otp;

	private Date otpExpiry;

	private String profileImage;
	
	private String clientGroupId;
	
	private String clientBusinessType;

	private int notification;

	private EdUser clientUser;
	
	private String verifiedStatus;
	
	private String cliSupportedVer;
	
	private String cliLatestVer;

	private Set<EdContentCollection> edContentCollections = new HashSet<EdContentCollection>();

	private Set<EdContentData> edContentDatas = new HashSet<EdContentData>();

	private MultipartFile multipartFile;

	private String profileBase64;
	
	private Long status1;
	
	private Long status2;
	
	private Long status3;
	
	private String setting1;
	
	private String setting2;
	
	private String setting3;
	
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date receivedDate = new Date();

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

	public EdUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(EdUser clientUser) {
		this.clientUser = clientUser;
	}

	public String getVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(String verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
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

	public Set<EdContentCollection> getEdContentCollections() {
		return edContentCollections;
	}

	public void setEdContentCollections(
			Set<EdContentCollection> edContentCollections) {
		this.edContentCollections = edContentCollections;
	}

	public Set<EdContentData> getEdContentDatas() {
		return edContentDatas;
	}

	public void setEdContentDatas(Set<EdContentData> edContentDatas) {
		this.edContentDatas = edContentDatas;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getProfileBase64() {
		return profileBase64;
	}

	public void setProfileBase64(String profileBase64) {
		this.profileBase64 = profileBase64;
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

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

}