package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdContentDataEntity;
import com.ednomy.crm.repository.custom.EdContentDataCustom;

public interface EdContentDataRepository extends 
		JpaRepository<EdContentDataEntity, Long>,
		JpaSpecificationExecutor<EdContentDataEntity>, EdContentDataCustom{
}
