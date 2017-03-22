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
import org.springframework.validation.BeanPropertyBindingResult;

import com.ednomy.crm.convertor.EdClientConvertor;
import com.ednomy.crm.entity.EdClientEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdClient;
import com.ednomy.crm.repository.EdClientRepository;
import com.ednomy.crm.service.EdClientService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.ReflectionUtil;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdClientValidator;

@Service
public class EdClientServiceImpl implements EdClientService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdClientRepository edClientRepository;

	@Autowired
	EdClientConvertor edClientConvertor;

	@Autowired
	EdClientValidator edClientValidator;

	@Autowired
	SequenceIncrementer sequenceIncrementer;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CopyUtility copyUtility;

	@Autowired
	private ReflectionUtil reflectionUtil;

	@Autowired
	private UtilityClass utility;

	@Override
	@Transactional
	public EdClient save(EdClient edClient) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(edClient, "edClient");
		edClientValidator.validate(edClient, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdClientEntity edClientEntity = edClientConvertor.deassembler(edClient, 0);

		if (edClientEntity.getId() == null || edClientEntity.getId() == 0) {
			edClientEntity.setId(sequenceIncrementer.getValue());
		}
		
		

		edClientEntity = edClientRepository.save(edClientEntity);
		return edClientConvertor.assembler(edClientEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edClientRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdClient get(Long id) {
		EdClientEntity tempCodeEntity = edClientRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edClientConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdClientEntity tempEdClientEntity = edClientRepository.findOne(id);
		if (tempEdClientEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edClientRepository.delete(tempEdClientEntity);
		}
	}

	@Override
	@Transactional
	public List<EdClient> list(Map<String, String> queryMap) {
		List<EdClientEntity> edClientEntities = edClientRepository.list(queryMap);
		List<EdClient> edClients = new ArrayList<EdClient>();
		Iterator<EdClientEntity> iterator = edClientEntities.iterator();
		while (iterator.hasNext()) {
			EdClientEntity edClientEntity = iterator.next();
			edClients.add(edClientConvertor.assembler(edClientEntity, 0));
		}
		return edClients;
	}

	@Override
	@Transactional
	public EdClient patch(EdClient edClient) {
		EdClient tempEdClient = get(edClient.getId());
		copyUtility.copyUtil(edClient, tempEdClient);
		return save(tempEdClient);
	}

}