package com.ednomy.crm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdCompanyEntity;
import com.ednomy.crm.repository.custom.EdCompanyCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdCompanyRepositoryImpl implements EdCompanyCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public List<EdCompanyEntity> list(Map<String, String> queryMap) {

		List<EdCompanyEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdCompanyEntity.class);

		if (jpaRepositoryUtil.hasFetchCondition(queryMap)) {
			List<Long> idList = new ArrayList<Long>();
			Iterator<EdCompanyEntity> itr = results.iterator();
			while (itr.hasNext()) {
				idList.add(itr.next().getId());
			}
			results = jpaRepositoryUtil.listWithFetch(queryMap, idList, "id",
					EdCompanyEntity.class);
		}

		return results;
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdCompanyEntity.class);
	}

}