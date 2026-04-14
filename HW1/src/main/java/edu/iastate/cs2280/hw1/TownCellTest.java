package edu.iastate.cs2280.hw1;

import static edu.iastate.cs2280.hw1.TownCell.nCensus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Ben Goeders
 *
 */

public class TownCellTest {

    @Test
    public void testCensus() {
        Town t = new Town(4, 4);
        t.randomInit(10);
        t.grid[0][0].census(nCensus);
        assertEquals("[1, 2, 0, 0, 0]", Arrays.toString(nCensus));
    }

    // cannot test methods who() and next() as they are abstract methods and therefore will not be called in their state in TownCell
    // also cannot test constructor as TownCell is an abstract class
    // see descendents for next implementation
}
