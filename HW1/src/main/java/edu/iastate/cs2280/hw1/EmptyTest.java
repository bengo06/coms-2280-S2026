package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ben Goeders
 *
 */

public class EmptyTest {

    @Test
    public void testConstructor() {
        Town t = new Town(1, 1);
        Empty e = new Empty(t, 0, 0);
        assertEquals(t, e.plain);
        assertEquals(0, e.row);
        assertEquals(0, e.col);
        assertEquals(State.EMPTY, e.type);
    }

    @Test
    public void testWho() {
        Town t = new Town(1, 1);
        Empty e = new Empty(t, 0, 0);
        assertEquals(State.EMPTY, e.who()); // test who
    }

    @Test
    public void testNext() {
        Town t1 = new Town(2, 2);
        Empty e1 = new Empty(t1, 0, 0);
        t1.grid[0][0] = e1;
        t1.grid[1][0] = new Empty(t1, 1, 0);
        t1.grid[0][1] = new Casual(t1, 0, 1);
        t1.grid[1][1] = new Casual(t1, 1, 1);
        assertEquals(State.RESELLER, e1.next(t1).who()); // test case outage + empty <= 1

        t1.grid[0][1] = new Empty(t1, 0, 1);
        assertEquals(State.CASUAL, e1.next(t1).who()); // test case turn default

    }

}
