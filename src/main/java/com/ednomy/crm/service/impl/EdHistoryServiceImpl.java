package com.ednomy.crm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ednomy.crm.convertor.EdHistoryConvertor;
import com.ednomy.crm.entity.EdHistoryEntity;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdHistory;
import com.ednomy.crm.repository.EdHistoryRepository;
import com.ednomy.crm.service.EdHistoryService;
import com.ednomy.crm.util.CopyUtility;

@Service
public class EdHistoryServiceImpl implements EdHistoryService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(EdHistoryServiceImpl.class);

	@Autowired
	EdHistoryRepository edHistoryRepository;
	
	@Autowired
	EdHistoryConvertor edHistoryConvertor;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private CopyUtility copyUtility;

	@Override
	@Transactional
	public EdHistory save(EdHistory edHistory) {
		EdHistoryEntity edHistoryEntity = edHistoryConvertor.deassembler(edHistory, 0);
		edHistoryEntity = edHistoryRepository.save(edHistoryEntity);
		return edHistoryConvertor.assembler(edHistoryEntity, 0);
	}
	
	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edHistoryRepository.count(queryMap);
	}
	
	@Override
	@Transactional
	public EdHistory get(Long id) {
		EdHistoryEntity tempCodeEntity = edHistoryRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edHistoryConvertor.assembler(tempCodeEntity, 0);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		EdHistoryEntity tempEdHistoryEntity = edHistoryRepository
				.findOne(id);
		if (tempEdHistoryEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edHistoryRepository.delete(tempEdHistoryEntity);
		}
	}

	@Override
	@Transactional
	public List<EdHistory> list(Map<String, String> queryMap) {
		List<EdHistoryEntity> edHistoryEntities = edHistoryRepository
				.list(queryMap);
		List<EdHistory> edHistories = new ArrayList<EdHistory>();
		Iterator<EdHistoryEntity> iterator = edHistoryEntities
				.iterator();
		while (iterator.hasNext()) {
			EdHistoryEntity edHistoryEntity = iterator.next();
			EdHistory edHistory = edHistoryConvertor.assembler(edHistoryEntity, 0);
			edHistories.add(edHistory);
		}
		return edHistories;
	}
	
	
	@Override
	@Transactional
	public EdHistory patch(EdHistory edHistory) {
		EdHistory tempEdHistory = get(edHistory.getId());
		copyUtility.copyUtil(edHistory, tempEdHistory);
		return save(tempEdHistory);
	}

	@Override
	@Transactional
	public EdHistory updateField(String fieldName, Long id,
			String fieldValue) {
		EdHistory edHistory = get(id);
		edHistory = (EdHistory) copyUtility.getPropertieValue(
				edHistory, fieldName, fieldValue);
		return save(edHistory);
	}

}