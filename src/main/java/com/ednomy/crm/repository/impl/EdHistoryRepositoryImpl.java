package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdHistoryEntity;
import com.ednomy.crm.repository.custom.EdHistoryCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdHistoryRepositoryImpl implements EdHistoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdHistoryEntity> list(Map<String, String> queryMap) {

		List<EdHistoryEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdHistoryEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdHistoryEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdHistoryEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdHistoryEntity.class);
	}

	@Override
	public EdHistoryEntity lastItem() {
		TypedQuery<EdHistoryEntity> query = entityManager.createQuery(
				"select p from EdHistoryEntity p ORDER BY id DESC",
				EdHistoryEntity.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

}