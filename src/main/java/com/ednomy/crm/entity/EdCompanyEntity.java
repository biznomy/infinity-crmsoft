package com.ednomy.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="ED_COMPANY")
public class EdCompanyEntity extends EdBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Lob
	@Column(name = "ED_COMPANY_ADDRESS")
	private String address;
	
	@Column(name="MAIN_ADDRESS")
	private String mainAddress;
	
	@Column(name="POSTAL_CODE")
	private String pincode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="ORG_TYPE")
	private String orgType;
	
	
	@Column(name="CATEGORY1")
	private String category1;
	
	@Column(name="CATEGORY2")
	private String category2;
	
	@Column(name="CATEGORY3")
	private String category3;
	
	@Lob
	@Column(name="ED_COMPANY_EMAIL")
	private String emails;
	
	@Lob
	@Column(name="ED_COMPANY_MOBILE")
	private String mobile;
	
	@Lob
	@Column(name="ED_COMPANY_LANDLINE")
	private String landline;
	
	@Lob
	@Column(name="ED_COMPANY_SOCIAL")
	private String socialAccount;
	
	@Lob
	@Column(name="ED_COMPANY_REMARKS")
	private String remarks;
	
	@Column(name="ED_COMPANY_SOURCE")
	private String source;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getSocialAccount() {
		return socialAccount;
	}

	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
