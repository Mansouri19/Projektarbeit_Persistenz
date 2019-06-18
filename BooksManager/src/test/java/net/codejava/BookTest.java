package net.codejava;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BookTest {
	
	@Test
	void testBookUpdate() {
		Book existBook = new Book();
		existBook.setBookId(3);
		Integer actual = existBook.getBookId();
		
		existBook.setTitle("Java SE 8 Programmer II");
		existBook.setAuthor("Bruce Eckel");
		existBook.setPrice(50);
			
		Integer expected = 3;
		assertEquals(expected, actual);
	}
	
	
/*  Beispiele
 
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

//	##################################################################

	@ParameterizedTest
	@ValueSource(ints = { 3, 6, 9 })
	void triple1(int i) {
		assertTrue(i % 3 == 0);
	}

//	##################################################################
	
	@Nested
	class Inner {

		@TestFactory
		@Tag(value = "important")
		Stream<DynamicTest> triple2() {
			return IntStream.of(3, 6, 9).mapToObj(i -> DynamicTest.dynamicTest(i + "?", () -> {
				assertTrue(i % 3 == 0);
			}));

		}
	}

//	##################################################################

	@ParameterizedTest(name = "#{index} |{0}| =? {1}")
	@CsvSource({ "four,4", "seven,5" })
	void wordLength(String word, int length) {
		assertEquals(length, word.length());
	}
*/
}
