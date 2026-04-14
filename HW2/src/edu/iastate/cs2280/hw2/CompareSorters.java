package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Ben Goeders
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		int numTrials = 1;
		System.out.print("Performances of Four Sorting Algorithms in Point Scanning\nKeys:  1 (random points)  2 (from a file)  3 (exit)\n");
		Scanner sc = new Scanner(System.in); // Scanner for user input
		while(true) { // breaks if 3 is input
			PointScanner[] scanners = new PointScanner[4];
			int choice = 0;
			boolean d1 = true; // boolean to handle exceptions and reprompt the user for valid input
			while (d1) { // loops while the user inputs anything other than integers
				try {
					System.out.print("Trial " + numTrials + ": ");
					choice = sc.nextInt(); // grab user choice (1, 2, 3)
					d1 = false; // break while loop
				}

				catch (InputMismatchException e) { // catch exception thrown by Scanner
					System.out.println("ERROR: Invalid input. Try again\n");
				}
				sc.nextLine();
			}

			if (choice == 1) { // if random points chosen
				boolean x1 = true; // booleans to handle exceptions and reprompt the user for valid input
				boolean x1a = true;
				while (x1) { // loops while the points array is invalid
					try {
						Point[] pts = null; // init points array
						while (x1a) { // loops while the number of random points given is invalid
							try {
								System.out.print("Enter the number of random points: ");
								pts = generateRandomPoints(sc.nextInt(), new Random()); // create random points array using the generate random points method
								x1a = false; // break while loop
							}

							catch (IllegalArgumentException e) { // catch exception thrown by generateRandomPoints
								System.out.println("ERROR: Number of random points is not valid. Try again\n");
							}

							catch (InputMismatchException e) { // catch exception thrown by Scanner
								System.out.println("ERROR: Invalid input. Try again\n");
							}
							sc.nextLine();
						}
						scanners[0] = new PointScanner(pts, Algorithm.SelectionSort); // create PointScanner(s) based on random points array
						scanners[1] = new PointScanner(pts, Algorithm.InsertionSort);
						scanners[2] = new PointScanner(pts, Algorithm.MergeSort);
						scanners[3] = new PointScanner(pts, Algorithm.QuickSort);
						x1 = false; // break while loop
					}

					catch (IllegalArgumentException e) { // catch exception thrown by PointScanner(s)
						System.out.println("ERROR: Points was not properly initialized. Try again\n");
					}
				}
			}

			else if (choice == 2) { // if file input chosen
				boolean x2 = true; // boolean to handle exceptions and reprompt the user for valid input
				System.out.println("Points from a file");
				while (x2) {
					try {
						System.out.print("File name: ");
						String fn = sc.next(); // grab file name
						scanners[0] = new PointScanner(fn, Algorithm.SelectionSort); // create PointScanner(s) based on file contents
						scanners[1] = new PointScanner(fn, Algorithm.InsertionSort);
						scanners[2] = new PointScanner(fn, Algorithm.MergeSort);
						scanners[3] = new PointScanner(fn, Algorithm.QuickSort);
						x2 = false; // break while loop
					}

					catch (FileNotFoundException e) { // catch exception thrown by PointScanner(s)
						System.out.println("ERROR: File does not exist. Try again\n");
					}

					catch (InputMismatchException e) { // catch exception thrown by PointScanner(s)
						System.out.println("ERROR: Odd number of integers in file. Try again\n");
					}
				}
			}

			else if (choice == 3) { // if stop chosen
				break; // break outer loop, stop program
			}

			else { // if anything other 1, 2, or 3 chosen
				System.out.println("ERROR: Enter 1, 2, or 3\n"); // reprompt for valid input
				numTrials--; // decrement trials to keep consistent trial value
			}

			if (scanners[0] != null) { // if the PointScanner(s) were initialized
				System.out.println("\nAlgorithm       Size    Time (ns)");
				System.out.println("___________________________________");
				for (PointScanner p : scanners) {
					p.scan();
					System.out.println(p.stats());
					p.writeMCPToFile(); // writes to file MCP.txt
				}
				System.out.println("___________________________________\n");
			}
			numTrials++; // increment trials
		}
		sc.close(); // close scanner
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] pts = new Point[numPts];
		for (int i = 0; i < numPts; i++) {
			pts[i] = new Point(rand.nextInt(-50, 51), rand.nextInt(-50, 51));
		}
			return pts;
	}
}
