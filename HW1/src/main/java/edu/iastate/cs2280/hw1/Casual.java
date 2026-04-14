package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *
 */

public class Casual extends TownCell {

    protected State type;

    public Casual(Town p, int r, int c) {
        super(p, r, c);
        this.type = State.CASUAL; // give object correct state
    }

    @Override
    public State who() {
        return type;
    }

    @Override
    public TownCell next(Town tNew) {
        census(nCensus); // based on census values, return correct cell type perscribed in the documentation
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, this.row, this.col);
        }

        if (nCensus[RESELLER] >= 1) {
            return new Outage(tNew, this.row, this.col);
        }

        if (nCensus[STREAMER] >= 1) {
            return new Streamer(tNew, this.row, this.col);
        }

        if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, this.row, this.col);
        }

        return new Casual(tNew, this.row, this.col);
    }
}
