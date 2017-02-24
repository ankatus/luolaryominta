package fi.ana.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;

public class GameMapTest {

    private GameMap map;
    
    public GameMapTest() {
    }

    @Before
    public void setUp() {
        map = new GameMap(10);
    }

    @Test
    public void valuesSetCorrectly() {
        map.setValue(1, 1, true);
        map.setValue(5, 6, true);
        assertTrue(map.getValue(1, 1));
        assertTrue(map.getValue(5, 6));
        map.setValue(1, 1, false);
        map.setValue(5, 6, false);
        assertTrue(!map.getValue(1, 1));
        assertTrue(!map.getValue(5, 6));
    }

    @Test
    public void limits() {
        map.setValue(9, 9, true);
        assertTrue(map.getValue(9, 9));
        map.setValue(0, 9, true);
        assertTrue(map.getValue(0, 9));
        map.setValue(0, 0, true);
        assertTrue(map.getValue(0, 0));
    }
    
    @Test
    public void clearTest() {
        map.setValue(1, 1, true);
        map.setValue(6, 7, true);
        map.setValue(5, 9, true);
        map.clear();
        assertTrue(!map.getValue(1, 1));
        assertTrue(!map.getValue(6, 7));
        assertTrue(!map.getValue(5, 9));
    }

}
