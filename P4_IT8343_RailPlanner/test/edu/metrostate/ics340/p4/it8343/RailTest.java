package edu.metrostate.ics340.p4.it8343;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.common.graph.ValueGraph;

class RailTest {

	@Test
	void testBookRails() {
		assertEquals(37,getEdgeWeightTotal("/testFiles/bookRails"));
	}

	@Test
	void testEmptyFile() {
		assertEquals(0,getEdgeWeightTotal("/testFiles/empty"));
	}

	@Test
	void testTwoNodes() {
		assertEquals(4,getEdgeWeightTotal("/testFiles/twoNodes"));
	}

	@Test
	void testThreeNodes() {
		assertEquals(3,getEdgeWeightTotal("/testFiles/threeNodes"));
	}

	@Test
	void testSameValueEdge() {
		assertEquals(5,getEdgeWeightTotal("/testFiles/sameValue"));
	}

	@Test
	void testGivenRoutes() {
		//This has disconnected routes given and the tree will still return them
		assertEquals(368,getEdgeWeightTotal("/testFiles/p4Routes"));
	}

	@Test
	void testLarge() {
		// Original 1919, other Version 1937
		assertEquals(1937, getEdgeWeightTotal("/testFiles/p4_scenario1.csv"));
	}

	private int getEdgeWeightTotal(String testScenario) {
		ValueGraph<String, Integer> mst = RailPlanner.createPlan(this.getClass().getResource(testScenario).getPath());
		int edgeTotal = 0;
		for (var edge : mst.edges()) {
			edgeTotal += mst.edgeValue(edge).get();
		}
		return edgeTotal;
	}
}
