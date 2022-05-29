package application;

import java.io.IOException;

import rule.RuleParser;
import transformer.MessageTransformer;

public class Application {
	
	private static final MessageProcessor PROCESSOR = new MessageProcessor(new RuleParser(), new MessageTransformer());

	public static void main(String[] args) throws IOException {
		for (String arg : args) {
			PROCESSOR.process(arg);
		}
	}
}
