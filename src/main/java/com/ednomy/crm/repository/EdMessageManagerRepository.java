package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdMessageManagerEntity;
import com.ednomy.crm.repository.custom.EdMessageManagerCustom;

public interface EdMessageManagerRepository extends JpaRepository<EdMessageManagerEntity, Long>,
		JpaSpecificationExecutor<EdMessageManagerEntity>, EdMessageManagerCustom {
	
}
