package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdClient;

public interface EdClientService {

	public EdClient save(EdClient edClient);

	public long count(Map<String, String> queryMap);

	public EdClient get(Long id);

	public void delete(Long id);

	public List<EdClient> list(Map<String, String> queryMap);
	
	public EdClient patch(EdClient edClient);
}
