package rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReplaceTest {

	private Replace fixture = new Replace();

	@Test
	void isValid() {
		assertFalse(fixture.isValid("To:user@domain.net,user2@domain2.org"));
		assertTrue(fixture.isValid("To:user2@domain.net,user@domain.com"));
	}
	
	@Test
	void shouldApply() {
		assertEquals("ee yy uu",fixture.apply("$$ ^^ &&"));
	}
}
