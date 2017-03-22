package com.ednomy.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import com.ednomy.crm.commons.constants.EdModelConstants;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.query.EdQueryBuilder;
import com.ednomy.crm.commons.query.Filter;
import com.ednomy.crm.commons.query.QueryConstants;
import com.ednomy.crm.convertor.EdUserConvertor;
import com.ednomy.crm.entity.EdUserEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.model.metadatamodel.EdUserConstant;
import com.ednomy.crm.repository.EdUserRepository;
import com.ednomy.crm.service.EdUserService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.ReflectionUtil;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdUserValidator;

@Service
public class EdUserServiceImpl implements EdUserService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdUserRepository edUserRepository;

	@Autowired
	EdUserConvertor edUserConvertor;

	@Autowired
	EdUserValidator edUserValidator;

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
	public EdUser save(EdUser edUser) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(
				edUser, "edUser");
		edUserValidator.validate(edUser, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException
				.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdUserEntity edUserEntity = edUserConvertor.deassembler(edUser, 0);

		if (edUserEntity.getId() == null || edUserEntity.getId() == 0) {
			edUserEntity.setId(sequenceIncrementer.getValue());
		}

		edUserEntity = edUserRepository.save(edUserEntity);
		return edUserConvertor.assembler(edUserEntity, 0);
	}

	@Override
	@Transactional
	public EdUser signup(EdUser edUser) {

		if (reflectionUtil.isEmpty(edUser.getMobile())
				|| reflectionUtil.isEmpty(edUser.getPassword())
				|| reflectionUtil.isEmpty(edUser.getUserType())
				|| edUser.getClientUser() == null) {

			throw new EdnomyException(
					"Registered Mobile and password are compulsory");
		}

		if (!reflectionUtil.isEmpty(edUser.getMobile())
				&& !reflectionUtil.isEmpty(edUser.getPassword())
				&& !reflectionUtil.isEmpty(edUser.getUserType())
				&& edUser.getClientUser() != null) {

			// look for existing registration for this user
			Map<String, String> queryMap = new HashMap<String, String>();
			EdQueryBuilder dhq = new EdQueryBuilder();
			dhq.setPageQuery(1, 10);
			Filter filter = new Filter();
			filter.addFilterMulti(dhq, EdModelConstants.ED_USER,
					EdUserConstant.MOBILE1, QueryConstants.EQUALS,
					edUser.getMobile());

			filter.addFilterMulti(dhq, EdModelConstants.ED_USER + "."
					+ EdUserConstant.CLIENT_USER, EdUserConstant.ID,
					QueryConstants.EQUALS, edUser.getClientUser().getId() + "");
			filter.addFilterMulti(dhq, EdModelConstants.ED_USER,
					EdUserConstant.USER_TYPE, QueryConstants.EQUALS,
					edUser.getUserType());

			LOGGER.debug(dhq.getJsonString());

			queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER,
					dhq.getJsonString());
			List<EdUser> edUsers = list(queryMap);

			if (!edUsers.isEmpty()) {
				throw new EdnomyException(
						"Mobile Number Aleready registered please login");
			}

		}

		return null;

	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edUserRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdUser get(Long id) {
		EdUserEntity tempCodeEntity = edUserRepository.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edUserConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdUserEntity tempEdUserEntity = edUserRepository.findOne(id);
		if (tempEdUserEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edUserRepository.delete(tempEdUserEntity);
		}
	}

	@Override
	@Transactional
	public List<EdUser> list(Map<String, String> queryMap) {
		List<EdUserEntity> edUserEntities = edUserRepository.list(queryMap);
		List<EdUser> edUsers = new ArrayList<EdUser>();
		Iterator<EdUserEntity> iterator = edUserEntities.iterator();
		while (iterator.hasNext()) {
			EdUserEntity edUserEntity = iterator.next();
			edUsers.add(edUserConvertor.assembler(edUserEntity, 0));
		}
		return edUsers;
	}

	@Override
	@Transactional
	public EdUser patch(EdUser edUser) {

		EdUser tempEdUser = get(edUser.getId());
		copyUtility.copyUtil(edUser, tempEdUser);
		return save(tempEdUser);
	}

	@Override
	@Transactional
	public EdUser lastItem() {
		EdUserEntity edUserEntity = edUserRepository.lastItem();
		return edUserConvertor.assembler(edUserEntity, 0);
	}

	@Override
	@Transactional
	public EdUser findUser(String email, String password) {
		EdUser edUser = edUserConvertor.assembler(
				edUserRepository.findUserId(email, password), 0);
		if (edUser == null) {
			throw new EdnomyException("Unknown User :" + email);
		}
		String authToken = "";

		edUser.setAuthToken(authToken);
		patch(edUser);

		return edUser;
	}

	@Override
	@Transactional
	public EdUser findUser(Long id, String password) {
		EdUser edUser = edUserConvertor.assembler(
				edUserRepository.findUserId(id, password), 0);
		if (edUser == null) {
			throw new EdnomyException("Unknown User");
		}
		return edUser;
	}

	@Override
	@Transactional
	public EdUser findUser(Long id, String password, Long clientId) {
		EdUser edUser = edUserConvertor.assembler(
				edUserRepository.findUserId(id, password, clientId), 0);
		if (edUser == null) {
			throw new EdnomyException("Unknown User");
		}
		return edUser;
	}

	@Override
	@Transactional
	public EdUserEntity findUser(Long id, String password, Long clientid,
			String newPassword) {
		EdUserEntity edUserEntity = edUserRepository.findUserId(id,
				password, clientid);
		if (edUserEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}

		edUserEntity.setPassword(newPassword);
		return edUserRepository.save(edUserEntity);
	}

}