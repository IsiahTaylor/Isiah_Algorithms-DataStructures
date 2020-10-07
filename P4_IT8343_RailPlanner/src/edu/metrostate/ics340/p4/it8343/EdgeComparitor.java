package edu.metrostate.ics340.p4.it8343;

import java.util.Comparator;


public class EdgeComparitor implements Comparator<Edge>{
	@Override
	public int compare(Edge e1,Edge e2) {
		return e1.getVal() - e2.getVal();
	}
}
