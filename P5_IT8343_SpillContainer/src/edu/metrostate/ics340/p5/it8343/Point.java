package edu.metrostate.ics340.p5.it8343;

class Point {
	private String name;
	private double x, y, dis;

	public Point(String name, double x, double y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.dis = 0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) {
			return false;
		}
		return ((Point) o).getName().compareTo(name) == 0;
	}

	@Override
	public String toString() {
		return "Point [name=" + name + ", x=" + x + ", y=" + y + ", dis=" + dis + "]";
	}
}
