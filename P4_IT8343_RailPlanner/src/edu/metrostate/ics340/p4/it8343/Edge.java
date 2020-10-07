package edu.metrostate.ics340.p4.it8343;

public class Edge {
	private String node1;
	private String node2;
	private int val;
	public Edge(String node1, String node2, int val) {
		super();
		this.node1 = node1;
		this.node2 = node2;
		this.val = val;
	}
	public String getNode1() {
		return node1;
	}
	public String getNode2() {
		return node2;
	}
	public int getVal() {
		return val;
	}
	public void setNode1(String node1) {
		this.node1 = node1;
	}
	public void setNode2(String node2) {
		this.node2 = node2;
	}
	public void setVal(int val) {
		this.val = val;
	}
	
	
}
