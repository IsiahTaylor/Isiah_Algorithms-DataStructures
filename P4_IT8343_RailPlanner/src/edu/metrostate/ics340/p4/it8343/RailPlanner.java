/**@author Isiah Taylor
 * Metropolitan state University, ICS 340
 * 
 * Project 4,Rail Planner
 * 
 * I have have implemented Kruskal's Algorithm to create a minimum spanning tree when given a pole delimited file.
 * In the method kruskal() is where most of the algorithm lies.
 * 1. I add all of the edges into a priority queue
 * 2. add the smallest edge at the front of the queue*
 * 
 *  * ** in the addSmallest Method i apply the logic of the algorithm to 
 * check that the two nodes are not apart of the same tree
 * 
 * 3. Continue the loop until the Queue is empty
 * 4. Return the tree when done
 */
package edu.metrostate.ics340.p4.it8343;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class RailPlanner {
	private static final String DELIM = "[\\|\n\r\f]";
	private MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();
	// Create a graph that will be the output
	private MutableValueGraph<String, Integer> minGraph = ValueGraphBuilder.undirected().build();

	// Constructor
	private RailPlanner() {
	}

	// Added public to increase visibility
	public static com.google.common.graph.ValueGraph<String, Integer> createPlan(String estimateFilePath) {
		RailPlanner railPlanner = new RailPlanner();
		railPlanner.fileInput(estimateFilePath);
		return railPlanner.kruskal();
	}

	// Implements Kruskal's algorithm
	private ValueGraph<String, Integer> kruskal() {
		PriorityQueue<Edge> edgeQueue = sortEdges(graph.edges());
		// While edges can be added, keep adding the smallest edge that can be added

		// if no edges are in the graph then stop
		while (!edgeQueue.isEmpty()) {
			Edge frontEdgeInQ = edgeQueue.remove();
			if (canAdd(frontEdgeInQ)) {
				addSmallestEdge(frontEdgeInQ);
			}
		}
		return minGraph;
	}

	// Adds the smallest edge to the graph as long as it does not create a cycle
	private void addSmallestEdge(Edge frontEdgeInQ) {
		minGraph.addNode(frontEdgeInQ.getNode1());
		minGraph.addNode(frontEdgeInQ.getNode2());
		minGraph.putEdgeValue(frontEdgeInQ.getNode2(), frontEdgeInQ.getNode1(), frontEdgeInQ.getVal());

	}

	// Checks to see if the added node will create a cycle
	private boolean canAdd(Edge frontEdgeInQ) {
		// if the node is not in the graph then it is okay to add
		if (!minGraph.nodes().contains(frontEdgeInQ.getNode1())
				|| !minGraph.nodes().contains(frontEdgeInQ.getNode2())) {
			return true;
		}
		return !allNodesConnectingTo(frontEdgeInQ.getNode1()).contains(frontEdgeInQ.getNode2());
	}

	// Adds all of the edges to a priority queue
	private PriorityQueue<Edge> sortEdges(Set<EndpointPair<String>> set) {
		PriorityQueue<Edge> output = new PriorityQueue<Edge>(new EdgeComparitor());
		for (var i : set) {
			output.add(new Edge(i.nodeU(), i.nodeV(), graph.edgeValue(i.nodeU(), i.nodeV()).get()));
		}
		return output;

	}

	// This adds all the nodes that can be reached from the inputed node
	private Set<String> allNodesConnectingTo(String input) {
		Set<String> set = new HashSet<String>();
		Set<String> setAux = new HashSet<String>();
		set.addAll(minGraph.adjacentNodes(input));
		// Two different sets to make sure that I am not adding to a set that i am also
		// trying to reiterate over at the same time i am adding nodes
		// ConcurrentModificationException work around
		boolean moreAdded = true;
		//If anything was added, go through all the nodes again
		while (moreAdded) {
			moreAdded = false;
			for (var adjNode : set) {
				if (setAux.addAll(minGraph.adjacentNodes(adjNode))) {
					moreAdded = true;
				}
			}
			set.addAll(setAux);
		}
		return set;
	}

	// Takes a file as input and adds it to the graph
	private void fileInput(String filePath) {
		// Import the file and add all the edges and nodes to the graph
		String node1 = "";
		String node2 = "";
		int value = 0;
		String[] array;
		File file = new File(filePath);

		try (Scanner scanner = new Scanner(file)) {
			// While there is a line to be read from the file make the line into tokens then
			// add them to the graph
			while (scanner.hasNextLine()) {
				// Recommended delimiters
				array = scanner.nextLine().split(DELIM, 3);
				node1 = array[0];
				graph.addNode(node1);

				node2 = array[1];
				graph.addNode(node2);

				value = Integer.parseInt(array[2]);
				graph.putEdgeValue(node1, node2, value);

			}
			scanner.close();
		} catch (IOException e) {
			// If file is not found
			System.out.println("file not found.");
			throw new IllegalArgumentException(e);
		}
	}

}