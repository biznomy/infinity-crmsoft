package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdClientEntity;
import com.ednomy.crm.repository.custom.EdClientCustom;

public interface EdClientRepository extends JpaRepository<EdClientEntity, Long>,
		JpaSpecificationExecutor<EdClientEntity>, EdClientCustom {
	
}
