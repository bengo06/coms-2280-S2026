package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Ben Goeders
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	
	protected Point[] pointsInitial;
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}

		this.points = new Point[pts.length];
		this.pointsInitial = new Point[pts.length];
		for (int i = 0; i < pts.length; i++) {
			this.points[i] = pts[i];
			this.pointsInitial[i] = pts[i];
		}
	}

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		if (order < 0 || order > 1) {
			throw new IllegalArgumentException();
		}

		if (order == 0) {
			Point.setXorY(true);
			pointComparator = new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.compareTo(o2);
				}
			};
		}

		if (order == 1) {
			Point.setXorY(false);
			pointComparator = new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.compareTo(o2);
				}
			};
		}
	}

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	protected abstract void sort();
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		for (int i = 0; i < pts.length; i++) {
			pts[i] = points[i];
		}
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}

	/**
	 * Resets the value of this AbstractSolver's points array to the initial value passed to it
	 */
	protected void resetPoints() {
		for (int i = 0; i < this.points.length; i++) {
			this.points[i] = this.pointsInitial[i];
		}
	} // This was implemented to ensure the sorting that happens in PointScanner happens over the same array for X and Y
}
