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
    private Monster gc;

    public HealthPackTest() {
    }

    @Before
    public void setUp() {
        hPack = new HealthPack(0, 0);
        gc = new Monster(0, 0, 0, 0);
    }

    @Test
    public void xSetGetTest() {
        hPack.setX(5);
        assertEquals(5, hPack.getX());
    }
    
    @Test
    public void ySetGetTest() {
        hPack.setY(5);
        assertEquals(5, hPack.getY());
    }
    
    @Test
    public void interactTest() {
        hPack.interact(gc);
        assertEquals(1, gc.getHp());
    }
}
