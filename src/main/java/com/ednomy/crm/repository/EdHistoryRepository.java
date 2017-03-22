package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ednomy.crm.entity.EdHistoryEntity;
import com.ednomy.crm.repository.custom.EdHistoryCustom;

public interface EdHistoryRepository extends JpaRepository<EdHistoryEntity, Long>,
		JpaSpecificationExecutor<EdHistoryEntity>, EdHistoryCustom {

	@Query("SELECT id FROM EdHistoryEntity id WHERE clientId=?1 AND endUserId=?2")
	EdHistoryEntity findUserId(Long clientId, Long endUserId);
	
	@Query("SELECT id FROM EdHistoryEntity id WHERE id=?1 AND logType=?2 AND clientId.id=?3")
	EdHistoryEntity findUserId(Long id, String logType, Long clientid);
}
