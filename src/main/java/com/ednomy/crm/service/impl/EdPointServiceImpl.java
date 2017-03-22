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

import com.ednomy.crm.convertor.EdPointConvertor;
import com.ednomy.crm.entity.EdPointEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdPoint;
import com.ednomy.crm.repository.EdPointRepository;
import com.ednomy.crm.service.EdPointService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.ReflectionUtil;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdPointValidator;

@Service
public class EdPointServiceImpl implements EdPointService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdPointRepository edPointRepository;

	@Autowired
	EdPointConvertor edPointConvertor;

	@Autowired
	EdPointValidator edPointValidator;

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
	public EdPoint save(EdPoint edPoint) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(edPoint, "bizClient");
		edPointValidator.validate(edPoint, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdPointEntity edPointEntity = edPointConvertor.deassembler(edPoint, 0);

		if (edPointEntity.getId() == null || edPointEntity.getId() == 0) {
			edPointEntity.setId(sequenceIncrementer.getValue());
		}
		
		

		edPointEntity = edPointRepository.save(edPointEntity);
		return edPointConvertor.assembler(edPointEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edPointRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdPoint get(Long id) {
		EdPointEntity tempCodeEntity = edPointRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edPointConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdPointEntity tempEdClientEntity = edPointRepository.findOne(id);
		if (tempEdClientEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edPointRepository.delete(tempEdClientEntity);
		}
	}

	@Override
	@Transactional
	public List<EdPoint> list(Map<String, String> queryMap) {
		List<EdPointEntity> edPointEntities = edPointRepository.list(queryMap);
		List<EdPoint> edPoints = new ArrayList<EdPoint>();
		Iterator<EdPointEntity> iterator = edPointEntities.iterator();
		while (iterator.hasNext()) {
			EdPointEntity edPointEntity = iterator.next();
			edPoints.add(edPointConvertor.assembler(edPointEntity, 0));
		}
		return edPoints;
	}

	@Override
	@Transactional
	public EdPoint patch(EdPoint edPoint) {
		EdPoint tempEdClient = get(edPoint.getId());
		copyUtility.copyUtil(edPoint, tempEdClient);
		return save(tempEdClient);
	}

}