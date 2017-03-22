package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdCrmEntity;
import com.ednomy.crm.repository.custom.EdCrmCustom;

public interface EdCrmRepository extends JpaRepository<EdCrmEntity, Long>,
		JpaSpecificationExecutor<EdCrmEntity>, EdCrmCustom {	
}
