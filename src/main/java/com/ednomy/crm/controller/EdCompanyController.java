/**
 *
 */
package com.ednomy.crm.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.model.EdCompany;
import com.ednomy.crm.service.EdCompanyService;
import com.ednomy.crm.util.EdResponse;

@Controller
@RequestMapping(value = "/company")
public class EdCompanyController {

	@Autowired
	EdCompanyService edCompanyService;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EdCompanyController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> delete(@PathVariable Long id) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		edCompanyService.delete(id);
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> get(@PathVariable Long id) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		response.setResult(edCompanyService.get(id));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> list(@RequestParam Map<String, String> queryMap) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		response.setCount(edCompanyService.count(queryMap));
		response.setResults(edCompanyService.list(queryMap));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> save(@RequestBody EdCompany edCompany) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		response.setResult(edCompanyService.save(edCompany));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> update(@RequestBody EdCompany edCompany, @PathVariable Long id) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		
		edCompany.setId(id);
		response.setResult(edCompanyService.save(edCompany));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdCompany> patch(@RequestBody EdCompany edCompany, @PathVariable Long id) {
		final EdResponse<EdCompany> response = new EdResponse<EdCompany>();
		
		edCompany.setId(id);
		response.setResult(edCompanyService.patch(edCompany));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}
}