package com.ednomy.crm.convertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdMessageManagerEntity;
import com.ednomy.crm.model.EdMessageManager;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdMessageManagerConvertor{

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {""};

	public EdMessageManager assembler(EdMessageManagerEntity edMessageManagerEntity, int level) {
		if (edMessageManagerEntity == null)
			return null;
		EdMessageManager edMessageManager = new EdMessageManager();

		copyUtility.copyUtil(edMessageManagerEntity, edMessageManager, ignoreFileds);
				
		return edMessageManager;
	}

	public EdMessageManagerEntity deassembler(EdMessageManager edMessageManager, int level) {
		if (edMessageManager == null)
			return null;

		EdMessageManagerEntity edMessageManagerEntity = new EdMessageManagerEntity();

		copyUtility.copyUtil(edMessageManager, edMessageManagerEntity, ignoreFileds);

		return edMessageManagerEntity;
	}

	public List<EdMessageManager> assembler(List<EdMessageManagerEntity> edMessageManagerEntities, int level) {

		List<EdMessageManager> edMessageManagers = new ArrayList<EdMessageManager>();
		Iterator<EdMessageManagerEntity> iterator = edMessageManagerEntities.iterator();
		while (iterator.hasNext()) {
			EdMessageManagerEntity edMessageManagerEntity = iterator.next();
			edMessageManagers.add(assembler(edMessageManagerEntity, level));
		}
		return edMessageManagers;

	}

	public List<EdMessageManagerEntity> deassembler(List<EdMessageManager> edMessageManagers, int level) {

		List<EdMessageManagerEntity> edMessageManagerEntities = new ArrayList<EdMessageManagerEntity>();
		Iterator<EdMessageManager> iterator = edMessageManagers.iterator();
		while (iterator.hasNext()) {
			EdMessageManager edMessageManager = iterator.next();
			edMessageManagerEntities.add(deassembler(edMessageManager, level));
		}
		return edMessageManagerEntities;

	}

}
