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
public class EntitySpawnerTest {
    
    Game game;
    EntitySpawner entitySpawner;
    
    public EntitySpawnerTest() {
    }
    
    @Before
    public void setUp() {
        game = new Game();
        entitySpawner = new EntitySpawner(game);
    }
    
    @Test
    public void spawnHealthPacksTest() {
        game.getItems().addAll(entitySpawner.spawnHealthPacks(5));
        assertEquals(5, game.getItems().size());
    }
    
    @Test
    public void spawnMonstersTest() {
        game.getMonsters().addAll(entitySpawner.spawnMonsters(5));
        assertEquals(5, game.getMonsters().size());
    }
    
    @Test
    public void spawnGoalTest() {
        game.game1();
        assertNotNull(game.getGoal());
    }
    
    

}
