package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import rule.RuleParser;
import transformer.MessageTransformer;

public class MessageProcessor {

	private final RuleParser ruleParser;
	private final MessageTransformer messageTransformer;
	private final File destDir;

	public MessageProcessor(RuleParser ruleParser, MessageTransformer messageTransformer) {
		this(ruleParser, messageTransformer, null);
	}

	public MessageProcessor(RuleParser ruleParser, MessageTransformer messageTransformer, File destDir) {
		this.ruleParser = ruleParser;
		this.messageTransformer = messageTransformer;
		this.destDir = destDir;
	}

	public void process(String path) throws IOException {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			return;
		}
		// Assuming messages are well-formed
		String message = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		var rule = ruleParser.parse(message);
		String transformedMessage = messageTransformer.transform(message, rule);
		String newFilePath = getNewFilePath(file);
		FileUtils.writeStringToFile(new File(newFilePath), transformedMessage, StandardCharsets.UTF_8);
		System.out.println();
	}

	private String getNewFilePath(File file) {
		String filename = file.getName();
		String destinationDir = destDir == null ? FilenameUtils.getPath(file.getPath()) : destDir.getPath();
		return destinationDir + File.separator + FilenameUtils.getBaseName(filename) + "_processed."
				+ FilenameUtils.getExtension(filename);
	}
}
