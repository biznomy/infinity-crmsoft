package com.ednomy.crm.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.expression.ParseException;

import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException,
			JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat(
				EdnomyConstants.EDNOMY_SERVER_DATE_FORMAT);
		String date = jsonParser.getText();
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (java.text.ParseException e) {
			throw new RuntimeException(e);
		}
	}

}