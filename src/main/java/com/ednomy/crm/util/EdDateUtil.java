package com.ednomy.crm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ednomy.crm.commons.constants.EdnomyConstants;

@Component
public class EdDateUtil {

	public Date getDatefromString(String date) {
		DateFormat formatter = new SimpleDateFormat(
				EdnomyConstants.EDNOMY_DB_DATE_FORMAT,
				EdnomyConstants.EDNOMY_LOCALE);
		try {
			return (formatter.parse(date));
		} catch (Exception e) {

		}
		return null;

	}

}
