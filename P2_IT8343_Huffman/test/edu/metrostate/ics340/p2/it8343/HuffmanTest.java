package edu.metrostate.ics340.p2.it8343;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTest {

	@Test
	void test() {
		Huffman h = Huffman.build("C:\\Users\\It448\\Desktop\\Sample Text - Big Iron.txt");
		String testcase = "shoot imma shed a tear";
		assertEquals(testcase, h.decode(h.encode(testcase)));
	}

}
