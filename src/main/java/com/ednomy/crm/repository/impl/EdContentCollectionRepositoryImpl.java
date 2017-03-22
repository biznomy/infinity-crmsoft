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

import com.ednomy.crm.entity.EdContentCollectionEntity;
import com.ednomy.crm.repository.custom.EdContentCollectionCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdContentCollectionRepositoryImpl implements
		EdContentCollectionCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdContentCollectionEntity> list(Map<String, String> queryMap) {

		List<EdContentCollectionEntity> results = jpaRepositoryUtil
				.listWithoutFetch(queryMap, EdContentCollectionEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdContentCollectionEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdContentCollectionEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap,
				EdContentCollectionEntity.class);
	}

	@Override
	public EdContentCollectionEntity lastItem() {
		TypedQuery<EdContentCollectionEntity> query = entityManager
				.createQuery(
						"select p from EdContentCollectionEntity p ORDER BY id DESC",
						EdContentCollectionEntity.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

}