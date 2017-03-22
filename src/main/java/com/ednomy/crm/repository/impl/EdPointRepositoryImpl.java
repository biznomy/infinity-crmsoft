package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdPointEntity;
import com.ednomy.crm.repository.custom.EdPointCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdPointRepositoryImpl implements EdPointCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdPointEntity> list(Map<String, String> queryMap) {

		List<EdPointEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdPointEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdPointEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdPointEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdPointEntity.class);
	}

}