/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

import fi.ana.pathfinding.Path;
import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katantti
 */
public class EntityMoverTest {

    private EntityMover entityMover;
    private Game game;

    public EntityMoverTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
        entityMover = new EntityMover(game);
        game.start();
        game.game1();
    }

    @Test
    public void moveRandomlyTest() {
        int x = game.getMonsters().get(0).getX();
        int y = game.getMonsters().get(0).getY();
        entityMover.moveRandomly(game.getMonsters().get(0));

        if (x != game.getMonsters().get(0).getX()) {
            assertTrue(true);
        } else if (y != game.getMonsters().get(0).getY()) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }
    
    @Test
    public void moveToTest() {
        entityMover.moveTo(game.getPlayer(), 2, 1);
        assertEquals(2, game.getPlayer().getX());
        assertEquals(1, game.getPlayer().getY());
    }
}
