package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdContentCollectionEntity;
import com.ednomy.crm.repository.custom.EdContentCollectionCustom;

public interface EdContentCollectionRepository extends
		JpaRepository<EdContentCollectionEntity, Long>,
		JpaSpecificationExecutor<EdContentCollectionEntity>,
		EdContentCollectionCustom {

}
