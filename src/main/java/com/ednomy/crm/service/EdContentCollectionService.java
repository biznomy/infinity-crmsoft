package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdContentCollection;

public interface EdContentCollectionService {

	public EdContentCollection save(EdContentCollection edContentCollection);

	public long count(Map<String, String> queryMap);

	public EdContentCollection get(Long id);

	public void delete(Long id);

	public List<EdContentCollection> list(Map<String, String> queryMap);

	public EdContentCollection patch(EdContentCollection edContentCollection);
}
