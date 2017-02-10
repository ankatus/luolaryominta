package fi.ana.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;

public class MapTest {

    private GameMap map;
    
    public MapTest() {
    }

    @Before
    public void setUp() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        map = new GameMap(10, l);
    }

    @Test
    public void validValuesSetCorrectly() {
        map.setValue(1, 1, 1);
        map.setValue(5, 6, 1);
        assertEquals(1, map.getValue(1, 1));
        assertEquals(1, map.getValue(5, 6));
        map.setValue(1, 1, 0);
        map.setValue(5, 6, 0);
        assertEquals(0, map.getValue(1, 1));
        assertEquals(0, map.getValue(5, 6));
    }
    
    @Test
    public void invalidValuesNotSet() {
        map.setValue(1, 1, -1);
        map.setValue(-1, 1, 0);
        map.setValue(-1, -1, -10);
        map.setValue(-20, -30, -100);
        map.setValue(100, 100000, -500);
        map.setValue(10000, 52000, 6005);
        assertEquals(0, map.getValue(1, 1));
        assertEquals(-1, map.getValue(-1, 1));
        assertEquals(-1, map.getValue(-1, -1));
        assertEquals(-1, map.getValue(-20, -30));
        assertEquals(-1, map.getValue(100, 100000));
        assertEquals(-1, map.getValue(10000, 52000));
        
    }

    @Test
    public void limits() {
        map.setValue(9, 9, 1);
        assertEquals(1,map.getValue(9, 9));
        map.setValue(0, 9, 1);
        assertEquals(1,map.getValue(0, 9));
        map.setValue(0, 0, 1);
        assertEquals(1,map.getValue(0, 0));
    }
    
    @Test
    public void clearTest() {
        map.setValue(1, 1, 1);
        map.setValue(6, 7, 1);
        map.setValue(5, 9, 1);
        map.clear();
        assertEquals(0,map.getValue(1, 1));
        assertEquals(0,map.getValue(6, 7));
        assertEquals(0,map.getValue(5, 9));
    }

}
