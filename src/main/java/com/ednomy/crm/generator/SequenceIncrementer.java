package com.ednomy.crm.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.util.TransactionIdentifierGenerator;

@Component
public class SequenceIncrementer {

	@Autowired
	TransactionIdentifierGenerator transactionIdentifierGenerator;

	public Long getValue() {
		return transactionIdentifierGenerator.generate(null, null).longValue();
	}
}