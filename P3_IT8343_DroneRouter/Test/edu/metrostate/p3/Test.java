package edu.metrostate.p3;

import static org.junit.jupiter.api.Assertions.*;

//Null map
//Small 2 node Map
//Teacher test nodes
//Book map 
class Test {
	@org.junit.jupiter.api.Test
	void test() {
		DroneRouter d = new DroneRouter();
		d.loadRoutes("p3 Routes.txt", "A");
		System.out.println(d.toString());
		for (String i : d.getRoute("G")) {
			System.out.print(i);
		}
		assertEquals(7, d.getPathCost("G"));
	}

	@org.junit.jupiter.api.Test
	void testNoRoute() {
		DroneRouter d = new DroneRouter();
		d.loadRoutes("LargeNode", "A");
		assertThrows(IllegalArgumentException.class, () -> d.getRoute("J"));
	}

	@org.junit.jupiter.api.Test
	void test2nodes() {
		DroneRouter d = new DroneRouter();
		d.loadRoutes("2nodes", "A");
		System.out.println(d.toString());
	}

	@org.junit.jupiter.api.Test
	void testLarge() {
		DroneRouter d = new DroneRouter();
		d.loadRoutes("largeNode", "A");
		System.out.println(d.toString());
		System.out.println("A:");
		for (String i : d.getRoute("A")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("B:");
		for (String i : d.getRoute("B")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("C:");
		for (String i : d.getRoute("C")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("D:");
		for (String i : d.getRoute("D")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("E:");
		for (String i : d.getRoute("E")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("F:");
		for (String i : d.getRoute("F")) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("G:");
		for (String i : d.getRoute("G")) {
			System.out.print(i);
		}

	}

	@org.junit.jupiter.api.Test
	void testBadFile() {
		DroneRouter d = new DroneRouter();
		assertThrows(IllegalArgumentException.class, () -> d.loadRoutes("2node", "A"));
	}

	void testNoCost() {
		DroneRouter d = new DroneRouter();
		d.loadRoutes("LargeNode", "A");
		assertThrows(IllegalArgumentException.class, () -> d.getPathCost("T"));
		assertThrows(NullPointerException.class, () -> d.getPathCost(null));
	}
}
