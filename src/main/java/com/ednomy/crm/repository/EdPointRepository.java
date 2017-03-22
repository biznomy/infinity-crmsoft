package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdPointEntity;
import com.ednomy.crm.repository.custom.EdPointCustom;

public interface EdPointRepository extends JpaRepository<EdPointEntity, Long>,
		JpaSpecificationExecutor<EdPointEntity>, EdPointCustom {	
}
