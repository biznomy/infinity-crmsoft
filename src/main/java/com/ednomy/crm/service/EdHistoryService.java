package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdHistory;

public interface EdHistoryService {

	public long count(Map<String, String> queryMap);

	public EdHistory get(Long id);

	public void delete(Long id);

	public List<EdHistory> list(Map<String, String> queryMap);

	public EdHistory save(EdHistory edHistory);

	public EdHistory patch(EdHistory edHistory);

	public EdHistory updateField(String fieldName, Long id, String fieldValue);
	
}
