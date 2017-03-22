package com.ednomy.crm.repository.custom;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdUserEntity;

public interface EdUserCustom {

	public List<EdUserEntity> list(Map<String, String> queryMap);

	public long count(Map<String, String> queryMap);

	public EdUserEntity lastItem();

}
