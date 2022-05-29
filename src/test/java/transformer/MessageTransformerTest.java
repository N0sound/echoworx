package transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rule.Rule;

class MessageTransformerTest {

	private static final String HEADERS = "TO:\r\nFROM:asdfsa\r\nSUBJECT:asdfsa\r\nBODY:\r\n";
	private static final TestRule TEST_RULE = new TestRule();

	private final MessageTransformer fixture = new MessageTransformer();

	@Test
	void shouldReturnOriginalMessageIfNoRule() {
		String message = "abc";
		assertEquals(message, fixture.transform(message, null));
	}
	
	@Test
	void shouldReturnOriginalMessageIfMissingFields() {
		String message = "abc";
		assertEquals(message, fixture.transform(message, TEST_RULE));
	}

	@Test
	void shouldTransform() {
		String body = "$$ ^^ &&\r\n&& $$ ^^\r\n\r\n\r\nabc abac abac $";
		String actual = fixture.transform(HEADERS + body, TEST_RULE);
		String expected = "TO:\r\nFROM:asdfsa\r\nSUBJECT:asdfsa\r\nBODY:\r\nee yy uu\r\nuu ee yy\r\n"
				+ "\r\n\r\nabc abac abac e";
		assertEquals(expected, actual);
	}

	private static class TestRule implements Rule {

		@Override
		public boolean isValid(String messsage) {
			return false;
		}

		@Override
		public String apply(String message) {
			return message.replace("$", "e").replace("^", "y").replace("&", "u");
		}
	}
}
