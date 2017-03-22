package com.ednomy.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ED_CRM_DATA")
public class EdCrmEntity extends EdBaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "COMPANY_NAME")
	private String company;
	
	@Column(name = "CAT_1")
	private String category1;
	
	@Column(name = "CAT_2")
	private String category2;
	
	@Column(name = "CAT_3")
	private String category3;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MOB_1")
	private String mob1;
	
	@Column(name = "MOB_2")
	private String mob2;
	
	@Column(name = "LA_1")
	private String la1;
	
	@Column(name = "LA_2")
	private String la2;
	
	@Column(name = "ADD_1")
	private String add1;
	
	@Column(name = "ADD_2")
	private String add2;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "WEBSITE")
	private String website;
	
	@Column(name = "SOURCE")
	private String source;
	
	@Column(name = "CONTACT_PERSON")
	private String contactPerson;
	
	@Column(name = "WA_STATUS")
	private String waStatus;
	
	@Column(name = "USER_NAME")
	private String username;
	
	@Column(name = "SMS_STATUS")
	private String smsStatus;
	
	@Column(name = "LEVEL_1_REMARK")
	private String remarkLevel1;
	
	@Column(name = "LEVEL_2_REMARK")
	private String remarkLevel2;
	
	@Column(name = "LEVEL_3_REMARK")
	private String remarkLevel3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob1() {
		return mob1;
	}

	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}

	public String getMob2() {
		return mob2;
	}

	public void setMob2(String mob2) {
		this.mob2 = mob2;
	}

	public String getLa1() {
		return la1;
	}

	public void setLa1(String la1) {
		this.la1 = la1;
	}

	public String getLa2() {
		return la2;
	}

	public void setLa2(String la2) {
		this.la2 = la2;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getWaStatus() {
		return waStatus;
	}

	public void setWaStatus(String waStatus) {
		this.waStatus = waStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getRemarkLevel1() {
		return remarkLevel1;
	}

	public void setRemarkLevel1(String remarkLevel1) {
		this.remarkLevel1 = remarkLevel1;
	}

	public String getRemarkLevel2() {
		return remarkLevel2;
	}

	public void setRemarkLevel2(String remarkLevel2) {
		this.remarkLevel2 = remarkLevel2;
	}

	public String getRemarkLevel3() {
		return remarkLevel3;
	}

	public void setRemarkLevel3(String remarkLevel3) {
		this.remarkLevel3 = remarkLevel3;
	}

	
	
}