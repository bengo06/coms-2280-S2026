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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		this.algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(this.points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length < 2) {
			return;
		}

		int mid = pts.length / 2;
		Point[] l = new Point[mid];
		Point[] r = new Point[pts.length - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = pts[i];
		}

		for (int i = mid; i < pts.length; i++) {
			r[i - mid] = pts[i];
		}

		mergeSortRec(l);
		mergeSortRec(r);

		merge(pts, l, r);
	}

	/**
	 * This method merges the 2 halves of the points array that was split in mergeSortRec
	 * back into the original points array, while sorting the 2 halves by comparing their values
	 *
	 * @param pts original points array
	 * @param l left array of points
	 * @param r right array of points
	 */
	private void merge(Point[] pts, Point[] l, Point[] r)
	{
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < l.length && j < r.length) {
			if (this.pointComparator.compare(l[i], r[j]) < 0) {
				pts[k] = l[i];
				i++;
			}

			else {
				pts[k] = r[j];
				j++;
			}
			k++;
		}

		while (i < l.length) {
			pts[k] = l[i];
			i++;
			k++;
		}

		while (j < r.length) {
			pts[k] = r[j];
			j++;
			k++;
		}
	}
}
