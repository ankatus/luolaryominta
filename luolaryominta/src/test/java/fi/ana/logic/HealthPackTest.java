/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katantti
 */
public class HealthPackTest {

    private HealthPack hPack;
    private PlayerCharacter player;

    public HealthPackTest() {
    }

    @Before
    public void setUp() {
        hPack = new HealthPack(0, 0);
        player = new PlayerCharacter(0, 0, 0);
    }

    @Test
    public void getSetXTest() {
        hPack.setX(5);
        assertEquals(5, hPack.getX());
    }
    
    @Test
    public void GetSetYTest() {
        hPack.setY(5);
        assertEquals(5, hPack.getY());
    }
    
    @Test
    public void interactTest() {
        hPack.interact(player);
        assertEquals(1, player.getHp());
    }
}
