
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
        c = new Character(1,2,3,3);
    }
    
    @Test
    public void constructorSetsValuesCorrectly() {
        assertEquals(1,c.getX());
        assertEquals(2,c.getY());
        assertEquals(3, c.getHp());
    }
    
    @Test
    public void damageTest() {
        c.takeDamage(1);
        assertEquals(2,c.getHp());
        c.takeDamage(2);
        assertEquals(0, c.getHp());
    }
    
    @Test
    public void hpNotNegative() {
        c.takeDamage(1000000);
        assertEquals(0, c.getHp());
    }
    
    @Test
    public void negativeDamageTest() {
        c.takeDamage(-100);
        assertEquals(3, c.getHp());
    }
    
    @Test
    public void hpLimitTest() {
        c.takeDamage(3);
        assertEquals(0, c.getHp());
    }
}
