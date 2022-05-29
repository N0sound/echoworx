package rule;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class RuleParserTest {

	private final RuleParser fixture = new RuleParser();

	@Test
	void shouldReturnReplace() throws IOException {
		String message = FileUtils.readFileToString(new File("src/test/resources/message/message_with_replace.txt"), StandardCharsets.UTF_8);
		assertTrue(fixture.parse(message) instanceof Replace);
	}

	@Test
	void shouldReturnReverse() throws IOException {
		String message = FileUtils.readFileToString(new File("src/test/resources/message/message_with_reverse.txt"), StandardCharsets.UTF_8);
		assertTrue(fixture.parse(message) instanceof Reverse);
	}

	@Test
	void shouldReturnReplaceAndReverse() throws IOException {
		String message = FileUtils.readFileToString(new File("src/test/resources/message/message_with_replace_and_reverse.txt"),
				StandardCharsets.UTF_8);
		assertTrue(fixture.parse(message) instanceof ReplaceAndReverse);
	}

	@Test
	void shouldReturnNull() throws IOException {
		String message = FileUtils.readFileToString(new File("src/test/resources/message/message_with_no_transformation.txt"),
				StandardCharsets.UTF_8);
		assertNull(fixture.parse(message));
	}
}
