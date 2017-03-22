package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdCompanyEntity;
import com.ednomy.crm.repository.custom.EdCompanyCustom;

public interface EdCompanyRepository extends JpaRepository<EdCompanyEntity, Long>,
		JpaSpecificationExecutor<EdCompanyEntity>, EdCompanyCustom{
}
