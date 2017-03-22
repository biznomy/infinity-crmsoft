package com.ednomy.crm.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class TransactionIdentifierGenerator implements IdentifierGenerator {

	private static final String MAX_VALUE = "9999999";

	private static final String TRANSACTION_ID_FORMAT = "yyMMddHHmmssSSS";

	private static AtomicInteger value = new AtomicInteger();

	@Override
	public synchronized BigDecimal generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		StringBuilder builder = new StringBuilder();
		String objectId = builder
				.append(DateFormatUtils.format(Calendar.getInstance(),
						TRANSACTION_ID_FORMAT))
				.append(StringUtils.leftPad(
						String.valueOf(value.incrementAndGet()), 2, '0'))
				.toString();
		if (objectId.endsWith(MAX_VALUE)) {
			value.set(0);
		}
		
		return new BigDecimal(StringUtils.substring(objectId, 0, 14));
	}	
}
