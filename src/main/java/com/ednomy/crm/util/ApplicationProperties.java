package com.ednomy.crm.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Value("${location.temp.file}")
	private String tempFile;

	@Value("${location.temp.dir}")
	private String tempDir;

	@Value("${bucket.name}")
	private String bucketName;

	@Value("${bucket.accesskey}")
	private String bucketAccessKey;

	@Value("${bucket.secretkey}")
	private String bucketSecretKey;

	@Value("${gcm.title.tag}")
	private String gcmTitleTag;

	@Value("${gcm.content.tag}")
	private String gcmContentTag;

	@Value("${gcm.notificaton.id.tag}")
	private String gcmNotificationTag;

	@Value("${ios.certificate.password}")
	private String ioSCertificatePassword;

	@Value("${ios.certificate.production}")
	private String isIOSProduction;

	@Value("${upload.image.base64seprator}")
	private String base64Seprator;

	@Value("${upload.image.imageprefix}")
	private String imagePrefix;
	
	@Value("${upload.image.imageprefixonrequest}")
	private String imagePrefixOnRequest;

	@Value("${upload.image.medialink}")
	private String mediaLinkConst;
	
	@Value("${ednomy.email.address}")
	private String ednomyEmail;
	
	@Value("${sms.host}")
	private String smsUrl;

	@Value("${sms.user}")
	private String smsUserName;
	
	@Value("${sms.pass}")
	private String smsPass;
	
	@Value("${sms.priority}")
	private String smsPriority;
	
	@Value("${sms.stype}")
	private String smsStype;
 
	
	@Value("${sms.sender}")
	private String smsSender;
	
	
    
	@Value("${mail.host}")
	private String emailHost;

	@Value("${mail.username}")
	private String emailUserName;
	
	@Value("${mail.password}")
	private String emailPassword;
	
	@Value("${mail.port}")
	private int emailPort;
	
	@Value("${mail.transport.protocol}")
	private String emailProtocol;
	
	@Value("${mail.smtp.auth}")
	private String emailSmtpUth;
	
	@Value("${mail.smtp.starttls.enable}")
	private String emailStartEnable;

	public String getTempFile() {
		return tempFile;
	}

	public String getTempDir() {
		return tempDir;
	}

	public String getBucketName() {
		return bucketName;
	}

	public String getBucketAccessKey() {
		return bucketAccessKey;
	}

	public String getBucketSecretKey() {
		return bucketSecretKey;
	}

	public String getGcmTitleTag() {
		return gcmTitleTag;
	}

	public String getGcmContentTag() {
		return gcmContentTag;
	}

	public String getGcmNotificationTag() {
		return gcmNotificationTag;
	}

	public String getIoSCertificatePassword() {
		return ioSCertificatePassword;
	}

	public String getIsIOSProduction() {
		return isIOSProduction;
	}

	public String getBase64Seprator() {
		return base64Seprator;
	}

	public String getImagePrefix() {
		return imagePrefix;
	}

	public String getImagePrefixOnRequest() {
		return imagePrefixOnRequest;
	}

	public String getMediaLinkConst() {
		return mediaLinkConst;
	}

	public String getEdnomyEmail() {
		return ednomyEmail;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public String getSmsUserName() {
		return smsUserName;
	}

	public String getSmsPass() {
		return smsPass;
	}

	public String getSmsPriority() {
		return smsPriority;
	}

	public String getSmsStype() {
		return smsStype;
	}

	public String getSmsSender() {
		return smsSender;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public int getEmailPort() {
		return emailPort;
	}

	public String getEmailProtocol() {
		return emailProtocol;
	}

	public String getEmailSmtpUth() {
		return emailSmtpUth;
	}

	public String getEmailStartEnable() {
		return emailStartEnable;
	}

	public void setTempFile(String tempFile) {
		this.tempFile = tempFile;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setBucketAccessKey(String bucketAccessKey) {
		this.bucketAccessKey = bucketAccessKey;
	}

	public void setBucketSecretKey(String bucketSecretKey) {
		this.bucketSecretKey = bucketSecretKey;
	}

	public void setGcmTitleTag(String gcmTitleTag) {
		this.gcmTitleTag = gcmTitleTag;
	}

	public void setGcmContentTag(String gcmContentTag) {
		this.gcmContentTag = gcmContentTag;
	}

	public void setGcmNotificationTag(String gcmNotificationTag) {
		this.gcmNotificationTag = gcmNotificationTag;
	}

	public void setIoSCertificatePassword(String ioSCertificatePassword) {
		this.ioSCertificatePassword = ioSCertificatePassword;
	}

	public void setIsIOSProduction(String isIOSProduction) {
		this.isIOSProduction = isIOSProduction;
	}

	public void setBase64Seprator(String base64Seprator) {
		this.base64Seprator = base64Seprator;
	}

	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}

	public void setImagePrefixOnRequest(String imagePrefixOnRequest) {
		this.imagePrefixOnRequest = imagePrefixOnRequest;
	}

	public void setMediaLinkConst(String mediaLinkConst) {
		this.mediaLinkConst = mediaLinkConst;
	}

	public void setEdnomyEmail(String ednomyEmail) {
		this.ednomyEmail = ednomyEmail;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public void setSmsPass(String smsPass) {
		this.smsPass = smsPass;
	}

	public void setSmsPriority(String smsPriority) {
		this.smsPriority = smsPriority;
	}

	public void setSmsStype(String smsStype) {
		this.smsStype = smsStype;
	}

	public void setSmsSender(String smsSender) {
		this.smsSender = smsSender;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public void setEmailPort(int emailPort) {
		this.emailPort = emailPort;
	}

	public void setEmailProtocol(String emailProtocol) {
		this.emailProtocol = emailProtocol;
	}

	public void setEmailSmtpUth(String emailSmtpUth) {
		this.emailSmtpUth = emailSmtpUth;
	}

	public void setEmailStartEnable(String emailStartEnable) {
		this.emailStartEnable = emailStartEnable;
	}
	
    

}

