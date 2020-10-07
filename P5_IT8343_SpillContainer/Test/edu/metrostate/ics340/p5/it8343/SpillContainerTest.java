package edu.metrostate.ics340.p5.it8343;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SpillContainerTest {
	List<String> testList = new ArrayList<String>();
	@Test
	void testFourPoints() {
		testList.add("p1");
		testList.add("p4");
		testList.add("p2");
		testList.add("p3");
		
		assertEquals( testList, list("/TestFiles/FourPoints"));
	}
	
	@Test
	void testThreePoints() {
		testList.add("p1");
		testList.add("p2");
		testList.add("p3");
		
		assertEquals( testList, list("/TestFiles/ThreePoints"));
	}
	
	@Test
	void testFivePoints() {
		testList.add("p1");
		testList.add("p4");
		testList.add("p2");
		testList.add("p3");
		
		assertEquals( testList, list("/TestFiles/FivePoints"));
	}
	
	@Test
	void testThirtyPlus() {
		testList.add("al");
		testList.add("a");
		testList.add("d");
		testList.add("e");
		testList.add("f");
		testList.add("k");
		testList.add("ad");
		testList.add("w");
		testList.add("x");
		
		assertEquals( testList, list("/TestFiles/ThirtyPlusPoints"));
		
	}
		
	@Test
	void testColinearTriangle() {
		testList.add("a");
		testList.add("c");
		testList.add("d");
		
		assertEquals( testList, list("/TestFiles/ColinearTriangle"));
	}
	
	@Test
	void testLineOfPoints() {
		testList.add("a");
		testList.add("c");
		
		assertEquals( testList, list("/TestFiles/LineOfPoints"));
	}
	
	@Test
	void twoPoints() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> list("/TestFiles/TwoPoints"));
	}
	
	@Test
	void twoHundred() {
		System.out.println( list("/TestFiles/twoHundred"));
	}
	public List<String> list(String fileinput) {
		return SpillContainer.getBoundary(this.getClass().getResource(fileinput).getPath());
	}

}
