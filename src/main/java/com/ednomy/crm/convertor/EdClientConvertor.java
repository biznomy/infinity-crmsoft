package com.ednomy.crm.convertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdClientEntity;
import com.ednomy.crm.model.EdClient;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdClientConvertor{

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {};

	public EdClient assembler(EdClientEntity edClientEntity, int level) {
		if (edClientEntity == null)
			return null;
		EdClient edClient = new EdClient();

		copyUtility.copyUtil(edClientEntity, edClient, ignoreFileds);
				
		return edClient;
	}

	public EdClientEntity deassembler(EdClient edClient, int level) {
		if (edClient == null)
			return null;

		EdClientEntity edClientEntity = new EdClientEntity();

		copyUtility.copyUtil(edClient, edClientEntity, ignoreFileds);

		return edClientEntity;
	}

	public List<EdClient> assembler(List<EdClientEntity> edClientEntities, int level) {

		List<EdClient> edClients = new ArrayList<EdClient>();
		Iterator<EdClientEntity> iterator = edClientEntities.iterator();
		while (iterator.hasNext()) {
			EdClientEntity edClientEntity = iterator.next();
			edClients.add(assembler(edClientEntity, level));
		}
		return edClients;

	}

	public List<EdClientEntity> deassembler(List<EdClient> edClients, int level) {

		List<EdClientEntity> edClientEntities = new ArrayList<EdClientEntity>();
		Iterator<EdClient> iterator = edClients.iterator();
		while (iterator.hasNext()) {
			EdClient edClient = iterator.next();
			edClientEntities.add(deassembler(edClient, level));
		}
		return edClientEntities;

	}

}
