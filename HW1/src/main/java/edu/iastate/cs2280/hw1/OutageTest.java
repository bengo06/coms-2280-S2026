package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ben Goeders
 *
 */

public class OutageTest {

    @Test
    public void testConstructor() {
        Town t = new Town(1, 1);
        Outage o = new Outage(t, 0, 0);
        assertEquals(t, o.plain);
        assertEquals(0, o.row);
        assertEquals(0, o.col);
        assertEquals(State.OUTAGE, o.type);
    }

    @Test
    public void testWho() {
        Town t = new Town(1, 1);
        Outage o = new Outage(t, 0, 0);
        assertEquals(State.OUTAGE, o.who()); // test who
    }

    @Test
    public void testNext() {
        Town t1 = new Town(1, 1);
        Outage o1 = new Outage(t1, 0, 0);
        t1.grid[0][0] = o1;
        assertEquals(State.EMPTY, o1.next(t1).who()); // test case default
    }

}
