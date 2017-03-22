package com.ednomy.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ED_POINTS")
public class EdPointEntity extends EdBaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="LATITUDE")
	private Double latitude;
	
	@Column(name="LONGITUDE")
	private Double longitude;
	
	@Column(name="INSIDE_OUTSIDE", columnDefinition = "int default 0" )
	private Integer coInOut;
	
	@Column(name="MOVE_STATUS", columnDefinition = "int default 0" )
	private Integer status ;
	
	@Column(name="COUNTRY_CODE")
	private String code1 ;
	
	@Column(name="ANOTHER_CODE")
	private String code2 ;
	
	@Column(name="LAT_LONG", unique = true)
	private String latlng ;
	
	@Column(name="DISTANCE")
	private Double distance;
	
	@Override
	public String toString() {
		return latitude + ", " + longitude+"\n" ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getCoInOut() {
		return coInOut;
	}

	public void setCoInOut(Integer coInOut) {
		this.coInOut = coInOut;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	
	
}
