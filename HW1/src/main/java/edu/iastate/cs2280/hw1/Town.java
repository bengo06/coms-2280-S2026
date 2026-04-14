package edu.iastate.cs2280.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Ben Goeders
 *
 */

public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		this.grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		try { // File throws exception if it cannot find the file
			File f = new File(inputFileName);
			Scanner sc = new Scanner(f);
			this.length = sc.nextInt(); // read l, w from input file
			this.width = sc.nextInt();
			sc.nextLine();
			this.grid = new TownCell[length][width]; // create town grid
			String line;
			int c;
			for (int r = 0; r < length; r++) {
				line = sc.nextLine();
				c = 0;
				for (int i = 0; i < line.length(); i++) {
					switch (line.charAt(i)) { // assign position in grid the correct TownCell type based on character in file
						case 'R':
							grid[r][c] = new Reseller(this, r, c);
							c++;
							break;

						case 'E':
							grid[r][c] = new Empty(this, r, c);
							c++;
							break;

						case 'C':
							grid[r][c] = new Casual(this, r, c);
							c++;
							break;

						case 'O':
							grid[r][c] = new Outage(this, r, c);
							c++;
							break;

						case 'S':
							grid[r][c] = new Streamer(this, r, c);
							c++;
							break;
					}
				}
			}
			sc.close();
		}

		catch(FileNotFoundException e) { // rethrows exception thrown by File
			throw new FileNotFoundException();
		}
	}

	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * edu.iastate.cs2280.hw1.Casual, edu.iastate.cs2280.hw1.Empty, edu.iastate.cs2280.hw1.Outage, edu.iastate.cs2280.hw1.Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed); // seeds rand
		int luck = 0;
		for (int r = 0; r < this.length; r++) {
			for (int c = 0; c < this.width; c++) {
				luck = rand.nextInt(5); // gets next int in range 5 from rand and fills the cell based on that value
				switch (luck) {
					case 0:
						grid[r][c] = new Reseller(this, r, c);
						break;

					case 1:
						grid[r][c] = new Empty(this, r, c);
						break;

					case 2:
						grid[r][c] = new Casual(this, r, c);
						break;

					case 3:
						grid[r][c] = new Outage(this, r, c);
						break;

					case 4:
						grid[r][c] = new Streamer(this, r, c);
						break;
				}
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		TownCell cell;
		for (int r = 0; r < this.length; r++) {
			for (int c = 0; c < this.width; c++) {
				cell = this.grid[r][c];
				switch (cell.who()) { // adds correct character to the string by the cell's state
					case State.RESELLER:
						s += "R ";
						break;
					case State.EMPTY:
						s += "E ";
						break;
					case State.CASUAL:
						s += "C ";
						break;
					case State.OUTAGE:
						s += "O ";
						break;
					case State.STREAMER:
						s += "S ";
						break;
				}
			}
			s += "\n"; // break rows of the grid with newline
		}
		return s;
	}
}
