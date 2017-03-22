package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdHistoryEntity;

public interface EdHistoryCustom {

	public List<EdHistoryEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

	public EdHistoryEntity lastItem();

}
