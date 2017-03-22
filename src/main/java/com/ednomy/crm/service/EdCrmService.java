package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdCrm;

public interface EdCrmService {

	public long count(Map<String, String> queryMap);

	public EdCrm get(Long id);

	public void delete(Long id);

	public List<EdCrm> list(Map<String, String> queryMap);

	public EdCrm save(EdCrm edCrm);

	public EdCrm patch(EdCrm edCrm);	
	
}
