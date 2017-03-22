package com.ednomy.crm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ED_CONTENT_COLLECTION")
public class EdContentCollectionEntity extends EdBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COLLECTION_ID")
	private Long id;

	@Column(name = "COLLECTION_TYPE")
	private String type;

	@Column(name = "COLLECTION_DISPLAY_ORDER")
	private Long displayOrder;

	@Column(name = "COLLECTION_TITLE")
	private String title;

	@Column(name = "COLLECTION_DESC")
	private String description;

	@Column(name = "COLLECTION_IMAGE_MAIN")
	private String mediaMain;

	@Column(name = "COLLECTION_VERSION")
	private String version;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private EdUserEntity clientUser;

	@OneToMany(mappedBy = "edContentCollection", fetch = FetchType.EAGER)
	private Set<EdContentDataEntity> edContentDatas = new HashSet<EdContentDataEntity>();

	@Column(name = "ST_LABEL_1")
	private String st1_Label;

	@Column(name = "ST_LABEL_2")
	private String st2_Label;

	@Column(name = "ST_LABEL_3")
	private String st3_Label;

	@Column(name = "ST_LABEL_4")
	private String st4_Label;

	@Column(name = "ST_LABEL_5")
	private String st5_Label;

	@Column(name = "ST_LABEL_6")
	private String st6_Label;

	@Column(name = "ST_LABEL_7")
	private String st7_Label;

	@Column(name = "ST_LABEL_8")
	private String st8_Label;

	@Column(name = "ST_LABEL_9")
	private String st9_Label;

	@Column(name = "ST_LABEL_10")
	private String st10_Label;

	@Column(name = "LT_LABEL_1")
	private String lt1_Label;

	@Column(name = "LT_LABEL_2")
	private String lt2_Label;

	@Column(name = "LT_LABEL_3")
	private String lt3_Label;

	@Column(name = "LT_LABEL_4")
	private String lt4_Label;

	@Column(name = "LT_LABEL_5")
	private String lt5_Label;

	@Column(name = "LT_LABEL_6")
	private String lt6_Label;

	@Column(name = "LT_LABEL_7")
	private String lt7_Label;

	@Column(name = "LT_LABEL_8")
	private String lt8_Label;

	@Column(name = "LT_LABEL_9")
	private String lt9_Label;

	@Column(name = "LT_LABEL_10")
	private String lt10_Label;

	@Column(name = "LN_LABEL_1")
	private String ln1_Label;

	@Column(name = "LN_LABEL_2")
	private String ln2_Label;

	@Column(name = "LN_LABEL_3")
	private String ln3_Label;

	@Column(name = "LN_LABEL_4")
	private String ln4_Label;

	@Column(name = "LN_LABEL_5")
	private String ln5_Label;

	@Column(name = "LN_LABEL_6")
	private String ln6_Label;

	@Column(name = "LN_LABEL_7")
	private String ln7_Label;

	@Column(name = "LN_LABEL_8")
	private String ln8_Label;

	@Column(name = "LN_LABEL_9")
	private String ln9_Label;

	@Column(name = "LN_LABEL_10")
	private String ln10_Label;

	@Column(name = "DN_LABEL_1")
	private String dn1_Label;

	@Column(name = "DN_LABEL_2")
	private String dn2_Label;

	@Column(name = "DN_LABEL_3")
	private String dn3_Label;

	@Column(name = "DN_LABEL_4")
	private String dn4_Label;

	@Column(name = "DN_LABEL_5")
	private String dn5_Label;

	@Column(name = "DN_LABEL_6")
	private String dn6_Label;

	@Column(name = "DN_LABEL_7")
	private String dn7_Label;

	@Column(name = "DN_LABEL_8")
	private String dn8_Label;

	@Column(name = "DN_LABEL_9")
	private String dn9_Label;

	@Column(name = "DN_LABEL_10")
	private String dn10_Label;

	@Column(name = "MD_LABEL_1")
	private String media1_Label;

	@Column(name = "MD_LABEL_2")
	private String media2_Label;

	@Column(name = "MD_LABEL_3")
	private String media3_Label;

	@Column(name = "MD_LABEL_4")
	private String media4_Label;

	@Column(name = "MD_LABEL_5")
	private String media5_Label;

	@Column(name = "MD_LABEL_6")
	private String media6_Label;

	@Column(name = "MD_LABEL_7")
	private String media7_Label;

	@Column(name = "MD_LABEL_8")
	private String media8_Label;

	@Column(name = "MD_LABEL_9")
	private String media9_Label;

	@Column(name = "MD_LABEL_10")
	private String media10_Label;

	@Column(name = "DT_LABEL_1")
	private String date1_Label;

	@Column(name = "DT_LABEL_2")
	private String date2_Label;

	@Column(name = "DT_LABEL_3")
	private String date3_Label;

	@Column(name = "DT_LABEL_4")
	private String date4_Label;

	@Column(name = "DT_LABEL_5")
	private String date5_Label;

	@Column(name = "DT_LABEL_6")
	private String date6_Label;

	@Column(name = "DT_LABEL_7")
	private String date7_Label;

	@Column(name = "DT_LABEL_8")
	private String date8_Label;

	@Column(name = "DT_LABEL_9")
	private String date9_Label;

	@Column(name = "DT_LABEL_10")
	private String date10_Label;

	@Column(name = "CAT_LABEL_1")
	private String category1_Label;

	@Column(name = "CAT_LABEL_2")
	private String category2_Label;

	@Column(name = "CAT_LABEL_3")
	private String category3_Label;

	@Column(name = "CAT_LABEL_4")
	private String category4_Label;

	@Column(name = "CAT_LABEL_5")
	private String category5_Label;
	
	@Column(name = "REF_1")
	private String ref1;

	@Column(name = "STATUS_1", columnDefinition = "int default 0")
	private Long status1;
	
	@Column(name = "STATUS_2", columnDefinition = "int default 0")
	private Long status2;
	
	@Column(name = "STATUS_3", columnDefinition = "int default 0")
	private Long status3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaMain() {
		return mediaMain;
	}

	public void setMediaMain(String mediaMain) {
		this.mediaMain = mediaMain;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public EdUserEntity getClientUser() {
		return clientUser;
	}

	public void setClientUser(EdUserEntity clientUser) {
		this.clientUser = clientUser;
	}

	public Set<EdContentDataEntity> getEdContentDatas() {
		return edContentDatas;
	}

	public void setEdContentDatas(Set<EdContentDataEntity> edContentDatas) {
		this.edContentDatas = edContentDatas;
	}

	public String getSt1_Label() {
		return st1_Label;
	}

	public void setSt1_Label(String st1_Label) {
		this.st1_Label = st1_Label;
	}

	public String getSt2_Label() {
		return st2_Label;
	}

	public void setSt2_Label(String st2_Label) {
		this.st2_Label = st2_Label;
	}

	public String getSt3_Label() {
		return st3_Label;
	}

	public void setSt3_Label(String st3_Label) {
		this.st3_Label = st3_Label;
	}

	public String getSt4_Label() {
		return st4_Label;
	}

	public void setSt4_Label(String st4_Label) {
		this.st4_Label = st4_Label;
	}

	public String getSt5_Label() {
		return st5_Label;
	}

	public void setSt5_Label(String st5_Label) {
		this.st5_Label = st5_Label;
	}

	public String getSt6_Label() {
		return st6_Label;
	}

	public void setSt6_Label(String st6_Label) {
		this.st6_Label = st6_Label;
	}

	public String getSt7_Label() {
		return st7_Label;
	}

	public void setSt7_Label(String st7_Label) {
		this.st7_Label = st7_Label;
	}

	public String getSt8_Label() {
		return st8_Label;
	}

	public void setSt8_Label(String st8_Label) {
		this.st8_Label = st8_Label;
	}

	public String getSt9_Label() {
		return st9_Label;
	}

	public void setSt9_Label(String st9_Label) {
		this.st9_Label = st9_Label;
	}

	public String getSt10_Label() {
		return st10_Label;
	}

	public void setSt10_Label(String st10_Label) {
		this.st10_Label = st10_Label;
	}

	public String getLt1_Label() {
		return lt1_Label;
	}

	public void setLt1_Label(String lt1_Label) {
		this.lt1_Label = lt1_Label;
	}

	public String getLt2_Label() {
		return lt2_Label;
	}

	public void setLt2_Label(String lt2_Label) {
		this.lt2_Label = lt2_Label;
	}

	public String getLt3_Label() {
		return lt3_Label;
	}

	public void setLt3_Label(String lt3_Label) {
		this.lt3_Label = lt3_Label;
	}

	public String getLt4_Label() {
		return lt4_Label;
	}

	public void setLt4_Label(String lt4_Label) {
		this.lt4_Label = lt4_Label;
	}

	public String getLt5_Label() {
		return lt5_Label;
	}

	public void setLt5_Label(String lt5_Label) {
		this.lt5_Label = lt5_Label;
	}

	public String getLt6_Label() {
		return lt6_Label;
	}

	public void setLt6_Label(String lt6_Label) {
		this.lt6_Label = lt6_Label;
	}

	public String getLt7_Label() {
		return lt7_Label;
	}

	public void setLt7_Label(String lt7_Label) {
		this.lt7_Label = lt7_Label;
	}

	public String getLt8_Label() {
		return lt8_Label;
	}

	public void setLt8_Label(String lt8_Label) {
		this.lt8_Label = lt8_Label;
	}

	public String getLt9_Label() {
		return lt9_Label;
	}

	public void setLt9_Label(String lt9_Label) {
		this.lt9_Label = lt9_Label;
	}

	public String getLt10_Label() {
		return lt10_Label;
	}

	public void setLt10_Label(String lt10_Label) {
		this.lt10_Label = lt10_Label;
	}

	public String getLn1_Label() {
		return ln1_Label;
	}

	public void setLn1_Label(String ln1_Label) {
		this.ln1_Label = ln1_Label;
	}

	public String getLn2_Label() {
		return ln2_Label;
	}

	public void setLn2_Label(String ln2_Label) {
		this.ln2_Label = ln2_Label;
	}

	public String getLn3_Label() {
		return ln3_Label;
	}

	public void setLn3_Label(String ln3_Label) {
		this.ln3_Label = ln3_Label;
	}

	public String getLn4_Label() {
		return ln4_Label;
	}

	public void setLn4_Label(String ln4_Label) {
		this.ln4_Label = ln4_Label;
	}

	public String getLn5_Label() {
		return ln5_Label;
	}

	public void setLn5_Label(String ln5_Label) {
		this.ln5_Label = ln5_Label;
	}

	public String getLn6_Label() {
		return ln6_Label;
	}

	public void setLn6_Label(String ln6_Label) {
		this.ln6_Label = ln6_Label;
	}

	public String getLn7_Label() {
		return ln7_Label;
	}

	public void setLn7_Label(String ln7_Label) {
		this.ln7_Label = ln7_Label;
	}

	public String getLn8_Label() {
		return ln8_Label;
	}

	public void setLn8_Label(String ln8_Label) {
		this.ln8_Label = ln8_Label;
	}

	public String getLn9_Label() {
		return ln9_Label;
	}

	public void setLn9_Label(String ln9_Label) {
		this.ln9_Label = ln9_Label;
	}

	public String getLn10_Label() {
		return ln10_Label;
	}

	public void setLn10_Label(String ln10_Label) {
		this.ln10_Label = ln10_Label;
	}

	public String getDn1_Label() {
		return dn1_Label;
	}

	public void setDn1_Label(String dn1_Label) {
		this.dn1_Label = dn1_Label;
	}

	public String getDn2_Label() {
		return dn2_Label;
	}

	public void setDn2_Label(String dn2_Label) {
		this.dn2_Label = dn2_Label;
	}

	public String getDn3_Label() {
		return dn3_Label;
	}

	public void setDn3_Label(String dn3_Label) {
		this.dn3_Label = dn3_Label;
	}

	public String getDn4_Label() {
		return dn4_Label;
	}

	public void setDn4_Label(String dn4_Label) {
		this.dn4_Label = dn4_Label;
	}

	public String getDn5_Label() {
		return dn5_Label;
	}

	public void setDn5_Label(String dn5_Label) {
		this.dn5_Label = dn5_Label;
	}

	public String getDn6_Label() {
		return dn6_Label;
	}

	public void setDn6_Label(String dn6_Label) {
		this.dn6_Label = dn6_Label;
	}

	public String getDn7_Label() {
		return dn7_Label;
	}

	public void setDn7_Label(String dn7_Label) {
		this.dn7_Label = dn7_Label;
	}

	public String getDn8_Label() {
		return dn8_Label;
	}

	public void setDn8_Label(String dn8_Label) {
		this.dn8_Label = dn8_Label;
	}

	public String getDn9_Label() {
		return dn9_Label;
	}

	public void setDn9_Label(String dn9_Label) {
		this.dn9_Label = dn9_Label;
	}

	public String getDn10_Label() {
		return dn10_Label;
	}

	public void setDn10_Label(String dn10_Label) {
		this.dn10_Label = dn10_Label;
	}

	public String getMedia1_Label() {
		return media1_Label;
	}

	public void setMedia1_Label(String media1_Label) {
		this.media1_Label = media1_Label;
	}

	public String getMedia2_Label() {
		return media2_Label;
	}

	public void setMedia2_Label(String media2_Label) {
		this.media2_Label = media2_Label;
	}

	public String getMedia3_Label() {
		return media3_Label;
	}

	public void setMedia3_Label(String media3_Label) {
		this.media3_Label = media3_Label;
	}

	public String getMedia4_Label() {
		return media4_Label;
	}

	public void setMedia4_Label(String media4_Label) {
		this.media4_Label = media4_Label;
	}

	public String getMedia5_Label() {
		return media5_Label;
	}

	public void setMedia5_Label(String media5_Label) {
		this.media5_Label = media5_Label;
	}

	public String getMedia6_Label() {
		return media6_Label;
	}

	public void setMedia6_Label(String media6_Label) {
		this.media6_Label = media6_Label;
	}

	public String getMedia7_Label() {
		return media7_Label;
	}

	public void setMedia7_Label(String media7_Label) {
		this.media7_Label = media7_Label;
	}

	public String getMedia8_Label() {
		return media8_Label;
	}

	public void setMedia8_Label(String media8_Label) {
		this.media8_Label = media8_Label;
	}

	public String getMedia9_Label() {
		return media9_Label;
	}

	public void setMedia9_Label(String media9_Label) {
		this.media9_Label = media9_Label;
	}

	public String getMedia10_Label() {
		return media10_Label;
	}

	public void setMedia10_Label(String media10_Label) {
		this.media10_Label = media10_Label;
	}

	public String getDate1_Label() {
		return date1_Label;
	}

	public void setDate1_Label(String date1_Label) {
		this.date1_Label = date1_Label;
	}

	public String getDate2_Label() {
		return date2_Label;
	}

	public void setDate2_Label(String date2_Label) {
		this.date2_Label = date2_Label;
	}

	public String getDate3_Label() {
		return date3_Label;
	}

	public void setDate3_Label(String date3_Label) {
		this.date3_Label = date3_Label;
	}

	public String getDate4_Label() {
		return date4_Label;
	}

	public void setDate4_Label(String date4_Label) {
		this.date4_Label = date4_Label;
	}

	public String getDate5_Label() {
		return date5_Label;
	}

	public void setDate5_Label(String date5_Label) {
		this.date5_Label = date5_Label;
	}

	public String getDate6_Label() {
		return date6_Label;
	}

	public void setDate6_Label(String date6_Label) {
		this.date6_Label = date6_Label;
	}

	public String getDate7_Label() {
		return date7_Label;
	}

	public void setDate7_Label(String date7_Label) {
		this.date7_Label = date7_Label;
	}

	public String getDate8_Label() {
		return date8_Label;
	}

	public void setDate8_Label(String date8_Label) {
		this.date8_Label = date8_Label;
	}

	public String getDate9_Label() {
		return date9_Label;
	}

	public void setDate9_Label(String date9_Label) {
		this.date9_Label = date9_Label;
	}

	public String getDate10_Label() {
		return date10_Label;
	}

	public void setDate10_Label(String date10_Label) {
		this.date10_Label = date10_Label;
	}

	public String getCategory1_Label() {
		return category1_Label;
	}

	public void setCategory1_Label(String category1_Label) {
		this.category1_Label = category1_Label;
	}

	public String getCategory2_Label() {
		return category2_Label;
	}

	public void setCategory2_Label(String category2_Label) {
		this.category2_Label = category2_Label;
	}

	public String getCategory3_Label() {
		return category3_Label;
	}

	public void setCategory3_Label(String category3_Label) {
		this.category3_Label = category3_Label;
	}

	public String getCategory4_Label() {
		return category4_Label;
	}

	public void setCategory4_Label(String category4_Label) {
		this.category4_Label = category4_Label;
	}

	public String getCategory5_Label() {
		return category5_Label;
	}

	public void setCategory5_Label(String category5_Label) {
		this.category5_Label = category5_Label;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
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

	
	
	
}
