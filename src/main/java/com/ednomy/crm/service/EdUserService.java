package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.entity.EdUserEntity;
import com.ednomy.crm.model.EdUser;

public interface EdUserService {

	public EdUser save(EdUser edUser);

	public EdUser signup(EdUser edUser);

	public long count(Map<String, String> queryMap);

	public EdUser get(Long id);

	public void delete(Long id);

	public List<EdUser> list(Map<String, String> queryMap);

	public EdUser lastItem();

	public EdUser patch(EdUser edUser);

	public EdUser findUser(String email, String password);
	
	public EdUser findUser(Long id, String password);
	
	public EdUser findUser(Long id, String password, Long clientId);

	public EdUserEntity findUser(Long id, String password, Long clientid, String newPassword);

}
