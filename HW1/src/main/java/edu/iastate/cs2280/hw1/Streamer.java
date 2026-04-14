package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *
 */

public class Streamer extends TownCell {

    protected State type;

    public Streamer(Town p, int r, int c) {
        super(p, r, c);
        this.type = State.STREAMER; // give object correct state
    }

    @Override
    public State who() {
        return State.STREAMER;
    }

    @Override
    public TownCell next(Town tNew) { // based on census values, return correct cell type perscribed in the documentation
        census(nCensus);
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, this.row, this.col);
        }

        if (nCensus[RESELLER] >= 1) {
            return new Outage(tNew, this.row, this.col);
        }

        if (nCensus[OUTAGE] >= 1) {
            return new Empty(tNew, this.row, this.col);
        }

        if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, this.row, this.col);
        }

        return new Streamer(tNew, this.row, this.col);
    }
}
