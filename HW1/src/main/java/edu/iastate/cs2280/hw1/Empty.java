package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *
 */

public class Empty extends TownCell {

    protected State type;

    public Empty(Town p, int r, int c) {
        super(p, r, c);
        this.type = State.EMPTY; // give object correct state
    }

    @Override
    public State who() {
        return State.EMPTY;
    }

    @Override
    public TownCell next(Town tNew) {
        census(nCensus); // based on census values, return correct cell type perscribed in the documentation
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, this.row, this.col);
        }
        return new Casual(tNew, this.row, this.col);
    }
}
