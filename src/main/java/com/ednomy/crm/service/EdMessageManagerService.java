package com.ednomy.crm.service;

import java.util.List;
import java.util.Map;

import com.ednomy.crm.model.EdMessageManager;
import com.fasterxml.jackson.databind.JsonNode;

public interface EdMessageManagerService {

	EdMessageManager save(EdMessageManager edMessageManager);
	
	EdMessageManager later(EdMessageManager edMessageManager);

	long count(Map<String, String> queryMap);

	EdMessageManager get(Long id);

	void delete(Long id);

	List<EdMessageManager> list(Map<String, String> queryMap);
	
	EdMessageManager patch(EdMessageManager edMessageManager);
	
	EdMessageManager retry(EdMessageManager edMessageManager);

	EdMessageManager emailNow(EdMessageManager edMessageManager);

	EdMessageManager emailLater(EdMessageManager edMessageManager);

	EdMessageManager smsNow(EdMessageManager edMessageManager);

	EdMessageManager smsLater(EdMessageManager edMessageManager);
	
	EdMessageManager template();
	
	JsonNode bulkMessage(Long id);
	
	JsonNode bulkSender(JsonNode jsonNose);

	void sendScheduledEmail(EdMessageManager edMessageManager);

	void sendScheduledSms(EdMessageManager edMessageManager);
	
}
