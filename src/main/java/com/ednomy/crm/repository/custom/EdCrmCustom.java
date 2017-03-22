package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdCrmEntity;


public interface EdCrmCustom {

	public List<EdCrmEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

}
