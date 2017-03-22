package com.ednomy.crm.convertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdCrmEntity;
import com.ednomy.crm.model.EdCrm;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdCrmConvertor{

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {};

	public EdCrm assembler(EdCrmEntity edCrmEntity, int level) {
		if (edCrmEntity == null)
			return null;
		EdCrm edCrm = new EdCrm();

		copyUtility.copyUtil(edCrmEntity, edCrm, ignoreFileds);
				
		return edCrm;
	}

	public EdCrmEntity deassembler(EdCrm edCrm, int level) {
		if (edCrm == null)
			return null;

		EdCrmEntity edCrmEntity = new EdCrmEntity();

		copyUtility.copyUtil(edCrm, edCrmEntity, ignoreFileds);

		return edCrmEntity;
	}

	public List<EdCrm> assembler(List<EdCrmEntity> edCrmEntities, int level) {

		List<EdCrm> edCrms = new ArrayList<EdCrm>();
		Iterator<EdCrmEntity> iterator = edCrmEntities.iterator();
		while (iterator.hasNext()) {
			EdCrmEntity edCrmEntity = iterator.next();
			edCrms.add(assembler(edCrmEntity, level));
		}
		return edCrms;

	}

	public List<EdCrmEntity> deassembler(List<EdCrm> edCrms, int level) {

		List<EdCrmEntity> edCrmEntities = new ArrayList<EdCrmEntity>();
		Iterator<EdCrm> iterator = edCrms.iterator();
		while (iterator.hasNext()) {
			EdCrm edCrm = iterator.next();
			edCrmEntities.add(deassembler(edCrm, level));
		}
		return edCrmEntities;

	}

}
