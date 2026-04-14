package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ben Goeders
 *
 */

public class ResellerTest {

    @Test
    public void testConstructor() {
        Town t = new Town(1, 1);
        Reseller r = new Reseller(t, 0, 0);
        assertEquals(t, r.plain);
        assertEquals(0, r.row);
        assertEquals(0, r.col);
        assertEquals(State.RESELLER, r.type);
    }

    @Test
    public void testWho() {
        Town t = new Town(1, 1);
        Reseller r = new Reseller(t, 0, 0);
        assertEquals(State.RESELLER, r.who()); // test who
    }

    @Test
    public void testNext() {
        Town t1 = new Town(2, 2);
        Reseller r1 = new Reseller(t1, 0, 0);
        t1.grid[0][0] = r1;
        t1.grid[1][0] = new Empty(t1, 1, 0);
        t1.grid[0][1] = new Outage(t1, 0, 1);
        t1.grid[1][1] = new Casual(t1, 1, 1);
        assertEquals(State.EMPTY, r1.next(t1).who()); // test case 3 or fewer casual

        t1.grid[0][1] = new Empty(t1, 0, 1);
        t1.grid[1][1] = new Empty(t1, 1, 1);
        assertEquals(State.EMPTY, r1.next(t1).who()); // test case 3 or more empty

        Town t2 = new Town(2, 3);
        Reseller r2 = new Reseller(t2, 0, 1);
        t2.grid[0][0] = new Casual(t2, 0, 0);
        t2.grid[1][0] = new Casual(t2, 1, 0);
        t2.grid[0][1] = r2;
        t2.grid[1][1] = new Casual(t2, 1, 1);
        t2.grid[0][2] = new Casual(t2, 0, 2);
        t2.grid[1][2] = new Casual(t2, 1, 2);
        assertEquals(State.STREAMER, r2.next(t2).who()); // test case 5 or more casual

        t2.grid[0][0] = new Empty(t2, 0, 0);
        assertEquals(State.RESELLER, r2.next(t2).who()); // test case default

    }

}
