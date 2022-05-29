package rule;

public class RuleParser {

	private static final String CARRIAGE_RETURN = "\r\n";
	private static final Rule REPLACE = new Replace();
	private static final Rule REVERSE = new Reverse();
	private static final Rule REPLACE_AND_REVERSE = new ReplaceAndReverse(REPLACE, REVERSE);

	// Assuming that the rules are mutually exclusive and message is well-formed
	public Rule parse(String message) {
		String[] lines = message.split(CARRIAGE_RETURN);
		if (REPLACE.isValid(lines[0])) {
			return REPLACE;
		}
		if (REVERSE.isValid(lines[2])) {
			return REVERSE;
		}

		if (REPLACE_AND_REVERSE.isValid(extractBody(lines))) {
			return REPLACE_AND_REVERSE;
		}

		return null;
	}

	private static String extractBody(String[] messages) {
		StringBuilder sb = new StringBuilder();
		for (int i = 4; i < messages.length; i++) {
			sb.append(messages[i]);
		}
		return sb.toString();
	}
}
