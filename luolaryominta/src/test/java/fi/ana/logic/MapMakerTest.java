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
        mm = new MapMaker();
    }

    @Test
    public void makeDefaultMapRightSize() {
        Map map = mm.makeDefaultMap();
        assertEquals(10, map.getSize());
    }

    @Test
    public void makeDefaultMapCreatesSomething() {
        boolean notEmpty = false;
        Map map = mm.makeDefaultMap();
        
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
