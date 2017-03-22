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

import com.ednomy.crm.entity.EdUserEntity;
import com.ednomy.crm.repository.custom.EdUserCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdUserRepositoryImpl implements EdUserCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdUserEntity> list(Map<String, String> queryMap) {

		List<EdUserEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdUserEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdUserEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdUserEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdUserEntity.class);
	}

	@Override
	public EdUserEntity lastItem() {
		TypedQuery<EdUserEntity> query = entityManager.createQuery(
				"select p from EdUserEntity p ORDER BY id DESC",
				EdUserEntity.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

}