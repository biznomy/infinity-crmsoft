package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdPoint;

public interface EdPointService {

	public EdPoint save(EdPoint edPoint);

	public long count(Map<String, String> queryMap);

	public EdPoint get(Long id);

	public void delete(Long id);

	public List<EdPoint> list(Map<String, String> queryMap);
	
	public EdPoint patch(EdPoint edPoint);
}
