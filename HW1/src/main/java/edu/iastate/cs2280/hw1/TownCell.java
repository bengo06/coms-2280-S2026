package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *	Also provide appropriate comments for this class
 *
 */

public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		this.plain = p;
		this.row = r;
		this.col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int[] nCensus) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		for (int r = this.row - 1; r <= this.row + 1; r++) {
			for (int c = this.col - 1; c <= this.col + 1; c++) { // start at top left of neighborhood
				if (r == this.row && c == this.col) { // if on the cell being analyzed, pass over this iteration
					continue;
				}
				if (r >= 0 && r < this.plain.getLength() && c >= 0 && c < this.plain.getWidth()) { // if the r and c values are within the grid
					switch (this.plain.grid[r][c].who()) { // add one to the corresponding array location for that cell's state
						case State.RESELLER:
							nCensus[RESELLER]++;
							break;
						case State.EMPTY:
							nCensus[EMPTY]++;
							break;
						case State.CASUAL:
							nCensus[CASUAL]++;
							break;
						case State.OUTAGE:
							nCensus[OUTAGE]++;
							break;
						case State.STREAMER:
							nCensus[STREAMER]++;
							break;
					}
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return edu.iastate.cs2280.hw1.State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return edu.iastate.cs2280.hw1.TownCell
	 */
	public abstract TownCell next(Town tNew);
}
