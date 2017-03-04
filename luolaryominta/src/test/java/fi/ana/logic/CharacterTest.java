package fi.ana.logic;

import fi.ana.pathfinding.Path;
import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    private Monster c;

    public CharacterTest() {
    }

    @Before
    public void setUp() {
        c = new Monster(1, 2, 3, 3);
    }

    @Test
    public void constructorSetsValuesCorrectly() {
        assertEquals(1, c.getX());
        assertEquals(2, c.getY());
        assertEquals(3, c.getHp());
        assertEquals(3,c.getType());
    }
    
    @Test
    public void typeSetGetTest() {
        c.setType(0);
        assertEquals(0, c.getType());
    }
    
    @Test
    public void xSetGetTest() {
        c.setX(10);
        assertEquals(10, c.getX());
    }
    
    @Test
    public void ySetGetTest() {
        c.setY(10);
        assertEquals(10, c.getY());
    }
    
    @Test
    public void hpSetGetTest() {
        c.setHP(10);
        assertEquals(10, c.getHp());
    }
    
    @Test
    public void pathSetGetTest() {
        List<PathfinderCoordinate> list = new ArrayList();
        list.add(new PathfinderCoordinate(0, 0, null));
        list.add(new PathfinderCoordinate(1, 2, list.get(0)));
        Path path = new Path(list);
        c.setPath(path);
        assertEquals(0, c.getPath().getPathfinderCoordinate(0).getX());
        assertEquals(0, c.getPath().getPathfinderCoordinate(0).getY());
        assertNull(c.getPath().getPathfinderCoordinate(0).getParent());
        assertEquals(1, c.getPath().getPathfinderCoordinate(1).getX());
        assertEquals(2, c.getPath().getPathfinderCoordinate(1).getY());
        assertEquals(c.getPath().getPathfinderCoordinate(0), c.getPath().getPathfinderCoordinate(1).getParent());
    }
}
