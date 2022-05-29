package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import rule.RuleParser;
import transformer.MessageTransformer;

class MessageProcessorTest {

	private static final MessageTransformer MESSAGE_TRANSFORMER = new MessageTransformer();
	private static final RuleParser RULE_PARSER = new RuleParser();

	@TempDir
	private File TempDir;

	@Test
	void shouldProcessWithNoTransformation() throws IOException {
		MessageProcessor fixture = new MessageProcessor(RULE_PARSER, MESSAGE_TRANSFORMER, TempDir);
		fixture.process("src/test/resources/message/message_with_no_transformation.txt");
		File newFile = new File(TempDir, "message_with_no_transformation_processed.txt");
		assertTrue(newFile.exists());
		String expected = FileUtils.readFileToString(
				new File("src/test/resources/message/message_with_no_transformation.txt"),
				StandardCharsets.UTF_8);
		String actual = FileUtils.readFileToString(newFile, StandardCharsets.UTF_8);
		assertEquals(expected, actual);
	}

	@Test
	void shouldProcessReverse() throws IOException {
		MessageProcessor fixture = new MessageProcessor(RULE_PARSER, MESSAGE_TRANSFORMER, TempDir);
		fixture.process("src/test/resources/message/message_with_reverse.txt");
		File newFile = new File(TempDir, "message_with_reverse_processed.txt");
		assertTrue(newFile.exists());
		String expected = FileUtils.readFileToString(
				new File("src/test/resources/message/processed/message_with_reverse_processed.txt"),
				StandardCharsets.UTF_8);
		String actual = FileUtils.readFileToString(newFile, StandardCharsets.UTF_8);
		assertEquals(expected, actual);
	}

	@Test
	void shouldProcessReplace() throws IOException {
		MessageProcessor fixture = new MessageProcessor(RULE_PARSER, MESSAGE_TRANSFORMER, TempDir);
		fixture.process("src/test/resources/message/message_with_replace.txt");
		File newFile = new File(TempDir, "message_with_replace_processed.txt");
		assertTrue(newFile.exists());
		String expected = FileUtils.readFileToString(
				new File("src/test/resources/message/processed/message_with_replace_processed.txt"),
				StandardCharsets.UTF_8);
		String actual = FileUtils.readFileToString(newFile, StandardCharsets.UTF_8);
		assertEquals(expected, actual);
	}

	@Test
	void shouldProcessReplaceAndReverse() throws IOException {
		MessageProcessor fixture = new MessageProcessor(RULE_PARSER, MESSAGE_TRANSFORMER, TempDir);
		fixture.process("src/test/resources/message/message_with_replace_and_reverse.txt");
		File newFile = new File(TempDir, "message_with_replace_and_reverse_processed.txt");
		assertTrue(newFile.exists());
		String expected = FileUtils.readFileToString(
				new File("src/test/resources/message/processed/message_with_replace_and_reverse_processed.txt"),
				StandardCharsets.UTF_8);
		String actual = FileUtils.readFileToString(newFile, StandardCharsets.UTF_8);
		assertEquals(expected, actual);
	}
}
