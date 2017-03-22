package com.ednomy.crm.util;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class JsonUtility {

	private static final Logger LOGGER = Logger.getLogger(JsonUtility.class);

	public boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory()
					.createJsonParser(json);
			while (parser.nextToken() != null) {
				LOGGER.isTraceEnabled();
			}
			valid = true;
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
		}

		return valid;
	}

	public boolean isInValidJSON(final String json) {
		boolean valid = true;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory()
					.createJsonParser(json);
			while (parser.nextToken() != null) {
				LOGGER.isTraceEnabled();
			}
			valid = false;
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
		}

		return valid;
	}

}
