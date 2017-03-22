package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdContentDataEntity;
import com.ednomy.crm.repository.custom.EdContentDataCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdContentDataRepositoryImpl implements EdContentDataCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdContentDataEntity> list(Map<String, String> queryMap) {

		List<EdContentDataEntity> results = jpaRepositoryUtil
				.listWithoutFetch(queryMap, EdContentDataEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdContentDataEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdContentDataEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdContentDataEntity.class);
	}

	@Override
	public EdContentDataEntity lastItem() {
		TypedQuery<EdContentDataEntity> query = entityManager.createQuery(
				"select p from EdContentDataEntity p ORDER BY id DESC",
				EdContentDataEntity.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EdContentDataEntity> sqlQuery(String sql){
		Query query = entityManager.createNativeQuery(sql, EdContentDataEntity.class);
		List<EdContentDataEntity> edContentDataEntities = query.getResultList();
		return edContentDataEntities;		
	}
	
	@Override
	public String sqlQueryCount(String sql){
		Query query = entityManager.createNativeQuery(sql);
		return  String.valueOf(query.getSingleResult());
	}

}