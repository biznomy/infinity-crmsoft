package com.ednomy.crm.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednomy.crm.entity.EdGoogleScrapperEntity;
import com.ednomy.crm.repository.custom.EdGoogleScrapperCustom;
import com.ednomy.crm.util.JpaRepositoryUtil;

@Repository
public class EdGoogleScrapperRepositoryImpl implements EdGoogleScrapperCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	JpaRepositoryUtil jpaRepositoryUtil;

	@Override
	public Set<EdGoogleScrapperEntity> list(Map<String, String> queryMap) {

		List<EdGoogleScrapperEntity> results = jpaRepositoryUtil.listWithoutFetch(
				queryMap, EdGoogleScrapperEntity.class);
		return new HashSet<EdGoogleScrapperEntity>(results);
	}

	@Override
	public long count(Map<String, String> queryMap) {
		return jpaRepositoryUtil.count(queryMap, EdGoogleScrapperEntity.class);
	}

}