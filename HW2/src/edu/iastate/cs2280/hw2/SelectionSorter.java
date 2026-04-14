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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		this.algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int small;
		Point temp;

		for (int i = 0; i < this.points.length; i++) {
			small = i;
			for (int j = i + 1; j < this.points.length; j++) {
				if (this.pointComparator.compare(this.points[j], this.points[small]) < 0) {
					small = j;
				}
			}
			temp = this.points[small];
			this.points[small] = this.points[i];
			this.points[i] = temp;
		}
	}	
}
