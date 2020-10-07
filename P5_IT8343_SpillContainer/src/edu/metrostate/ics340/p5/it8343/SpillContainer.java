package edu.metrostate.ics340.p5.it8343;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SpillContainer {
	private static final String DELIM = "[\\|\n\r\f]";

	public static List<String> getBoundary(String sectorFile) {
		// Return the list//Grab the convex hull//File input
		SpillContainer spillContainer = new SpillContainer();
		List<Point> fileOutput = spillContainer.fileToPoints(sectorFile);
		return spillContainer.jarvisMarch(fileOutput);
	}

	private List<String> jarvisMarch(List<Point> pointsList) {
		// ConvexHullList
		List<Point> hullList = new ArrayList<Point>();
		// The first anchor will be the left most point
		Point startingPoint = addStartingPoint(pointsList);
		hullList.add(startingPoint);
		// p0 - the anchor point
		// p1 - is the point that is considered the left most for the time
		// p2 - new point being compared
		Point p0 = startingPoint;
		Point p1;

		boolean isComplete = false;
		while (!isComplete) {
			// Clear the considered Left most
			p1 = null;
			// Loop through all the points and find any point that would have the line make
			// a left turn and set it to that point
			// All operations skip over the anchor point
			for (var p2 : pointsList) {
				if (p1 == null && !p2.equals(p0)) {
					p1 = p2;
				} else if (!p2.equals(p0)) {
					double crossProductValue = computeTurn(p0, p1, p2);
					// If collinear , find the one furthest to the anchor point
					if (crossProductValue == 0) {
						p1 = (distance(p0, p1) > distance(p0, p2)) ? p1 : p2;
					} else if (crossProductValue > 0) {
						p1 = p2;
					}
				}
			}
			// Check to see if the point that is found is the start, meaning that we are
			// done with the loop
			if (p1.equals(startingPoint)||hullList.contains(p1)) {
				isComplete = true;
			} else {
				// Add the left most point to the hull and set it to the next anchor point
				hullList.add(p1);
				p0 = p1;
			}

		}
		// Convert the points to a string then add them to a list
		List<String> returnList = listPointToString(hullList);
		return returnList;
	}

	private double distance(Point p0, Point p1) {
		return Math.sqrt(Math.hypot(p1.getX() - p0.getX(), p1.getY() - p0.getY()));
	}

	private List<String> listPointToString(List<Point> hullList) {
		List<String> returnList = new ArrayList<String>();
		for (Point point : hullList) {
			returnList.add(point.getName());
		}
		return returnList;
	}

	private Point addStartingPoint(List<Point> pointsList) {
		Point startPoint = pointsList.get(0);
		for (var i : pointsList) {
			if (startPoint.getX() > i.getX()) {
				startPoint = i;
			}
		}
		return startPoint;
	}

	private double computeTurn(Point p1, Point p2, Point p3) {
		return ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()))
				- ((p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
	}

	private List<Point> fileToPoints(String filePath){
		List<Point> list = new ArrayList<Point>();
		File file = new File(filePath);
		String[] array;

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				array = scanner.nextLine().split(DELIM, 3);
				list.add(new Point(array[0], Double.parseDouble(array[1]), Double.parseDouble(array[2])));
			}
			scanner.close();
			if(list.size()<3) {
				throw new StringIndexOutOfBoundsException();
			}
			return list;
		} catch (IOException e) {
			// If file is not found
			System.out.println("file not found.");
			throw new IllegalArgumentException(e);
		}
	}
}
