package com.ednomy.crm.service;

import com.ednomy.crm.commons.commu.EmailMessage;
import com.ednomy.crm.commons.commu.SMSMessage;


public interface TypeMessageService {

	public void sendMail(EmailMessage emailMessage);
	
	public void sendSms(SMSMessage smsMessage);	
	
	
}
