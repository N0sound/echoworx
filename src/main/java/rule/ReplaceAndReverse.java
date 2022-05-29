package rule;

import java.util.regex.Pattern;

class ReplaceAndReverse implements Rule {

	private static final Pattern CONSECUTIVE_NUMBERS_PATTERN = Pattern.compile(".*\\d{10,}.*");

	private final Rule replace;
	private final Rule reverse;
	
	ReplaceAndReverse(Rule replace, Rule reverse) {
		this.replace = replace;
		this.reverse = reverse;
	}
	
	@Override
	public boolean isValid(String messsage) {
		return CONSECUTIVE_NUMBERS_PATTERN.matcher(messsage).matches();
	}

	@Override
	public String apply(String message) {
		return reverse.apply(replace.apply(message));
	}
}
