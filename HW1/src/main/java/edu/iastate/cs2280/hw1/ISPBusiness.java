package edu.iastate.cs2280.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ben Goeders
 *
 * The edu.iastate.cs2280.hw1.ISPBusiness class performs simulation over a grid
 * plain with cells occupied by different edu.iastate.cs2280.hw1.TownCell types.
 *
 */

public class ISPBusiness {
	
	/**
	 * Returns a new edu.iastate.cs2280.hw1.Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current edu.iastate.cs2280.hw1.Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) { // Loop through old town and call next on each cell of the grid, adding them to the new town's grid
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for (int r = 0; r < tOld.getLength(); r++) {
			for (int c = 0; c < tOld.getWidth(); c++) {
				tNew.grid[r][c] = tOld.grid[r][c].next(tNew);
			}
		}

		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) { // Returns number of casuals for the given town
		int casuals = 0;
		for (int r = 0; r < town.getLength(); r++) {
			for (int c = 0; c < town.getWidth(); c++) {
				if (town.grid[r][c].who() == State.CASUAL) {
					casuals++;
				}
			}
		}
		return casuals;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the edu.iastate.cs2280.hw1.Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Town t = null;
		System.out.println("How to populate grid (type 1 or 2): \n 1: from a file. \n 2: randomly with seed.");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		sc.nextLine();
		while (choose != 1 && choose != 2) {
			System.out.println("Please choose a valid option (type 1 or 2): \n 1: from a file. \n 2: randomly with seed.");
			choose = sc.nextInt();
			sc.nextLine();
		}

		if (choose == 1) {
			boolean x = true;
			while (x) {
				System.out.println("Please enter file path:");
				String path = sc.nextLine();
				try {
					t = new Town(path);
					x = false;
				}
				catch (FileNotFoundException e) {
					System.out.println("ERROR: file not found\n");
				}
			}
		}

		else if (choose == 2) {
			System.out.println("Provide rows, cols, and seed integer separated by space:");
			int len = sc.nextInt();
			int wid = sc.nextInt();
			int seed = sc.nextInt();

			t = new Town(len, wid);
			t.randomInit(seed);
		}

		sc.close();
		int potentialP = (t.getLength() * t.getWidth()) * 12;
		int totalP = 0;
		int i = 0;
		while (i < 12) {
			//System.out.println(t); // Used for testing
			totalP += getProfit(t);
			t = updatePlain(t);
			i++;
		}
		//System.out.println(t); // Used for testing

		double p = (totalP * 100) / (double)(potentialP);
		String formattedP = String.format("%.2f", p);
		System.out.println(formattedP + "%");
	}
}
