 package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Ben Goeders
 *
 */

 /**
  * This class is responsible for creating objects that represent a position in the x-y plane
  *
  * It records the x and y position of each Point object
  */
 public class Point implements Comparable<Point>
{
	private int x; 
	private int y;
	
	public static boolean xORy;  // compare x coordinates if xORy == true and y coordinates otherwise 
	                             // To set its value, use Point.xORy = true or false. 

	/**
	 * Constructor assigns x and y to the default values of 0.
	 */
	public Point()  // default constructor
	{
		this.x = 0;
		this.y = 0;
	}

	/**
	 * This constructor takes in user values for x and y and assigns them to the instance variables.
	 * @param x
	 * @param y
	 */
	public Point(int x, int y)
	{
		this.x = x;  
		this.y = y;   
	}

	/**
	 * This constructor takes in a Point object and copies its fields onto the new Point's instance variables.
	 * @param p
	 */
	public Point(Point p) { // copy constructor
		x = p.getX();
		y = p.getY();
	}

	/**
	 * Grabs Point's x value.
	 * @return x
	 */
	public int getX()   
	{
		return x;
	}

	/**
	 * Grabs Point's y value.
	 * @return y
	 */
	public int getY()
	{
		return y;
	}
	
	/** 
	 * Set the value of the static instance variable xORy. 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy)
	{
		Point.xORy = xORy;
	}

	/**
	 * Checks equality with another Point by comparing x and y values.
	 * @param obj   the reference object with which to compare.
	 * @return false if the Points do not have the same x and y values, the parameter is null, or the classes of the parameter and the Point do not match.
	 * 		   true if the Points have the same x and y values.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
    
		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q depending on the value of the static variable xORy 
	 * @param 	q 
	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
	 * 		    0   if this.x == q.x && this.y == q.y)  
	 * 			1	otherwise 
	 */
	@Override
	public int compareTo(Point q)
	{
		if ((xORy && (this.x < q.x || (this.x == q.x && this.y < q.y))) || (!xORy && (this.y < q.y || (this.y == q.y && this.x < q.x)))) {
			return -1;
		}

		else if (this.x == q.x && this.y == q.y) {
			return 0;
		}

		else {
			return 1;
		}
	}
	
	
	/**
	 * Output a point in the standard form (x, y). 
	 */
	@Override
    public String toString() 
	{
		return "(" + this.x + ", " + this.y + ")";
	}
}
