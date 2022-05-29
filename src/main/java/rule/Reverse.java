package rule;

import java.util.regex.Pattern;

class Reverse implements Rule {

	private static final Pattern SUBJECT_PATTERN = Pattern.compile("^Subject:SECURE(.*)");

	@Override
	public boolean isValid(String message) {
		return SUBJECT_PATTERN.matcher(message).matches();
	}

	@Override
	public String apply(String message) {
		return new StringBuilder(message).reverse().toString();
	}
}
