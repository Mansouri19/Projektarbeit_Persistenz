package net.codejava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookTest {

	@Test
	@DisplayName("OK!")
	@Disabled
	void test() {
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> Integer.parseInt("Hallo"));
		
		String expected = "For input";
		assertAll(() -> {
			assertEquals(expected, ex.getMessage());
		}, () -> {
			assertNotNull(ex.getCause());
		});
	}

}
