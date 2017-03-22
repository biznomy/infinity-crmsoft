package com.ednomy.crm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import com.ednomy.crm.convertor.EdContentCollectionConvertor;
import com.ednomy.crm.entity.EdContentCollectionEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdContentCollection;
import com.ednomy.crm.repository.EdContentCollectionRepository;
import com.ednomy.crm.service.EdContentCollectionService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.validator.EdContentCollectionValidator;

@Service
public class EdContentCollectionServiceImpl implements EdContentCollectionService {

	@Autowired
	EdContentCollectionRepository edContentCollectionRepository;

	@Autowired
	EdContentCollectionConvertor edContentCollectionConvertor;

	@Autowired
	SequenceIncrementer sequenceIncrementer;

	@Autowired
	EdContentCollectionValidator edContentCollectionValidator;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CopyUtility copyUtility;

	@Override
	@Transactional
	public EdContentCollection save(EdContentCollection edContentCollection) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(
				edContentCollection, "edContentCollection");
		edContentCollectionValidator.validate(edContentCollection, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" }, null), messageList);
		}
		
		EdContentCollectionEntity edContentCollectionEntity = edContentCollectionConvertor
				.deassembler(edContentCollection, 0);

		if (edContentCollectionEntity.getId() == null || edContentCollectionEntity.getId() == 0) {
			edContentCollectionEntity.setId(sequenceIncrementer.getValue());
		}

		edContentCollectionEntity = edContentCollectionRepository.save(edContentCollectionEntity);
		return edContentCollectionConvertor.assembler(edContentCollectionEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edContentCollectionRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdContentCollection get(Long id) {
		EdContentCollectionEntity tempCodeEntity = edContentCollectionRepository
				.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edContentCollectionConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdContentCollectionEntity tempEdContentCollectionEntity = edContentCollectionRepository
				.findOne(id);
		if (tempEdContentCollectionEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edContentCollectionRepository
					.delete(tempEdContentCollectionEntity);
		}
	}

	@Override
	@Transactional
	public List<EdContentCollection> list(Map<String, String> queryMap) {
		List<EdContentCollectionEntity> edContentCollectionEntities = edContentCollectionRepository
				.list(queryMap);
		List<EdContentCollection> edContentCollections = new ArrayList<EdContentCollection>();
		Iterator<EdContentCollectionEntity> iterator = edContentCollectionEntities
				.iterator();
		while (iterator.hasNext()) {
			EdContentCollectionEntity edContentCollectionEntity = iterator
					.next();
			edContentCollections.add(edContentCollectionConvertor.assembler(
					edContentCollectionEntity, 0));
		}
		return edContentCollections;
	}

	@Override
	@Transactional
	public EdContentCollection patch(EdContentCollection edContentCollection) {

		EdContentCollection tempEdContentCollection = get(edContentCollection.getId());
		copyUtility.copyUtil(edContentCollection, tempEdContentCollection);
		return save(tempEdContentCollection);
	}
	

}
