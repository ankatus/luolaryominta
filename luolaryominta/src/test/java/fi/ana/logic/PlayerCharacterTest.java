/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antti
 */
public class PlayerCharacterTest {
    
    PlayerCharacter player;
    
    public PlayerCharacterTest() {
    }
    
    @Before
    public void setUp() {
        player = new PlayerCharacter(0, 0, 0);
    }
    
    @Test
    public void getSetHp() {
        player.setHp(10);
        assertEquals(10, player.getHp());
    }

}
