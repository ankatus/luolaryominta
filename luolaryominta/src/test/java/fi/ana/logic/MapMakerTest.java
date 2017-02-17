package fi.ana.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapMakerTest {

    private MapMaker mm;

    public MapMakerTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void smallMapNotNull() {
        assertNotNull(mm.makeSmallMap());
    }
    
    @Test
    public void mediumMapNotNull() {
        assertNotNull(mm.makeMediumMap());
    }
    
    @Test
    public void largeMapNotNull() {
        assertNotNull(mm.makeLargeMap());
    }

    @Test
    public void makeSmallMapCreatesSomething() {
        boolean notEmpty = false;
        GameMap map = mm.makeSmallMap();

        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                if (map.getValue(x, y) != 0) {
                    notEmpty = true;
                    break;
                }
            }
        }

        assertTrue(notEmpty);
    }
    
    @Test
    public void makeMediumMapCreatesSomething() {
        boolean notEmpty = false;
        GameMap map = mm.makeSmallMap();

        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                if (map.getValue(x, y) != 0) {
                    notEmpty = true;
                    break;
                }
            }
        }

        assertTrue(notEmpty);
    }
    
    @Test
    public void makeLargeMapCreatesSomething() {
        boolean notEmpty = false;
        GameMap map = mm.makeSmallMap();

        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                if (map.getValue(x, y) != 0) {
                    notEmpty = true;
                    break;
                }
            }
        }

        assertTrue(notEmpty);
    }
}
