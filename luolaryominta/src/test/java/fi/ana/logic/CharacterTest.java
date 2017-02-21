package fi.ana.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    private GameCharacter c;

    public CharacterTest() {
    }

    @Before
    public void setUp() {
        c = new GameCharacter(1, 2, 3, 3);
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
}
