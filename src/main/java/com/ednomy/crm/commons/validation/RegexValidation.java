package com.ednomy.crm.commons.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidation {

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Validate hex with regular expression
	 * 
	 * @param emailString hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateEmail(final String emailString) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailString);
		return matcher.matches();
	}
}
