package rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Replace implements Rule {

	private static final Pattern TO_PATTERN = Pattern.compile("^To:(.*)");
	private static final Pattern RECIPIENT_PATTERN = Pattern.compile("^.*@domain.com");

	@Override
	public boolean isValid(String message) {
		Matcher matcher = TO_PATTERN.matcher(message);
		if (!matcher.matches()) {
			return false;
		}
		String[] recipients = matcher.group(1).split(",");
		for (String recipient : recipients) {
			if (RECIPIENT_PATTERN.matcher(recipient).matches()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String apply(String message) {
		return message.replace("$", "e").replace("^", "y").replace("&", "u");
	}
}
