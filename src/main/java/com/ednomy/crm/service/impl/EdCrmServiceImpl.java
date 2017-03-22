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

import com.ednomy.crm.convertor.EdCrmConvertor;
import com.ednomy.crm.entity.EdCrmEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdCrm;
import com.ednomy.crm.repository.EdCrmRepository;
import com.ednomy.crm.service.EdCrmService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.ReflectionUtil;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdCrmValidator;

@Service
public class EdCrmServiceImpl implements EdCrmService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdCrmRepository edCrmRepository;

	@Autowired
	EdCrmConvertor edCrmConvertor;

	@Autowired
	EdCrmValidator edCrmValidator;

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
	public EdCrm save(EdCrm edCrm) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(edCrm, "edCrm");
		edCrmValidator.validate(edCrm, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdCrmEntity edCrmEntity = edCrmConvertor.deassembler(edCrm, 0);

		if (edCrmEntity.getId() == null || edCrmEntity.getId() == 0) {
			edCrmEntity.setId(sequenceIncrementer.getValue());
		}
		
		

		edCrmEntity = edCrmRepository.save(edCrmEntity);
		return edCrmConvertor.assembler(edCrmEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edCrmRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdCrm get(Long id) {
		EdCrmEntity tempCodeEntity = edCrmRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edCrmConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdCrmEntity tempEdClientEntity = edCrmRepository.findOne(id);
		if (tempEdClientEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edCrmRepository.delete(tempEdClientEntity);
		}
	}

	@Override
	@Transactional
	public List<EdCrm> list(Map<String, String> queryMap) {
		List<EdCrmEntity> edCrmEntities = edCrmRepository.list(queryMap);
		List<EdCrm> edCrms = new ArrayList<EdCrm>();
		Iterator<EdCrmEntity> iterator = edCrmEntities.iterator();
		while (iterator.hasNext()) {
			EdCrmEntity edCrmEntity = iterator.next();
			edCrms.add(edCrmConvertor.assembler(edCrmEntity, 0));
		}
		return edCrms;
	}

	@Override
	@Transactional
	public EdCrm patch(EdCrm edCrm) {
		EdCrm tempEdClient = get(edCrm.getId());
		copyUtility.copyUtil(edCrm, tempEdClient);
		return save(tempEdClient);
	}
	
	
	
	
}