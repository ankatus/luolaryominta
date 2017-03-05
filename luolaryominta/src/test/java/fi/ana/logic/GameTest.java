package fi.ana.logic;

import fi.ana.pathfinding.Path;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.start();
        game.game1();
    }

    @Test
    public void game1PlayerLocationTest() {
        game.game1();
        assertEquals(1, game.getPlayer().getX());
        assertEquals(1, game.getPlayer().getY());
    }

    @Test
    public void game2PlayerLocationTest2() {
        game.game2();
        assertEquals(1, game.getPlayer().getX());
        assertEquals(1, game.getPlayer().getY());
    }

    @Test
    public void game3PlayerLocationTest3() {
        game.game3();
        assertEquals(1, game.getPlayer().getX());
        assertEquals(1, game.getPlayer().getY());
    }

    @Test
    public void game1MonstersSpawnedTest() {
        game.game1();
        assertEquals(1, game.getMonsters().size());
    }

    @Test
    public void game2MonstersSpawnedTest() {
        game.game2();
        assertEquals(2, game.getMonsters().size());
    }

    @Test
    public void game3MonstersSpawnedTest() {
        game.game3();
        assertEquals(3, game.getMonsters().size());
    }

    @Test
    public void proceedTest1() {
        game.proceed(0, -1);
        assertEquals(1, game.getPlayer().getY());
    }
    
    @Test
    public void proceedTest2() {
        game.proceed(0, 1);
        assertEquals(2, game.getPlayer().getY());
    }

    @Test
    public void checkIfPassableCoordinateTest() {
        game.getMap().setValue(0, 0, true);
        assertTrue(!game.checkIfPassableCoordinate(0, 0));
        game.getMap().setValue(0, 0, false);
        assertTrue(game.checkIfPassableCoordinate(0, 0));
    }

    @Test
    public void checkIfInhabitedCoordinate() {
        game.getPlayer().setX(1);
        game.getPlayer().setY(1);
        assertTrue(game.checkIfInhabitedCoordinate(1, 1));
        game.getPlayer().setX(2);
        game.getPlayer().setY(1);
        assertTrue(!game.checkIfInhabitedCoordinate(1, 1));
    }
    
    @Test
    public void playerMovesOnProceedTest() {
        game.game2();
        int[] player = new int[]{game.getPlayer().getX(), game.getPlayer().getY()};
        game.proceed(1, 1);
        assertEquals(player[0] + 1, game.getPlayer().getX());
        assertEquals(player[1] + 1, game.getPlayer().getY());
    }
    
    @Test
    public void initializeHealthPackTest() {
        game.game1();
        assertNotNull(game.getItems());
    }
    
    @Test
    public void resolveCombatDoesNothingAgainstNotMonster() {
        game.game1();
        assertTrue(game.resolveCombat(0, 0));
    }
    
    @Test
    public void randomGameCreatesRandomMap() {
        game.randomGame();
        GameMap map1 = game.getMap();
        game.randomGame();
        GameMap map2 = game.getMap();
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (map1.getValue(x, y) != map2.getValue(x, y)) {
                    assertTrue(true);
                    return;
                }
            }
        }
        assertTrue(false);
    }
    
    @Test
    public void isPositionVisibleTest() {
        assertTrue(game.isPositionVisible(3, 3));
        assertFalse(game.isPositionVisible(9, 9));
    }
    
    @Test
    public void turnCountTest() {
        assertEquals(0, game.getTurnCount());
        game.proceed(0, 0);
        assertEquals(1, game.getTurnCount());
        game.proceed(0, 0);
        assertEquals(2, game.getTurnCount());
    }
    
    @Test
    public void resolveStackedMonsterAndPlayerReturnsTrueWhenNotStacked() {
        assertTrue(game.resolveStackedMonsterAndPlayer());
    }
    
    @Test
    public void checkIfOnGoalReturnsFalseWhenNotOnGoal() {
        assertFalse(game.checkIfOnGoal());
    }
    
    @Test
    public void monsterAggroTest() {
        boolean inRange = false;
        Path path1 = game.getMonsters().get(0).getPath();
        int range = Math.abs(game.getMonsters().get(0).getX() - game.getPlayer().getY()) + Math.abs(game.getMonsters().get(0).getY() - game.getPlayer().getY());
        if (range < 6) {
            assertNotEquals(path1, game.getMonsters().get(0).getPath());
        } else {
            assertEquals(path1, game.getMonsters().get(0).getPath());
        }
    }   
    
}
