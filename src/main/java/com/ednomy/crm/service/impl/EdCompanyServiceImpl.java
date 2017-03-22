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

import com.ednomy.crm.convertor.EdCompanyConvertor;
import com.ednomy.crm.entity.EdCompanyEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdCompany;
import com.ednomy.crm.repository.EdCompanyRepository;
import com.ednomy.crm.service.EdCompanyService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.ReflectionUtil;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdCompanyValidator;

@Service
public class EdCompanyServiceImpl implements EdCompanyService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdCompanyRepository edCompanyRepository;

	@Autowired
	EdCompanyConvertor edCompanyConvertor;

	@Autowired
	EdCompanyValidator edCompanyValidator;

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
	public EdCompany save(EdCompany edCompany) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(edCompany, "edCompany");
		edCompanyValidator.validate(edCompany, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdCompanyEntity edCompanyEntity = edCompanyConvertor.deassembler(edCompany, 0);

		if (edCompanyEntity.getId() == null || edCompanyEntity.getId() == 0) {
			edCompanyEntity.setId(sequenceIncrementer.getValue());
		}
		
		

		edCompanyEntity = edCompanyRepository.save(edCompanyEntity);
		return edCompanyConvertor.assembler(edCompanyEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edCompanyRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdCompany get(Long id) {
		EdCompanyEntity tempCodeEntity = edCompanyRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edCompanyConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdCompanyEntity tempEdCompanyEntity = edCompanyRepository.findOne(id);
		if (tempEdCompanyEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edCompanyRepository.delete(tempEdCompanyEntity);
		}
	}

	@Override
	@Transactional
	public List<EdCompany> list(Map<String, String> queryMap) {
		List<EdCompanyEntity> edCompanyEntities = edCompanyRepository.list(queryMap);
		List<EdCompany> edCompanys = new ArrayList<EdCompany>();
		Iterator<EdCompanyEntity> iterator = edCompanyEntities.iterator();
		while (iterator.hasNext()) {
			EdCompanyEntity edCompanyEntity = iterator.next();
			edCompanys.add(edCompanyConvertor.assembler(edCompanyEntity, 0));
		}
		return edCompanys;
	}

	@Override
	@Transactional
	public EdCompany patch(EdCompany edCompany) {
		EdCompany tempEdCompany = get(edCompany.getId());
		copyUtility.copyUtil(edCompany, tempEdCompany);
		return save(tempEdCompany);
	}

}