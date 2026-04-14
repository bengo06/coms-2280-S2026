package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Ben Goeders
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		this.algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.
	 */
	@Override 
	public void sort()
	{
		for (int i = 1; i < this.points.length; i++) {
			Point focus = this.points[i];
			int j = i - 1;
			while (j >= 0 && this.pointComparator.compare(focus, this.points[j]) < 0) {
				this.points[j + 1] = this.points[j];
				j--;
			}
			this.points[j + 1] = focus;
		}
	}		
}
