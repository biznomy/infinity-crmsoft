package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdPointEntity;


public interface EdPointCustom {

	public List<EdPointEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

}
