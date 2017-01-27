
package fi.ana.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerCharacterTest {
    
    private PlayerCharacter pc;
    
    public PlayerCharacterTest() {
    }
    
    @Before
    public void setUp() {
        pc = new PlayerCharacter(1,2,3);
    }
    
    @Test
    public void constructorSetsValuesCorrectly() {
        assertEquals(1,pc.getX());
        assertEquals(2,pc.getY());
        assertEquals(3, pc.getHp());
    }
    
    @Test
    public void damageTest() {
        pc.takeDamage(1);
        assertEquals(2,pc.getHp());
        pc.takeDamage(2);
        assertEquals(0, pc.getHp());
    }
    
    @Test
    public void hpNotNegative() {
        pc.takeDamage(1000000);
        assertEquals(0, pc.getHp());
    }
    
    @Test
    public void negativeDamageTest() {
        pc.takeDamage(-100);
        assertEquals(3, pc.getHp());
    }
    
    @Test
    public void hpLimitTest() {
        pc.takeDamage(3);
        assertEquals(0, pc.getHp());
    }
}
