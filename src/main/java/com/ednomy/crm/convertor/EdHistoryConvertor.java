package com.ednomy.crm.convertor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdHistoryEntity;
import com.ednomy.crm.model.EdHistory;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.DocRepositoryUtil;

@Component
public class EdHistoryConvertor{

	@Autowired
	DocRepositoryUtil docRepositoryUtil;

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {};

	public EdHistory assembler(EdHistoryEntity edHistoryEntity, int level) {
		if (edHistoryEntity == null)
			return null;
		EdHistory edHistory = new EdHistory();
		copyUtility.copyUtil(edHistoryEntity, edHistory, ignoreFileds);
		return edHistory;
	}

	public EdHistoryEntity deassembler(EdHistory edHistory, int level) {
		if (edHistory == null)
			return null;
		EdHistoryEntity edHistoryEntity = new EdHistoryEntity();
		copyUtility.copyUtil(edHistory, edHistoryEntity, ignoreFileds);		
		return edHistoryEntity;
	}

	public Set<EdHistory> assembler(Set<EdHistoryEntity> edHistoryEntities,
			int level) {
		Set<EdHistory> edHistories = new HashSet<EdHistory>();
		Iterator<EdHistoryEntity> iterator = edHistoryEntities.iterator();
		while (iterator.hasNext()) {
			EdHistoryEntity edHistoryEntity = iterator.next();
			edHistories.add(assembler(edHistoryEntity, level));
		}
		return edHistories;
	}

	public Set<EdHistoryEntity> deassembler(Set<EdHistory> edHistories,
			int level) {
		Set<EdHistoryEntity> edHistoryEntities = new HashSet<EdHistoryEntity>();
		Iterator<EdHistory> iterator = edHistories.iterator();
		while (iterator.hasNext()) {
			EdHistory edHistory = iterator.next();
			edHistoryEntities.add(deassembler(edHistory, level));
		}
		return edHistoryEntities;
	}
}
