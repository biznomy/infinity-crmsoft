package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdCompany;

public interface EdCompanyService {

	public EdCompany save(EdCompany edCompany);

	public long count(Map<String, String> queryMap);

	public EdCompany get(Long id);

	public void delete(Long id);

	public List<EdCompany> list(Map<String, String> queryMap);
	
	public EdCompany patch(EdCompany edCompany);
}
