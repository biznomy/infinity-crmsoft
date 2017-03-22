package com.ednomy.crm.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ednomy.crm.entity.EdCompanyEntity;
import com.ednomy.crm.model.EdCompany;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdCompanyConvertor{

	@Autowired
	CopyUtility copyUtility;
	
	private final String[] ignoreFileds = {"address", "emails", "mobile", "landline", "socialAccount", "remarks"};

	public EdCompany assembler(EdCompanyEntity edCompanyEntity, int level) {
		if (edCompanyEntity == null)
			return null;
		EdCompany edCompany = new EdCompany();

		copyUtility.copyUtil(edCompanyEntity, edCompany, ignoreFileds);
		
		
		edCompany.setAddress(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getAddress().replace("[", "").replace("]", "")));
		edCompany.setEmails(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getEmails().replace("[", "").replace("]", "")));
		edCompany.setMobile(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getMobile().replace("[", "").replace("]", "")));
		edCompany.setLandline(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getLandline().replace("[", "").replace("]", "")));
		edCompany.setSocialAccount(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getSocialAccount().replace("[", "").replace("]", "")));
		edCompany.setRemarks(StringUtils.commaDelimitedListToStringArray(edCompanyEntity.getRemarks().replace("[", "").replace("]", "")));
				
		return edCompany;
	}

	public EdCompanyEntity deassembler(EdCompany edCompany, int level) {
		if (edCompany == null)
			return null;

		EdCompanyEntity edCompanyEntity = new EdCompanyEntity();

		copyUtility.copyUtil(edCompany, edCompanyEntity, ignoreFileds);
		
		edCompanyEntity.setAddress(Arrays.toString(edCompany.getAddress()).replace("[", "").replace("]", ""));
		edCompanyEntity.setEmails(Arrays.toString(edCompany.getEmails()).replace("[", "").replace("]", ""));
		edCompanyEntity.setMobile(Arrays.toString(edCompany.getMobile()).replace("[", "").replace("]", ""));
		edCompanyEntity.setLandline(Arrays.toString(edCompany.getLandline()).replace("[", "").replace("]", ""));
		edCompanyEntity.setSocialAccount(Arrays.toString(edCompany.getSocialAccount()).replace("[", "").replace("]", ""));
		edCompanyEntity.setRemarks(Arrays.toString(edCompany.getRemarks()).replace("[", "").replace("]", ""));
		
		return edCompanyEntity;
	}

	public List<EdCompany> assembler(List<EdCompanyEntity> edCompanyEntities, int level) {

		List<EdCompany> edCompanys = new ArrayList<EdCompany>();
		Iterator<EdCompanyEntity> iterator = edCompanyEntities.iterator();
		while (iterator.hasNext()) {
			EdCompanyEntity edCompanyEntity = iterator.next();
			edCompanys.add(assembler(edCompanyEntity, level));
		}
		return edCompanys;

	}

	public List<EdCompanyEntity> deassembler(List<EdCompany> edCompanys, int level) {

		List<EdCompanyEntity> edCompanyEntities = new ArrayList<EdCompanyEntity>();
		Iterator<EdCompany> iterator = edCompanys.iterator();
		while (iterator.hasNext()) {
			EdCompany edCompany = iterator.next();
			edCompanyEntities.add(deassembler(edCompany, level));
		}
		return edCompanyEntities;

	}

}
