package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * @author Ben Goeders
 *
 */

public class TownTest {

    @Test
    public void testConstructor() {
        Town t = new Town(4, 2);
        assertEquals(4, t.getLength());
        assertEquals(2, t.getWidth());
        assertEquals(4, t.grid.length);
        assertEquals(2, t.grid[0].length);
    }

    @Test
    public void testFileConstructor() {
        Town t;

        try {
            t = new Town("src/main/java/edu/iastate/cs2280/hw1/ISP4x4.txt");

        }

        catch (FileNotFoundException e) {
            t = null;
        }
        assertEquals(4, t.getLength());
        assertEquals(4, t.getWidth());
        assertEquals(4, t.grid.length);
        assertEquals(4, t.grid[0].length);
        assertEquals("O R O R \n" + "E E C O \n" + "E S O S \n" + "E O R R \n", t.toString());
    }

    @Test
    public void testLength() {
        Town t = new Town (2, 1);
        assertEquals(2, t.getLength());
    }

    @Test
    public void testWidth() {
        Town t = new Town (1, 2);
        assertEquals(2, t.getWidth());
    }

    @Test
    public void testRandomInit() {
        Town t = new Town (4, 4);
        t.randomInit(10);
        assertEquals("O R O R \n" + "E E C O \n" + "E S O S \n" + "E O R R \n", t.toString());
    }

    @Test
    public void testToString() {
        Town t;

        try {
            t = new Town("src/main/java/edu/iastate/cs2280/hw1/ISP4x4.txt");

        }

        catch (FileNotFoundException e) {
            t = null;
        }

        assertEquals("O R O R \n" + "E E C O \n" + "E S O S \n" + "E O R R \n", t.toString());

    }

}
