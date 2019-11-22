package mountain;

public class Side {
	Point a;
	Point b;
	
	/**
	 * Constructs and initializes a Side with points a and b
	 * 
	 * @param a the first point of the side
	 * @param b the second point of the side
	 */
	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Returns the first point of the side
	 * 
	 * @return the first point of the side
	 */
	public Point getA() {
		return a;
	}
	
	/**
	 * Returns the second point of the side
	 * 
	 * @return the second point of the side
	 */
	public Point getB() {
		return b;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Side) {
			Side s = (Side)o;
			if((a.equals(s.getA()) && b.equals(s.getB())) || (a.equals(s.getB()) && b.equals(s.getA()))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 42 * a.hashCode() + b.hashCode();
	}
}
