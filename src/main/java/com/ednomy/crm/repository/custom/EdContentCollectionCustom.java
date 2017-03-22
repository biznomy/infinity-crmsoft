package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdContentCollectionEntity;

public interface EdContentCollectionCustom {

	public List<EdContentCollectionEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

	public EdContentCollectionEntity lastItem();

}
