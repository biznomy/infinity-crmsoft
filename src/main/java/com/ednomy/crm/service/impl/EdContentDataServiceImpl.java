package com.ednomy.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.ednomy.crm.convertor.EdContentCollectionConvertor;
import com.ednomy.crm.convertor.EdContentDataConvertor;
import com.ednomy.crm.entity.EdContentDataEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.repository.EdContentDataRepository;
import com.ednomy.crm.service.EdContentCollectionService;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.UtilityClass;
import com.ednomy.crm.validator.EdContentDataValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class EdContentDataServiceImpl implements EdContentDataService {

	@Autowired
	UtilityClass UtilityClass;

	@Autowired
	EdContentDataRepository edContentDataRepository;

	@Autowired
	EdContentCollectionService edContentCollectionService;

	@Autowired
	EdContentCollectionConvertor edContentCollectionConvertor;

	@Autowired
	EdContentDataConvertor edContentDataConvertor;

	@Autowired
	EdContentDataValidator edContentDataValidator;

	@Autowired
	SequenceIncrementer sequenceIncrementer;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CopyUtility copyUtility;

	@Override
	@Transactional
	public EdContentData save(EdContentData edContentData) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(
				edContentData, "edContentData");
		edContentDataValidator.validate(edContentData, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException
				.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdContentDataEntity edContentDataEntity = edContentDataConvertor
				.deassembler(edContentData, 0);

		if (edContentDataEntity.getId() == null
				|| edContentDataEntity.getId() == 0) {
			edContentDataEntity.setId(sequenceIncrementer
					.getValue());
		}
		
		edContentDataEntity = edContentDataRepository
				.save(edContentDataEntity);

		return edContentDataConvertor.assembler(edContentDataEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edContentDataRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdContentData get(Long id) {
		EdContentDataEntity tempCodeEntity = edContentDataRepository
				.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edContentDataConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public List<EdContentData> getTest(Long id) {
		EdContentDataEntity tempCodeEntity = edContentDataRepository
				.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		
		String fetch = tempCodeEntity.getLt5();
		Map<String, String> queryMap = new HashMap<String, String>();
		if(!fetch.isEmpty() && fetch != null) {
			if(fetch.contains(",")){
				if(String.valueOf(fetch.charAt(0)).equalsIgnoreCase(","))
					fetch = fetch.substring(1);
				fetch = fetch.substring(0, fetch.lastIndexOf(','));
			}
			String query = "";
			if(UtilityClass.isNotNull(tempCodeEntity.getLn1())) {				
				if(tempCodeEntity.getLn1() == EdnomyConstants.APP_DATA_TEST_ALL_0) {
					query = "{\"query\":{\"edContentData\":{\"filter\": {\"type\":{\"notequals\":\"" + EdnomyConstants.APP_DATA_TYPE_DETAIL + "\"},\"ref1\": {\"in\":[" + fetch + "]}}}}}";
				} else if (tempCodeEntity.getLn1() == (EdnomyConstants.APP_DATA_TEST_QA_2)) {
					query = "{\"query\":{\"edContentData\":{\"filter\": {\"type\":{\"equals\":\"" + EdnomyConstants.APP_DATA_TYPE_PRACTICE + "\"},\"ref1\": {\"in\":[" + fetch + "]}}}}}";
				} else {
					query = "{\"query\":{\"edContentData\":{\"filter\": {\"type\":{\"equals\":\"" + EdnomyConstants.APP_DATA_TYPE_MCQ + "\"},\"ref1\": {\"in\":[" + fetch + "]}}}}}";
				}
			} else {
				query = "{\"query\":{\"edContentData\":{\"filter\": {\"type\":{\"equals\":\"" + EdnomyConstants.APP_DATA_TYPE_MCQ + "\"},\"ref1\": {\"in\":[" + fetch + "]}}}}}";
			}
			queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		}
		
		List<EdContentData> edContentDatas = list(queryMap);
		if(UtilityClass.isNotNull(edContentDatas)) {
			long seed = System.nanoTime();
			Collections.shuffle(edContentDatas, new Random(seed));
			Collections.shuffle(edContentDatas, new Random(seed));
		}
		if(UtilityClass.isNotNull(tempCodeEntity.getDn2())) {
			if(tempCodeEntity.getDn2() > 0 && tempCodeEntity.getDn2() < edContentDatas.size()) {
				edContentDatas = edContentDatas.subList(0, tempCodeEntity.getDn2().intValue());
			}
		}
		return edContentDatas;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdContentDataEntity tempEdContentDataEntity = edContentDataRepository
				.findOne(id);
		if (tempEdContentDataEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edContentDataRepository.delete(tempEdContentDataEntity);
		}
	}

	@Override
	@Transactional
	public List<EdContentData> list(Map<String, String> queryMap) {
		Long loginId = null;
		try {
			loginId = Long.valueOf(queryMap.get("loginId"));
		} catch (Exception e) {
		}
		List<EdContentDataEntity> edContentDataEntities = edContentDataRepository
				.list(queryMap);
		List<EdContentData> edContentDatas = new ArrayList<EdContentData>();
		Iterator<EdContentDataEntity> iterator = edContentDataEntities
				.iterator();
		while (iterator.hasNext()) {
			EdContentDataEntity edContentDataEntity = iterator.next();
			EdContentData edContentData = edContentDataConvertor.assembler(
					edContentDataEntity, 0);
			edContentDatas.add(edContentData);

		}
		return edContentDatas;
	}

	@Override
	@Transactional
	public List<EdContentData> random(Map<String, String> queryMap) {
		Long loginId = null;
		try {
			loginId = Long.valueOf(queryMap.get("loginId"));
		} catch (Exception e) {
		}
		String jsq = queryMap
				.get(EdQueryConstants.JSON_QUERY_PARAMETER);

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode objectNode = null;
		int max = 0;
		try {
			objectNode = (ObjectNode) objectMapper.readTree(jsq);
			if (objectNode.get(EdQueryConstants.PAGE_NODE) != null) {
				max = objectNode.get(EdQueryConstants.PAGE_NODE)
						.get(EdQueryConstants.PAGE_MAX).asInt();
				if (max < 0) {
					max = 10;
				}
				((ObjectNode) objectNode
						.get(EdQueryConstants.PAGE_NODE)).put(
						EdQueryConstants.PAGE_MAX, max * 10);
			}
		} catch (Exception e) {
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put(EdQueryConstants.JSON_QUERY_PARAMETER,
				objectNode.toString());

		List<EdContentDataEntity> edContentDataEntities = edContentDataRepository
				.list(result);
		long seed = System.nanoTime();
		Collections.shuffle(edContentDataEntities, new Random(seed));
		Collections.shuffle(edContentDataEntities, new Random(seed));
		if (edContentDataEntities.size() > max) {
			edContentDataEntities = edContentDataEntities.subList(0, max);
		}
		List<EdContentData> edContentDatas = new ArrayList<EdContentData>();
		Iterator<EdContentDataEntity> iterator = edContentDataEntities
				.iterator();
		while (iterator.hasNext()) {
			EdContentDataEntity edContentDataEntity = iterator.next();
			EdContentData edContentData = edContentDataConvertor.assembler(
					edContentDataEntity, 0);
			edContentDatas.add(edContentData);

		}
		return edContentDatas;
	}

	@Override
	@Transactional
	public EdContentData patch(EdContentData edContentData) {

		EdContentData tempEdContentData = get(edContentData.getId());
		copyUtility.copyUtil(edContentData, tempEdContentData);
		return save(tempEdContentData);
	}

	@Override
	@Transactional
	public EdContentData updateField(String fieldName, Long id,
			String fieldValue) {
		EdContentData edContentData = get(id);
		edContentData = (EdContentData) copyUtility.getPropertieValue(
				edContentData, fieldName, fieldValue);
		return save(edContentData);

	}

	
	/**
	 * @param companies value to st1
	 * @param sendMessage lt1
	 * @param mediaLink media1
	 * @param updatedBy st4
	 * @return
	 */
	
	@Override
	@Transactional
	public String updateCRMStatus(List<Long> companies, String sendMessage,
			String mediaLink, String updatedBy, String type, Long endUserId) {
		
		String[] statusStack = new String[companies.size()];
		
		Iterator<Long> iterator = companies.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Long dataId = (Long) iterator.next();
			if (dataId == null) {
				statusStack[count] = dataId+ " : id null";
				continue;
			}		
			
			try{
				get(dataId);
				EdContentData edContentData = new EdContentData();
				edContentData.setSt1(String.valueOf(dataId));
				edContentData.setSt2("out");
				edContentData.setSt3(type);
				edContentData.setSt4(updatedBy);
				edContentData.setSt5("campaign");
				edContentData.setLt1(sendMessage);
				edContentData.setMedia1(mediaLink);
				edContentData.setStatus1(1L);
				edContentData.setLanguage("en");
				edContentData.setType("Message");
				EdUser endUser = new EdUser();  
				endUser.setId(endUserId);
				edContentData.setEndUser(endUser);
				edContentData = save(edContentData);
				
				statusStack[count] = String.valueOf(dataId)+" : "+ edContentData.getId();
				
			}catch(Exception exception){
				statusStack[count] = String.valueOf(dataId)+" : "+ exception.getMessage();
			}
			count++;
		}
		
		// TODO Auto-generated method stub
		return Arrays.toString(statusStack);
	}

}
