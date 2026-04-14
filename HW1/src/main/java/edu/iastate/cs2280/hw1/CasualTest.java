package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ben Goeders
 *
 */

public class CasualTest {

    @Test
    public void testConstructor() {
        Town t = new Town(1, 1);
        Casual c = new Casual(t, 0, 0);
        assertEquals(t, c.plain);
        assertEquals(0, c.row);
        assertEquals(0, c.col);
        assertEquals(State.CASUAL, c.type);
    }

    @Test
    public void testWho() {
        Town t = new Town(1, 1);
        Casual c = new Casual(t, 0, 0);
        assertEquals(State.CASUAL, c.who()); // test who
    }

    @Test
    public void testNext() {
        Town t1 = new Town(1, 1);
        Casual c1 = new Casual(t1, 0, 0);
        t1.grid[0][0] = c1;
        assertEquals(State.RESELLER, c1.next(t1).who()); // test case outage + empty <= 1

        Town t2 = new Town(3, 3);
        Casual c2 = new Casual(t2, 1, 1);
        t2.grid[1][1] = c2;
        t2.grid[0][0] = new Casual(t2, 0, 0);
        t2.grid[0][1] = new Casual(t2, 0, 1);
        t2.grid[0][2] = new Casual(t2, 0, 2);
        t2.grid[1][0] = new Casual(t2, 1, 0);
        t2.grid[1][2] = new Outage(t2, 1, 2);
        t2.grid[2][0] = new Outage(t2, 2, 0);
        t2.grid[2][1] = new Empty(t2, 2, 1);
        t2.grid[2][2] = new Reseller(t2, 2, 2);
        assertEquals(State.OUTAGE, c2.next(t2).who()); // test case any reseller

        t2.grid[2][2] = new Streamer(t2, 2, 2);
        assertEquals(State.STREAMER, c2.next(t2).who()); // test case any streamer

        t2.grid[2][2] = new Casual(t2, 2, 2);
        assertEquals(State.STREAMER, c2.next(t2).who()); // test case 5 or more casual

        t2.grid[2][2] = new Outage(t2, 2, 2);
        assertEquals(State.CASUAL, c2.next(t2).who()); // test case default


    }

}
