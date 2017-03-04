/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

import fi.ana.pathfinding.Path;
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
public class MonsterTest {
    
    private Monster monster;
    
    public MonsterTest() {
    }
    
    @Before
    public void setUp() {
        monster = new Monster(0, 0, 0, null);
    }
    
    @Test
    public void setGetXTest() {
        monster.setX(10);
        assertEquals(10, monster.getX());
    }
    
    @Test
    public void setGetYTest() {
        monster.setY(10);
        assertEquals(10, monster.getY());
    }
    
    @Test
    public void setGetHpTest() {
        monster.setHp(10);
        assertEquals(10, monster.getHp());
    }
    
    @Test
    public void setGetPathTest() {
        Path path = new Path(null);
        monster.setPath(path);
        assertEquals(path, monster.getPath());
    }

    
}
