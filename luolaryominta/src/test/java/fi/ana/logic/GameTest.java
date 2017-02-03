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
public class GameTest {

    Game g;

    @Before
    public void setUp() {
        g = new Game();
    }

    @Test
    public void constructorTest() {
        assertEquals(0, g.getPlayer().getX());
        assertEquals(0, g.getPlayer().getX());
        assertEquals(3, g.getPlayer().getType());
        assertNotNull(g.getMap());
    }

    @Test
    public void mapBigEnoughForTests() {
        assertTrue(g.getMap().getSize() >= 2);
    }

    @Test
    public void textCommandMoveTest1() {
        g.interpretCommand("d");
        assertEquals(1, g.getPlayer().getX());

    }

    @Test
    public void textCommandMoveTest2() {
        g.interpretCommand("d");
        g.interpretCommand("d");
        g.interpretCommand("a");
        assertEquals(1, g.getPlayer().getX());
    }

    @Test
    public void textCommandMoveTest3() {
        g.interpretCommand("s");
        assertEquals(1, g.getPlayer().getY());
    }

    @Test
    public void textCommandMoveTest4() {
        g.interpretCommand("s");
        g.interpretCommand("s");
        g.interpretCommand("w");
        assertEquals(1, g.getPlayer().getY());
    }
    
    @Test
    public void textCommandMoveLimitsTest1() {
        g.interpretCommand("a");
        assertEquals(0, g.getPlayer().getX());
    }
    
    @Test
    public void textCommandMoveLimitsTest2() {
        g.interpretCommand("w");
        assertEquals(0, g.getPlayer().getY());
    }

    @Test
    public void moveByLimitsTest1() {
        g.moveBy(g.getPlayer(), g.getMap().getSize() + 10, 0);
        assertEquals(0, g.getPlayer().getX());

    }

    @Test
    public void moveByLimitsTest2() {
        g.moveBy(g.getPlayer(), 0, g.getMap().getSize() + 10);
        assertEquals(0, g.getPlayer().getY());
    }

    @Test
    public void moveByLimitsTest3() {
        g.moveBy(g.getPlayer(), 0, -1 * g.getMap().getSize() - 10);
        assertEquals(0, g.getPlayer().getY());
    }

    @Test
    public void moveByLimitsTest4() {
        g.moveBy(g.getPlayer(), -1 * g.getMap().getSize() - 10, 0);
        assertEquals(0, g.getPlayer().getX());
    }

    @Test
    public void moveByTest1() {
        for (int i = 0; i < g.getMap().getSize(); i++) {
            g.moveBy(g.getPlayer(), 1, 0);
        }
        assertEquals(g.getMap().getSize() - 1, g.getPlayer().getX());
    }

    @Test
    public void moveByTest2() {
        for (int i = 0; i < g.getMap().getSize(); i++) {
            g.moveBy(g.getPlayer(), 0, 1);
        }
        assertEquals(g.getMap().getSize() - 1, g.getPlayer().getY());
    }

    @Test
    public void moveByTest3() {
        g.moveBy(g.getPlayer(), g.getMap().getSize() - 1, 0);
        assertEquals(g.getMap().getSize() - 1, g.getPlayer().getX());
    }

    @Test
    public void moveByTest4() {
        g.moveBy(g.getPlayer(), 0, g.getMap().getSize() - 1);
        assertEquals(g.getMap().getSize() - 1, g.getPlayer().getY());
    }

    @Test
    public void moveToTest1() {
        g.moveTo(g.getPlayer(), 2, 1);
        assertEquals(g.getPlayer().getX(), 2);
        assertEquals(g.getPlayer().getY(), 1);
    }

    @Test
    public void moveToTest2() {
        g.moveTo(g.getPlayer(), g.getMap().getSize() / 2, g.getMap().getSize() / 2);
        assertEquals(g.getMap().getSize() / 2, g.getPlayer().getX());
        assertEquals(g.getMap().getSize() / 2, g.getPlayer().getY());
    }
}
