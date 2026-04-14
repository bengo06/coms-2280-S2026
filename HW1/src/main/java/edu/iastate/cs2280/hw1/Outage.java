package edu.iastate.cs2280.hw1;

/**
 *  @author Ben Goeders
 *
 */

public class Outage extends TownCell {

    protected State type;

    public Outage(Town p, int r, int c) {
        super(p, r, c);
        this.type = State.OUTAGE; // give object correct state
    }

    @Override
    public State who() {
        return State.OUTAGE;
    }

    @Override
    public TownCell next(Town tNew) { // based on census values, return correct cell type perscribed in the documentation
        return new Empty(tNew, this.row, this.col);
    }
}
