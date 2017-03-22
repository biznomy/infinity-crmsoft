package com.ednomy.crm.service;

import java.util.Map;
import java.util.Set;

import com.ednomy.crm.model.EdGoogleScrapper;

public interface EdGoogleScrapperService {

	public long count(Map<String, String> queryMap);

	public EdGoogleScrapper get(String id);

	public void delete(String id);

	public Set<EdGoogleScrapper> list(Map<String, String> queryMap);

	public EdGoogleScrapper save(EdGoogleScrapper edGoogleScrapper);

	public EdGoogleScrapper patch(EdGoogleScrapper edGoogleScrapper);

	
}
