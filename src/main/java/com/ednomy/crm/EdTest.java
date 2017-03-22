package com.ednomy.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EdTest {
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	

	public void test(){
		
		System.err.println(javaMailSenderImpl.getHost());
		
	}
	
}
