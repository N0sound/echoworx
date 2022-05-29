package rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReplaceAndReverseTest {

	private final ReplaceAndReverse fixture = new ReplaceAndReverse(new Replace(), new Reverse());

	@Test
	void isValid() {
		assertFalse(fixture.isValid("asdfsad asdfasd ads fdas ads fa dsadsf 11213 asdf"));
		assertTrue(fixture.isValid("asdfsad a123124141414124124124 ads fdas ads fa dsadsf 11213 asdf"));
	}
	
	@Test
	void shouldApply() {
		assertEquals("uu yy ee",fixture.apply("$$ ^^ &&"));
	}
}
