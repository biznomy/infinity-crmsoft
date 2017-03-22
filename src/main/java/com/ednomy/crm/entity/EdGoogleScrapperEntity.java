package com.ednomy.crm.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ED_GOOGLE_SCRAPPER")
@EntityListeners({AuditingEntityListener.class})
public class EdGoogleScrapperEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "PLACE_ID", unique = true)
	private String placeId;

	@Column(name = "REFERENCE")
	private String reference;

	@Column(name = "LAT")
	private String lat;

	@Column(name = "LNG")
	private String lng;
	
	@Column(name = "DETAIL_STATUS")
	private int detailStatus;
	
	@Lob
	@Column(name = "COMPLETE_DETAIL")
	private String completeDetail;
	
	@Lob
	@Column(name = "FORMATTED_ADDRESS")
	private String formattedAddress;
	
	@Column(name = "FORMATTED_NUMBER")
	private String formattedNumber;
	
	@Column(name = "INTERN_CONTACT_NUMBER")
	private String internationalPhoneNumber;
	
	@Column(name = "NAME_1")
	private String name;
	
	@Column(name = "RATING")
	private String rating;
	
	@Column(name = "SCOPE")
	private String scope;
	
	@Lob
	@Column(name = "TYPES")
	private String types;
	
	@Column(name="GOOGLE_URL")
	private String googleURL;
	
	@Lob
	@Column(name = "VICINITY")
	private String vicinity;
	
	@Column(name="WEBSITE")
	private String website;
	
	@Column(name="EMAIL")
	private String email;
	
	@Lob
	@Column(name = "ADDRESS_COMPONENTS")
	private String addressComponents;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;	
	
	@Column(name="POSTAL_CODE")
	private String postalCode;
	
	@Column(name="ADMINISTRATIVE_AREA_LEVEL_1")
	private String administrativeAreaLevel1;
	
	@Column(name="ADMINISTRATIVE_AREA_LEVEL_1_CODE")
	private String administrativeAreaLevel1Code;
	
	@Column(name="ADMINISTRATIVE_AREA_LEVEL_2")
	private String administrativeAreaLevel2;
	
	@Column(name="ADMINISTRATIVE_AREA_LEVEL_3")
	private String administrativeAreaLevel3;
	
	@Column(name="ADMINISTRATIVE_AREA_LEVEL_4")
	private String administrativeAreaLevel4;
	
	@Column(name="SUBLOCALITY_LEVEL_1")
	private String sublocalityLevel1;
	
	@Column(name="SUBLOCALITY_LEVEL_2")
	private String sublocalityLevel2;
	
	@Column(name="LOCALITY")
	private String locality;
	
	@Column(name="ROUTE")
	private String route;
	
	@Column(name="STATUS_MOVE")
	private Integer status = 0;
	
	@Column(name="CREATED_ON", updatable =false , nullable = false)
	@CreatedDate
	private Date createdOn = new Date();
	
	@Column(name="UPDATED_ON", updatable = true, nullable = false)
	@LastModifiedDate
	private Date updatedOn = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public int getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(int detailStatus) {
		this.detailStatus = detailStatus;
	}

	public String getCompleteDetail() {
		return completeDetail;
	}

	public void setCompleteDetail(String completeDetail) {
		this.completeDetail = completeDetail;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedNumber() {
		return formattedNumber;
	}

	public void setFormattedNumber(String formattedNumber) {
		this.formattedNumber = formattedNumber;
	}

	public String getInternationalPhoneNumber() {
		return internationalPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber) {
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getGoogleURL() {
		return googleURL;
	}

	public void setGoogleURL(String googleURL) {
		this.googleURL = googleURL;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(String addressComponents) {
		this.addressComponents = addressComponents;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}

	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}

	public String getAdministrativeAreaLevel1Code() {
		return administrativeAreaLevel1Code;
	}

	public void setAdministrativeAreaLevel1Code(String administrativeAreaLevel1Code) {
		this.administrativeAreaLevel1Code = administrativeAreaLevel1Code;
	}

	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}

	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}

	public String getAdministrativeAreaLevel3() {
		return administrativeAreaLevel3;
	}

	public void setAdministrativeAreaLevel3(String administrativeAreaLevel3) {
		this.administrativeAreaLevel3 = administrativeAreaLevel3;
	}

	public String getAdministrativeAreaLevel4() {
		return administrativeAreaLevel4;
	}

	public void setAdministrativeAreaLevel4(String administrativeAreaLevel4) {
		this.administrativeAreaLevel4 = administrativeAreaLevel4;
	}

	public String getSublocalityLevel1() {
		return sublocalityLevel1;
	}

	public void setSublocalityLevel1(String sublocalityLevel1) {
		this.sublocalityLevel1 = sublocalityLevel1;
	}

	public String getSublocalityLevel2() {
		return sublocalityLevel2;
	}

	public void setSublocalityLevel2(String sublocalityLevel2) {
		this.sublocalityLevel2 = sublocalityLevel2;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}