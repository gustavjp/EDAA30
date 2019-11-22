package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class Mountain extends Fractal {
	double dev;
	Point p1, p2, p3;
	Map<Side, Point> sideMap;
	
	/**
	 * Constructs and initializes a mountain.
	 * 
	 * @param p1 the first point of the triangle
	 * @param p2 the second point of the triangle
	 * @param p3 the third point of the triangle
	 * @param dev the y deviation
	 */
	public Mountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		sideMap = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Mountain";
	}
	
	/**
	 * Returns a point between two points
	 * @param a the first point
	 * @param b the second point
	 * @return the point between point a and point b
	 */
	private Point getCenter(Point a, Point b, double dev) {
		Side s = new Side(a, b);
		Point centerPoint;
		if(sideMap.containsKey(s)) {
			centerPoint = sideMap.get(s);
			sideMap.remove(s);
		} else {
			centerPoint = new Point(((a.getX() + b.getX()) / 2), (((a.getY() + b.getY()) / 2) + (int)RandomUtilities.randFunc(dev)));
			sideMap.put(s, centerPoint);
		}
		return centerPoint;
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		sideMap.clear();
		fractalTriangle(turtle, order, dev, p1, p2, p3);
	}
	
	/* 
	 * Recursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, double dev, Point a, Point b, Point c) {
		if (order == 0 ) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point ab = getCenter(a, b, dev);
			Point bc = getCenter(b, c, dev);
			Point ca = getCenter(a, c, dev);

			fractalTriangle(turtle, order - 1, dev / 2, a, ab, ca);
			fractalTriangle(turtle, order - 1, dev / 2, b, ab, bc);
			fractalTriangle(turtle, order -1, dev / 2, c, bc, ca);
			fractalTriangle(turtle, order - 1, dev / 2, ab, bc, ca);
		}	
	}
}
