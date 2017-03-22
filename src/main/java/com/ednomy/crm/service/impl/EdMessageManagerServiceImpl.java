package com.ednomy.crm.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.regexp.recompile;
import org.jboss.logging.Logger;
import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;

import com.amazonaws.AmazonClientException;
import com.ednomy.crm.commons.commu.EmailMessage;
import com.ednomy.crm.commons.commu.SMSMessage;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.ednomy.crm.convertor.EdMessageManagerConvertor;
import com.ednomy.crm.entity.EdContentDataEntity;
import com.ednomy.crm.entity.EdMessageManagerEntity;
import com.ednomy.crm.exception.EdnomyErrorMessage;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.generator.SequenceIncrementer;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.model.EdMessageManager;
import com.ednomy.crm.repository.EdMessageManagerRepository;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.EdMessageManagerService;
import com.ednomy.crm.service.TypeMessageService;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.EdResponse;
import com.ednomy.crm.validator.EdMessageManagerValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class EdMessageManagerServiceImpl implements EdMessageManagerService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdMessageManagerRepository edMessageManagerRepository;

	@Autowired
	EdMessageManagerConvertor edMessageManagerConvertor;

	@Autowired
	EdMessageManagerValidator edMessageManagerValidator;

	@Autowired
	SequenceIncrementer sequenceIncrementer;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CopyUtility copyUtility;

	@Autowired
	private TypeMessageService typeMessageService;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	EdContentDataService edContentDataService;

	@Override
	@Transactional
	public EdMessageManager save(EdMessageManager edMessageManager) {

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(
				edMessageManager, "edMessageManager");
		edMessageManagerValidator.validate(edMessageManager, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException
				.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		// save base64 File
		try {
			edMessageManagerValidator.saveAttachment(edMessageManager);
		} catch (AmazonClientException | IOException exception) {
			LOGGER.error(exception.getMessage(), exception);
		}

		EdMessageManagerEntity edMessageManagerEntity = edMessageManagerConvertor
				.deassembler(edMessageManager, 0);

		if (edMessageManagerEntity.getId() == null
				|| edMessageManagerEntity.getId() == 0) {
			edMessageManagerEntity.setId(sequenceIncrementer.getValue());
		}

		edMessageManagerEntity = edMessageManagerRepository
				.save(edMessageManagerEntity);
		return edMessageManagerConvertor.assembler(edMessageManagerEntity, 0);
	}

	@Override
	@Transactional
	public long count(Map<String, String> queryMap) {
		return edMessageManagerRepository.count(queryMap);
	}

	@Override
	@Transactional
	public EdMessageManager get(Long id) {
		EdMessageManagerEntity tempCodeEntity = edMessageManagerRepository
				.findOne(id);
		if (tempCodeEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		}
		return edMessageManagerConvertor.assembler(tempCodeEntity, 0);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		EdMessageManagerEntity tempEdMessageManagerEntity = edMessageManagerRepository
				.findOne(id);
		if (tempEdMessageManagerEntity == null) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.dataNotFound", new String[] { "" }, null));
		} else {
			edMessageManagerRepository.delete(tempEdMessageManagerEntity);
		}
	}

	@Override
	@Transactional
	public List<EdMessageManager> list(Map<String, String> queryMap) {
		List<EdMessageManagerEntity> edMessageManagerEntities = edMessageManagerRepository
				.list(queryMap);
		List<EdMessageManager> edMessageManagers = new ArrayList<EdMessageManager>();
		Iterator<EdMessageManagerEntity> iterator = edMessageManagerEntities
				.iterator();
		while (iterator.hasNext()) {
			EdMessageManagerEntity edMessageManagerEntity = iterator.next();
			edMessageManagers.add(edMessageManagerConvertor.assembler(
					edMessageManagerEntity, 0));
		}
		return edMessageManagers;
	}

	@Override
	@Transactional
	public EdMessageManager patch(EdMessageManager edMessageManager) {
		EdMessageManager tempEdMessageManager = get(edMessageManager.getId());
		copyUtility.copyUtil(edMessageManager, tempEdMessageManager);
		return save(tempEdMessageManager);
	}

	@Override
	@Transactional
	public EdMessageManager retry(EdMessageManager edMessageManager) {
		EdMessageManager tempEdMessageManager = get(edMessageManager.getId());
		copyUtility.copyUtil(edMessageManager, tempEdMessageManager);
		tempEdMessageManager
				.setRetryCount(tempEdMessageManager.getRetryCount() + 1);
		return save(tempEdMessageManager);
	}

	@Override
	@Transactional
	public EdMessageManager later(EdMessageManager edMessageManager) {
		// SCHEDULED
		edMessageManager.setIsScheduled((long) EdnomyConstants.TRUE_INT);

		final BeanPropertyBindingResult result = new BeanPropertyBindingResult(
				edMessageManager, "edMessageManager");
		edMessageManagerValidator.validate(edMessageManager, result);
		final List<EdnomyErrorMessage> messageList = EdnomyException
				.listExceptions(result);
		if (!messageList.isEmpty()) {
			throw new EdnomyException(this.messageSource.getMessage(
					"webapi.response.validation.error", new String[] { "" },
					null), messageList);
		}

		EdMessageManagerEntity edMessageManagerEntity = edMessageManagerConvertor
				.deassembler(edMessageManager, 0);

		if (edMessageManagerEntity.getId() == null
				|| edMessageManagerEntity.getId() == 0) {
			edMessageManagerEntity.setId(sequenceIncrementer.getValue());
		}

		edMessageManagerEntity = edMessageManagerRepository
				.save(edMessageManagerEntity);
		return edMessageManagerConvertor.assembler(edMessageManagerEntity, 0);

	}

	@Override
	@Transactional
	public EdMessageManager emailNow(EdMessageManager edMessageManager) {
		edMessageManager.setType((long) EdnomyConstants.TYPE_EMAIL_INT);
		// edMessageManager = save(edMessageManager);
		sendEmail(edMessageManager);
		return edMessageManager;
	}

	@Override
	@Transactional
	public EdMessageManager emailLater(EdMessageManager edMessageManager) {
		edMessageManager.setType((long) EdnomyConstants.TYPE_EMAIL_INT);
		return later(edMessageManager);
	}

	@Override
	@Transactional
	public EdMessageManager smsNow(EdMessageManager edMessageManager) {
		edMessageManager.setType((long) EdnomyConstants.TYPE_SMS_INT);
		// edMessageManager = save(edMessageManager);
		sendSms(edMessageManager);
		return edMessageManager;
	}

	@Override
	@Transactional
	public EdMessageManager smsLater(EdMessageManager edMessageManager) {
		edMessageManager.setType((long) EdnomyConstants.TYPE_SMS_INT);
		return later(edMessageManager);
	}

	private void sendEmail(EdMessageManager edMessageManager) {
		try {
			if (edMessageManager.getParameters() != null) {
				replaceParameters(edMessageManager);
			}
			String multipleReciver[] = edMessageManager.getReceiver().split(
					"\\s*,\\s*");

			for (int i = 0; i < multipleReciver.length; i++) {
				EdMessageManager edMessageManagerq = new EdMessageManager();
				edMessageManagerq = edMessageManager;
				edMessageManagerq.setReceiver(multipleReciver[i]);
				edMessageManagerq = save(edMessageManagerq);
				EmailMessage emailMessage = new EmailMessage.Builder()
						.setId(edMessageManagerq.getId())
						.setSender(edMessageManagerq.getSender())
						.setReceiver(edMessageManagerq.getReceiver())
						.setSenderName(edMessageManagerq.getSenderName())
						.setSubject(edMessageManagerq.getTitle())
						.setBody(edMessageManagerq.getContent()).build();
				typeMessageService.sendMail(emailMessage);
			}

		} catch (Exception exception) {
			LOGGER.info(exception.getMessage(), exception);
		}
	}

	private void sendSms(EdMessageManager edMessageManager) {
		try {
			if (edMessageManager.getParameters() != null && !edMessageManager.getParameters().isEmpty()) {
				replaceParameters(edMessageManager);
			}
			String multipleReciver[] = edMessageManager.getReceiver().split(
					"\\s*,\\s*");

			for (int i = 0; i < multipleReciver.length; i++) {
				EdMessageManager edMessageManagerq = new EdMessageManager();
				edMessageManagerq = edMessageManager;
				edMessageManagerq.setReceiver(multipleReciver[i]);
				edMessageManagerq.setSender("ednomy");
				edMessageManagerq = save(edMessageManagerq);
				SMSMessage smsMessage = new SMSMessage.Builder()
						.setUrl(applicationProperties.getSmsUrl())
						.setSender(edMessageManagerq.getSender())
						.setReceiver(edMessageManagerq.getReceiver())
						.setContent(edMessageManagerq.getContent()).build();
				typeMessageService.sendSms(smsMessage);
			}
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage(), exception);
		}
	}

	@Override
	public void sendScheduledEmail(EdMessageManager edMessageManager) {
		try {
			sendEmail(edMessageManager);
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage(), exception);
		}
	}

	@Override
	public void sendScheduledSms(EdMessageManager edMessageManager) {
		try {
			sendSms(edMessageManager);
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage(), exception);
		}
	}

	/**
	 * this function replace parameters having key with ${title}/${body}
	 * 
	 * @param edMessageManager
	 */
	private void replaceParameters(EdMessageManager edMessageManager) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(edMessageManager
					.getParameters());
			String title = edMessageManager.getTitle();
			String body = edMessageManager.getContent();

			Iterator<String> it = jsonNode.fieldNames();
			while (it.hasNext()) {

				String key = it.next();

				if (key.contains("title")) {
					String value = String.valueOf(jsonNode.findPath(key)
							.asText());
					title = title.replace(key, value);
				} else if (key.contains("body")) {
					String value = String.valueOf(jsonNode.findPath(key)
							.asText());
					body = body.replace(key, value);
				}
			}
			edMessageManager.setTitle(title);
			edMessageManager.setContent(body);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* Pramod */
	@Override
	public EdMessageManager template() {
		EdMessageManager edMessageManager = new EdMessageManager();
		List<String> results = new ArrayList<String>();
		String sCurrentLine = "";
		File[] files = null;
		try {
			files = ResourceUtils.getFile("classpath:emailtemplate")
					.listFiles();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// If this pathname does not denote a directory, then listFiles()
		// returns null.

		for (File file : files) {
			if (file.isFile()) {
				try {
					sCurrentLine = FileUtils.readFileToString(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				results.add(sCurrentLine);

			}
		}
		edMessageManager.setTemplate(results);
		return edMessageManager;
	}

	@Override
	public JsonNode bulkMessage(Long id) {
		EdContentData content = edContentDataService.get(id);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = mapper.createObjectNode();
		ArrayNode arrayNumber = mapper.createArrayNode();
		ArrayNode arrayEmail = mapper.createArrayNode();
		if (content != null && content.getType().equalsIgnoreCase("campaign")) {
			System.out.println(content.getType());
			String[] parts = (content.getLt1()).split(",");
			for (int i = 0; i < parts.length; i++) {
				System.out.println(parts[i]);
				if (!parts[i].equalsIgnoreCase("")) {
					try {
						ObjectNode jsonNumber = mapper.createObjectNode();
						ObjectNode jsonEmail = mapper.createObjectNode();
						EdContentData content1 = edContentDataService.get(Long
								.parseLong(parts[i]));
						if (content1 != null && content1.getSt7() != null
								&& !content1.getSt7().equalsIgnoreCase("")) {
							jsonNumber.put("mobile", content1.getSt7());
							jsonNumber.put("id", parts[i]);
							jsonNumber.put("title", content1.getTitle());
							arrayNumber.add(jsonNumber);
						}
						if (content1 != null && content1.getSt5() != null
								&& !content1.getSt5().equalsIgnoreCase("")) {
							jsonEmail.put("id", parts[i]);
							jsonEmail.put("title", content1.getTitle());
							jsonEmail.put(parts[i], content1.getSt5());
							arrayEmail.add(jsonEmail);
						}
					} catch (Exception e) {
						System.out.println(parts[i]);
					}
				}
			}
			json.put("type","campaign");
			json.put("mobile", arrayNumber);
			json.put("email", arrayEmail);
		}
		return json;
	}
	
	public JsonNode bulkSender(JsonNode jsonNode){
		
		
		String text = jsonNode.findPath("dataContent").asText();
		
		JsonNode jsonObject = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonObject = objectMapper.readTree(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		Iterator<JsonNode> iterator = jsonObject.iterator();
		while (iterator.hasNext()) {
			JsonNode jsonNode2 = (JsonNode) iterator.next();
			EdMessageManager edmessager = new EdMessageManager();
			System.out.println(jsonNode2.toString());
			
			
		}
		
		return jsonObject;
		
	}
}