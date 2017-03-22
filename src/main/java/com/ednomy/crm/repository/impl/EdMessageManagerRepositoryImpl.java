package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdMessageManagerEntity;
import com.ednomy.crm.repository.custom.EdMessageManagerCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdMessageManagerRepositoryImpl implements EdMessageManagerCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdMessageManagerEntity> list(Map<String, String> queryMap) {

		List<EdMessageManagerEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdMessageManagerEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdMessageManagerEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdMessageManagerEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdMessageManagerEntity.class);
	}

}