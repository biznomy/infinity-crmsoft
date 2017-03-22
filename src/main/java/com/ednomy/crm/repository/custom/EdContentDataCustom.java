package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdContentDataEntity;

public interface EdContentDataCustom {

	public List<EdContentDataEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

	public EdContentDataEntity lastItem();
	
	public List<EdContentDataEntity> sqlQuery(String sql);
	
	public String sqlQueryCount(String sql);

}
