package com.ednomy.crm.convertor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdGoogleScrapperEntity;
import com.ednomy.crm.model.EdGoogleScrapper;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdGoogleScrapperConvertor{

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {};

	public EdGoogleScrapper assembler(EdGoogleScrapperEntity edGoogleScrapperEntity, int level) {
		if (edGoogleScrapperEntity == null)
			return null;
		EdGoogleScrapper edGoogleScrapper = new EdGoogleScrapper();
		copyUtility.copyUtil(edGoogleScrapperEntity, edGoogleScrapper, ignoreFileds);
		return edGoogleScrapper;
	}

	public EdGoogleScrapperEntity deassembler(EdGoogleScrapper edGoogleScrapper, int level) {
		if (edGoogleScrapper == null)
			return null;
		EdGoogleScrapperEntity edGoogleScrapperEntity = new EdGoogleScrapperEntity();
		copyUtility.copyUtil(edGoogleScrapper, edGoogleScrapperEntity, ignoreFileds);
		return edGoogleScrapperEntity;
	}

	public Set<EdGoogleScrapper> assembler(Set<EdGoogleScrapperEntity> edGoogleScrapperEntities,
			int level) {
		Set<EdGoogleScrapper> edGoogleScrappers = new HashSet<EdGoogleScrapper>();
		Iterator<EdGoogleScrapperEntity> iterator = edGoogleScrapperEntities.iterator();
		while (iterator.hasNext()) {
			EdGoogleScrapperEntity edGoogleScrapperEntity = iterator.next();
			edGoogleScrappers.add(assembler(edGoogleScrapperEntity, level));
		}
		return edGoogleScrappers;
	}

	public Set<EdGoogleScrapperEntity> deassembler(Set<EdGoogleScrapper> edGoogleScrappers,
			int level) {
		Set<EdGoogleScrapperEntity> edGoogleScrapperEntities = new HashSet<EdGoogleScrapperEntity>();
		Iterator<EdGoogleScrapper> iterator = edGoogleScrappers.iterator();
		while (iterator.hasNext()) {
			EdGoogleScrapper edGoogleScrapper = iterator.next();
			edGoogleScrapperEntities.add(deassembler(edGoogleScrapper, level));
		}
		return edGoogleScrapperEntities;
	}
}
