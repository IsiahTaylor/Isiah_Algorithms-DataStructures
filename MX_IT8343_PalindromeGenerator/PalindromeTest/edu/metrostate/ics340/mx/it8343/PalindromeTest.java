package edu.metrostate.ics340.mx.it8343;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics340.mx.it8343.PalindromeGenerator;

class PalindromeTest {

	@Test
	void test() {
		assertThrows(IllegalArgumentException.class, () -> PalindromeGenerator.generate(0));
	}
	@Test
	void testBatch1() {
		assertEquals(1, PalindromeGenerator.generate(1));
		assertEquals(11, PalindromeGenerator.generate(10));
		assertEquals(11, PalindromeGenerator.generate(11));
	}
	@Test
	void testBatch2() {
		assertEquals(33, PalindromeGenerator.generate(12));
		assertEquals(101, PalindromeGenerator.generate(100));
		assertEquals(-1, PalindromeGenerator.generate(196));
	}
}
