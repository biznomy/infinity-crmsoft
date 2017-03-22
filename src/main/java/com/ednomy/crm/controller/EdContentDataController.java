/**
 *
 */
package com.ednomy.crm.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.util.EdResponse;

@Controller
@RequestMapping(value = "/contentdata")
public class EdContentDataController {

	@Autowired
	EdContentDataService edContentDataService;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EdContentDataController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> delete(@PathVariable Long id) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		edContentDataService.delete(id);
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> get(@PathVariable Long id) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		response.setResult(edContentDataService.get(id));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> list(@RequestParam Map<String, String> queryMap) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		response.setCount(edContentDataService.count(queryMap));
		response.setResults(edContentDataService.list(queryMap));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> save(@RequestBody EdContentData edContentData) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		response.setResult(edContentDataService.save(edContentData));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE ,produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> saveForm(EdContentData edContentData) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		response.setResult(edContentDataService.save(edContentData));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> update(EdContentData edContentData) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		
		response.setResult(edContentDataService.patch(edContentData));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdContentData> patch(@RequestBody EdContentData edContentData, @PathVariable Long id) {
		final EdResponse<EdContentData> response = new EdResponse<EdContentData>();
		
		edContentData.setId(id);
		response.setResult(edContentDataService.patch(edContentData));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}
}