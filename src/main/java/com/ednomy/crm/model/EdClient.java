package com.ednomy.crm.model;

import org.springframework.web.multipart.MultipartFile;

public class EdClient {
	
	private Long id;
	
	private String appName;
	
	private String firstName;
	
	private String lastName;
	
	private String mobile;
	
	private String email;
	
	private String website;
	
	private String address;
	
	private String description;
	
	private String zipCode;
	
	private String userName;
	
	private String password;
	
	private String appImage;
	
	private String userImage;
	
	private String appImageBase64;
	
	private String userImageBase64;
	
	private MultipartFile appImageFile;
	
	private MultipartFile userImageFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppImage() {
		return appImage;
	}

	public void setAppImage(String appImage) {
		this.appImage = appImage;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getAppImageBase64() {
		return appImageBase64;
	}

	public void setAppImageBase64(String appImageBase64) {
		this.appImageBase64 = appImageBase64;
	}

	public String getUserImageBase64() {
		return userImageBase64;
	}

	public void setUserImageBase64(String userImageBase64) {
		this.userImageBase64 = userImageBase64;
	}

	public MultipartFile getAppImageFile() {
		return appImageFile;
	}

	public void setAppImageFile(MultipartFile appImageFile) {
		this.appImageFile = appImageFile;
	}

	public MultipartFile getUserImageFile() {
		return userImageFile;
	}

	public void setUserImageFile(MultipartFile userImageFile) {
		this.userImageFile = userImageFile;
	}
}
