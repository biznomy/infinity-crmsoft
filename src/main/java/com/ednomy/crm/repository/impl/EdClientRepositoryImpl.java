package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdClientEntity;
import com.ednomy.crm.repository.custom.EdClientCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdClientRepositoryImpl implements EdClientCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdClientEntity> list(Map<String, String> queryMap) {

		List<EdClientEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdClientEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdClientEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdClientEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdClientEntity.class);
	}

}