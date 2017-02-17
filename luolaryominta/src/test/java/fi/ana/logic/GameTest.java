package fi.ana.logic;

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
        int sum = 0;
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (game.getMap().getValue(x, y) == 2) {
                    sum++;
                }
            }
        }
        assertEquals(1, sum);
    }

    @Test
    public void game2MonstersSpawnedTest() {
        game.game2();
        int sum = 0;
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (game.getMap().getValue(x, y) == 2) {
                    sum++;
                }
            }
        }
        assertEquals(2, sum);
    }

    @Test
    public void game3MonstersSpawnedTest() {
        game.game3();
        int sum = 0;
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (game.getMap().getValue(x, y) == 2) {
                    sum++;
                }
            }
        }
        assertEquals(3, sum);
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
    public void initializeMonstersTest1() {
        List<Character> list = game.initializeMonsters(5);
        assertEquals(5, list.size());
    }

    @Test
    public void initializeMonstersTest2() {
        List<Character> list = game.initializeMonsters(5);
        for (Character c : list) {
            assertEquals(-1, c.getX());
            assertEquals(-1, c.getY());
            assertEquals(2, c.getType());
        }
    }

    @Test
    public void moveByLimitsTest1() {
        game.moveBy(game.getPlayer(), game.getMap().getSize() + 10, 0);
        assertEquals(1, game.getPlayer().getX());

    }

    @Test
    public void moveByLimitsTest2() {
        game.moveBy(game.getPlayer(), 0, game.getMap().getSize() + 10);
        assertEquals(1, game.getPlayer().getY());
    }

    @Test
    public void moveByLimitsTest3() {
        game.moveBy(game.getPlayer(), 0, -1 * game.getMap().getSize() - 10);
        assertEquals(1, game.getPlayer().getY());
    }

    @Test
    public void moveByLimitsTest4() {
        game.moveBy(game.getPlayer(), -1 * game.getMap().getSize() - 10, 0);
        assertEquals(1, game.getPlayer().getX());
    }

    @Test
    public void moveByTest1() {
        for (int i = 0; i < game.getMap().getSize(); i++) {
            game.moveBy(game.getPlayer(), 1, 0);
        }
        assertEquals(game.getMap().getSize() - 2, game.getPlayer().getX());
    }

    @Test
    public void moveByTest2() {
        for (int i = 0; i < game.getMap().getSize(); i++) {
            game.moveBy(game.getPlayer(), 0, 1);
        }
        assertEquals(game.getMap().getSize() - 2, game.getPlayer().getY());
    }

    @Test
    public void moveByTest3() {
        game.moveBy(game.getPlayer(), game.getMap().getSize() - 3, 0);
        assertEquals(game.getMap().getSize() - 2, game.getPlayer().getX());
    }

    @Test
    public void moveByTest4() {
        game.moveBy(game.getPlayer(), 0, game.getMap().getSize() - 3);
        assertEquals(game.getMap().getSize() - 2, game.getPlayer().getY());
    }

    @Test
    public void moveToTest1() {
        game.moveTo(game.getPlayer(), 2, 1);
        assertEquals(game.getPlayer().getX(), 2);
        assertEquals(game.getPlayer().getY(), 1);
    }

    @Test
    public void moveToTest2() {
        game.moveTo(game.getPlayer(), game.getMap().getSize() / 2, game.getMap().getSize() / 2);
        assertEquals(game.getMap().getSize() / 2, game.getPlayer().getX());
        assertEquals(game.getMap().getSize() / 2, game.getPlayer().getY());
    }

    @Test
    public void checkIfPassableCoordinateTest() {
        game.getMap().setValue(0, 0, 1);
        assertTrue(!game.checkIfPassableCoordinate(0, 0));
        game.getMap().setValue(0, 0, 0);
        assertTrue(game.checkIfPassableCoordinate(0, 0));
    }

    @Test
    public void checkIfInhabitedCoordinate() {
        game.moveTo(game.getPlayer(), 1, 1);
        assertTrue(game.checkIfInhabitedCoordinate(1, 1));
        game.moveTo(game.getPlayer(), 2, 1);
        assertTrue(!game.checkIfInhabitedCoordinate(1, 1));
    }
}
