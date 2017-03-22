package com.ednomy.crm.repository.custom;

import java.util.Map;
import java.util.Set;

import com.ednomy.crm.entity.EdGoogleScrapperEntity;


public interface EdGoogleScrapperCustom {

	public Set<EdGoogleScrapperEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

}
