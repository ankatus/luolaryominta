package fi.ana.logic;

import java.awt.event.KeyEvent;
import java.util.*;
import fi.ana.gui.GraphicalUI;

/**
 * 
 * Handles all of the player movement logic, starts the UI, and acts as a hub for all the logic of the game.
 * Will be refactored to multiple classes at some point (probably).
 */

public class Game {

    private List<Character> monsters;
    private Character player;
    private GameMap map;
    private MonsterMover monsterMover;
    private GraphicalUI gui;

    public Game() {
        gui = new GraphicalUI(this);
        monsters = new ArrayList<>();
        monsterMover = new MonsterMover(this);
        map = MapMaker.makeDefaultMap();
        player = new Character(0, 0, 0, 3);
    }
    
    /**
     * Starts the game.
     * @param howManyMonsters How many monsters will be spawned.
     */
    public void start(int howManyMonsters) {
        gui.run();
        monsters = initializeMonsters(howManyMonsters);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
    }

    /**
     * Moves the game ahead one turn.
     */
    public void proceed() {
        moveMonstersRandomly();
        gui.repaintFrame();
    }

    public Character getPlayer() {
        return player;
    }

    /**
     * Restarts the game, setting all values back to default and re-randomising monster locations.
     */
    public void restart() {
        map = MapMaker.makeDefaultMap();
        monsters = initializeMonsters(5);
        monsterMover.arrangeMonstersRandomly(monsters);
        player = new Character(0, 0, 0, 3);
        moveBy(player, 0, 0);
        gui.repaintFrame();
    }

    public GameMap getMap() {
        return map;
    }

    /**
     * Moves a Character object by the specified distance.
     * @param c Character to be moved.
     * @param x Distance to be moved laterally.
     * @param y Distance to be moved vertically.
     */
    public void moveBy(Character c, int x, int y) {
        if (!checkIfPassableCoordinate(c.getX() + x, c.getY() + y)) {
            return;
        }
        map.setValue(c.getX(), c.getY(), 0);
        c.setX(c.getX() + x);
        c.setY(c.getY() + y);
        map.setValue(c.getX(), c.getY(), c.getType());
    }

    /**
     * Moves a Character object to the specified coordinate.
     * @param c Character to be moved.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void moveTo(Character c, int x, int y) {
        if (!checkIfPassableCoordinate(x, y)) {
            return;
        }
        map.setValue(c.getX(), c.getY(), 0);
        c.setX(x);
        c.setY(y);
        map.setValue(c.getX(), c.getY(), c.getType());
    }

    /**
     * Checks if the specified location is 1) on the map 2) not obstructed.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return 
     */
    public boolean checkIfPassableCoordinate(int x, int y) {
        if (!map.isValidCoordinate(x, y)) {
            return false;
        }
        return map.getValue(x, y) != 1;
    }

    /**
     * Checks if the specified location is currently inhabited by a Character.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return 
     */
    public boolean checkIfInhabitedCoordinate(int x, int y) {
        if (player.getX() == x && player.getY() == y) {
            return true;
        }
        for (Character c : monsters) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fills the monsters list with Character objects.
     * @param howMany amount to be created.
     * @return filled list.
     */
    public List<Character> initializeMonsters(int howMany) {
        List<Character> monsters = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            monsters.add(new Character(0, 0, 5, 2));
        }
        return monsters;
    }

    /**
     * Calls the MonsterMover class's moveRandomly()-method for every monster.
     */
    public void moveMonstersRandomly() {
        for (Character c : monsters) {
            monsterMover.moveRandomly(c);
        }
    }
}
