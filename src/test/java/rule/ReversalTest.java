package rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReversalTest {

	private Reverse fixture = new Reverse();

	@Test
	void isValid() {
		assertFalse(fixture.isValid("Subject:Re: Re: Saying hi"));
		assertTrue(fixture.isValid("Subject:SECURE: Saying hi"));
	}
	
	@Test
	void shouldApply() {
		assertEquals("0b 9 8 7 6 5 4 3 2 1a" , fixture.apply("a1 2 3 4 5 6 7 8 9 b0"));
	}
}
