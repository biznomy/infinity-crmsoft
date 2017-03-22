package com.ednomy.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ednomy.crm.entity.EdUserEntity;
import com.ednomy.crm.repository.custom.EdUserCustom;

public interface EdUserRepository extends JpaRepository<EdUserEntity, Long>,
		JpaSpecificationExecutor<EdUserEntity>, EdUserCustom {

	@Query("SELECT user FROM EdUserEntity user WHERE email=?1 AND password=?2")
	EdUserEntity findUserId(String email, String password);
	
	@Query("SELECT user FROM EdUserEntity user WHERE id=?1 AND password=?2")
	EdUserEntity findUserId(Long id, String password);
	
	@Query("SELECT user FROM EdUserEntity user WHERE id=?1 AND password=?2 AND user.clientUser.id=?3")
	EdUserEntity findUserId(Long id, String password, Long clientid);
}
