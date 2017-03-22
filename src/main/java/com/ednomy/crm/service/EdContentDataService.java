package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdContentData;

public interface EdContentDataService {

	public EdContentData save(EdContentData edContentData);

	public long count(Map<String, String> queryMap);

	public EdContentData get(Long id);

	public void delete(Long id);

	public List<EdContentData> list(Map<String, String> queryMap);

	public EdContentData patch(EdContentData edContentData);

	public EdContentData updateField(String fieldName, Long id, String fieldValue);

	public List<EdContentData> random(Map<String, String> queryMap);

	public List<EdContentData> getTest(Long id);
	
	public String updateCRMStatus(List<Long>  campainId, String sendMessage, String mediaLink, String updatedBy, String type, Long endUser);
}
