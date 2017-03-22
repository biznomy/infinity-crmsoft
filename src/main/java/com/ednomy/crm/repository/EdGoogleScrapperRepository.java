package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ednomy.crm.entity.EdGoogleScrapperEntity;
import com.ednomy.crm.repository.custom.EdGoogleScrapperCustom;

public interface EdGoogleScrapperRepository extends JpaRepository<EdGoogleScrapperEntity, String>,
		JpaSpecificationExecutor<EdGoogleScrapperEntity>, EdGoogleScrapperCustom {	
}
