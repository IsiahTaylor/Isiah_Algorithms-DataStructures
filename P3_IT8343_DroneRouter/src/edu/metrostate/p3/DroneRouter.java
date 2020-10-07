package edu.metrostate.p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DroneRouter implements edu.metrostate.p3.Router {
	private ArrayList<vertex> vert = new ArrayList<vertex>();
	private ArrayList<String> letters = new ArrayList<String>();
	private int graph[][];
	private ArrayList<String> routes[];
	private String src;
	private int dist[];

	@Override
	public void loadRoutes(String routesFilePath, String source) {
		src = source;
		String workingDirectory = System.getProperty("user.dir");

		try {
			File file = new File(workingDirectory, routesFilePath);
			// File file = new File(routesFilePath);
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String x = myReader.next();
				String y = myReader.next();
				int dis = myReader.nextInt();
				addToLists(x, y, dis);

			}
			myReader.close();
			makeMap();
			dijkstra(src);
		} catch (FileNotFoundException e) {
			System.out.println("file not found.");
			// e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	private void makeMap() {
		graph = new int[letters.size()][letters.size()];
		routes = new ArrayList[letters.size()];
		for (vertex i : vert) {
			graph[letters.indexOf(i.getX())][letters.indexOf(i.getY())] = i.getDis();
			graph[letters.indexOf(i.getY())][letters.indexOf(i.getX())] = i.getDis();
		}

	}

	private void addToLists(String x, String y, int dis) {
		vert.add(new vertex(x, y, dis));
		if (!letters.contains(x)) {
			letters.add(x);
		}
		if (!letters.contains(y)) {
			letters.add(y);
		}
	}

	@Override
	public String[] getRoute(String destination) {
		try {
			routes[letters.indexOf(destination)].add(destination);
			String[] output = new String[routes[letters.indexOf(destination)].size()];
			for (int i = 0; i < routes[letters.indexOf(destination)].size(); i++) {
				output[i] = routes[letters.indexOf(destination)].get(i);
			}
			return output;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("no letter found");
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public int getPathCost(String destination) {
		try {
			int output;
			output = dist[letters.indexOf(destination)];
			return output;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("letter not found.");
			throw new IllegalArgumentException(e);
		} catch (NullPointerException n) {
			System.out.println("Null input");
			throw new NullPointerException();
		}
	}

	private void dijkstra(String src) {
		dist = new int[letters.size()];

		Boolean sptSet[] = new Boolean[letters.size()];

		for (int i = 0; i < letters.size(); i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
			routes[i] = new ArrayList<String>();
		}
		dist[letters.indexOf(src)] = 0;

		for (int count = 0; count < letters.size() - 1; count++) {

			int u = minDistance(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < letters.size(); v++) {
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
					dist[v] = dist[u] + graph[u][v];
					routes[v].addAll(routes[u]);
					routes[v].add(letters.get(u));
				}
			}
		}
	}

	private int minDistance(int dist[], Boolean sptSet[]) {
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < letters.size(); v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	private void printSolution(int dist[], int n) {
		System.out.println(letters);
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < letters.size(); i++)
			System.out.println(letters.get(i) + " tt " + dist[i]);
	}

	private class vertex {
		String x, y;
		int dis;

		public vertex(String x, String y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		public String getX() {
			return x;
		}

		public String getY() {
			return y;
		}

		public int getDis() {
			return dis;
		}
	}

	@Override
	public String toString() {
		return ("Letters: " + letters.toString());
	}
}
