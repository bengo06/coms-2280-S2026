package edu.iastate.cs2280.hw1;

import static edu.iastate.cs2280.hw1.ISPBusiness.getProfit;
import static edu.iastate.cs2280.hw1.ISPBusiness.updatePlain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ben Goeders
 *
 */

public class ISPBusinessTest {

    @Test
    public void testUpdatePlain() {
        Town t1 = new Town(4, 4);
        t1.randomInit(10);
        Town t2 = updatePlain(t1);
        assertEquals("E E E E \n" + "C C O E \n" + "C O E O \n" + "C E E E \n", t2.toString());
    }

    @Test
    public void testGetProfit() {
        Town t = new Town(2, 2);
        t.grid[0][0] = new Casual(t, 0, 0);
        t.grid[0][1] = new Casual(t, 0, 1);
        t.grid[1][0] = new Reseller(t, 1, 0);
        t.grid[1][1] = new Casual(t, 1, 1);
        int p = getProfit(t);
        assertEquals(3, p);
    }
}
