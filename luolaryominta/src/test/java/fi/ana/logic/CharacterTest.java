package fi.ana.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    private Character c;

    public CharacterTest() {
    }

    @Before
    public void setUp() {
        c = new Character(1, 2, 3, 3);
    }

    @Test
    public void constructorSetsValuesCorrectly() {
        assertEquals(1, c.getX());
        assertEquals(2, c.getY());
        assertEquals(3, c.getHp());
        assertEquals(3,c.getType());
    }
}
