package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *
 */

public class Reseller extends TownCell {

    protected State type;

    public Reseller(Town p, int r, int c) {
        super(p, r, c);
        this.type = State.RESELLER; // give object correct state
    }

    @Override
    public State who() {
        return State.RESELLER;
    }

    @Override
    public TownCell next(Town tNew) {
        census(nCensus); // based on census values, return correct cell type perscribed in the documentation
        if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
            return new Empty(tNew, this.row, this.col);
        }

        if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, this.row, this.col);
        }

        return new Reseller(tNew, this.row, this.col);
    }
}
