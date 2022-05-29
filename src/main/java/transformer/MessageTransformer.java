package transformer;

import rule.Rule;

public class MessageTransformer {

	private static final String CARRIAGE_RETURN = "\r\n";

	public String transform(String message, Rule rule) {
		if (rule == null) {
			return message;
		}

		// Assuming messages were well-formed
		String[] lines = message.split(CARRIAGE_RETURN);
		StringBuffer sb = new StringBuffer();
		int len = lines.length;
		for (int i = 0; i < len && i < 4; i++) {
			sb.append(lines[i]);
			if (i < len - 1) {
				sb.append(CARRIAGE_RETURN);
			}
		}
		for (int i = 4; i < len; i++) {
			sb.append(rule.apply(lines[i]));
			if (i < len - 1) {
				sb.append(CARRIAGE_RETURN);
			}
		}
		return sb.toString();
	}
}
