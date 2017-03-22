/**
 *
 */
package com.ednomy.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.ednomy.crm.model.EdMessageManager;
import com.ednomy.crm.service.EdMessageManagerService;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.EdResponse;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping(value = "/manager")
public class EdMessageManagerController {

	@Autowired
	EdMessageManagerService edMessageManagerService;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> delete(@PathVariable Long id) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		edMessageManagerService.delete(id);
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> get(@PathVariable Long id) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setResult(edMessageManagerService.get(id));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> list(@RequestParam Map<String, String> queryMap) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setCount(edMessageManagerService.count(queryMap));
		response.setResults(edMessageManagerService.list(queryMap));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> save(@RequestBody EdMessageManager edMessageManager) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setResult(edMessageManagerService.save(edMessageManager));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> update(@RequestBody EdMessageManager edMessageManager, @PathVariable Long id) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		
		edMessageManager.setId(id);
		response.setResult(edMessageManagerService.save(edMessageManager));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> patch(@RequestBody EdMessageManager edMessageManager, @PathVariable Long id) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		
		edMessageManager.setId(id);
		response.setResult(edMessageManagerService.patch(edMessageManager));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}
	@RequestMapping(value = "/email", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> email(@RequestBody EdMessageManager edMessageManager) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setResult((edMessageManager.getIsScheduled() == 0) ? edMessageManagerService.emailNow(edMessageManager) : edMessageManagerService.emailLater(edMessageManager));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
	
	@RequestMapping(value = "/sms", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> sms(@RequestBody EdMessageManager edMessageManager) {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setResult((edMessageManager.getIsScheduled() == 0) ? edMessageManagerService.smsNow(edMessageManager) : edMessageManagerService.smsLater(edMessageManager));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	} 
	
	@RequestMapping(value = "/template", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdMessageManager> template() {
		final EdResponse<EdMessageManager> response = new EdResponse<EdMessageManager>();
		response.setResult(edMessageManagerService.template());
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	@RequestMapping(value = "/bulk/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<JsonNode> BulkMessage(@PathVariable Long id) {
		final EdResponse<JsonNode> response = new EdResponse<JsonNode>();
		response.setResult(edMessageManagerService.bulkMessage(id));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	@RequestMapping(value = "/setting", method = RequestMethod.POST , consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<JsonNode> emailSetting(@RequestBody JsonNode jsonNode) {
		final EdResponse<JsonNode> response = new EdResponse<JsonNode>();
		//json = objectMapper.readTree(jsonStr);
		applicationProperties.setEmailHost(jsonNode.findValue("host").asText());
		applicationProperties.setEmailPort((jsonNode.findValue("port").asInt()));
		applicationProperties.setEmailUserName(jsonNode.findValue("userName").asText());
		applicationProperties.setEmailPassword(jsonNode.findValue("password").asText());
		//
		response.setResult(jsonNode);
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	@RequestMapping(value = "/bulk", method = RequestMethod.POST , consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<JsonNode> bulkSender(@RequestBody JsonNode jsonNode) {
		final EdResponse<JsonNode> response = new EdResponse<JsonNode>();
		
		response.setResult(edMessageManagerService.bulkSender(jsonNode));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	@Scheduled(fixedDelay=1000 * 900)
	public void emailScheduler() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(EdnomyConstants.EDNOMY_DB_DATE_FORMAT);
		String currentDateTime  = dateFormat.format(new Date());
		Map<String, String> queryMap = new HashMap<String, String>();
		String query =  "{\"query\":{\"edMessageManager\":{\"filter\":{\"deliveryDate\":[{\"op\":\"or\",\"le\":\""+currentDateTime+"\"},{\"op\":\"or\",\"isnull\":\"\"}],\"deliverStatus\":{\"equals\":0},\"type\":{\"equals\":0},\"retryCount\":{\"le\":10}}}}}";
	//	String query = "{\"query\":{\"edMessageManager\":{\"filter\":{\"deliveryDate\":{\"le\":\"2016-05-20 16:00:58\"},\"deliverStatus\":{\"equals\":0},\"retryCount\":{\"equals\":10}}}}}";
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		List<EdMessageManager> edMessageManager = edMessageManagerService.list(queryMap);
		Iterator<EdMessageManager> crunchifyIterator = edMessageManager.iterator();
		while (crunchifyIterator.hasNext()) {
			edMessageManagerService.emailNow(crunchifyIterator.next());
				}
	}
	

	@Scheduled(fixedDelay=1000 * 900)
	public void smsScheduler() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(EdnomyConstants.EDNOMY_DB_DATE_FORMAT);
		String currentDateTime  = dateFormat.format(new Date());
		Map<String, String> queryMap = new HashMap<String, String>();
		String query =  "{\"query\":{\"edMessageManager\":{\"filter\":{\"deliveryDate\":[{\"op\":\"or\",\"le\":\""+currentDateTime+"\"},{\"op\":\"or\",\"isnull\":\"\"}],\"deliverStatus\":{\"equals\":0},\"type\":{\"equals\":1},\"retryCount\":{\"le\":10}}}}}";
	//	String query = "{\"query\":{\"edMessageManager\":{\"filter\":{\"deliveryDate\":{\"le\":\"2016-05-20 16:00:58\"},\"deliverStatus\":{\"equals\":0},\"retryCount\":{\"equals\":10}}}}}";
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		List<EdMessageManager> edMessageManager = edMessageManagerService.list(queryMap);
		Iterator<EdMessageManager> crunchifyIterator = edMessageManager.iterator();
		while (crunchifyIterator.hasNext()) {
			edMessageManagerService.smsNow(crunchifyIterator.next());
				}
	}
}