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
import com.ednomy.crm.model.EdPoint;
import com.ednomy.crm.service.EdPointService;
import com.ednomy.crm.util.EdResponse;

@Controller
@RequestMapping(value = "/myedpoint")
public class EdPointController {

	@Autowired
	EdPointService edPointService;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EdPointController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> delete(@PathVariable Long id) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		edPointService.delete(id);
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> get(@PathVariable Long id) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		response.setResult(edPointService.get(id));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> list(@RequestParam Map<String, String> queryMap) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		response.setCount(edPointService.count(queryMap));
		response.setResults(edPointService.list(queryMap));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> save(@RequestBody EdPoint edPoint) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		response.setResult(edPointService.save(edPoint));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> update(@RequestBody EdPoint edPoint, @PathVariable Long id) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		
		edPoint.setId(id);
		response.setResult(edPointService.save(edPoint));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EdResponse<EdPoint> patch(@RequestBody EdPoint edPoint, @PathVariable Long id) {
		final EdResponse<EdPoint> response = new EdResponse<EdPoint>();
		
		edPoint.setId(id);
		response.setResult(edPointService.patch(edPoint));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);

		return response;
	}
}