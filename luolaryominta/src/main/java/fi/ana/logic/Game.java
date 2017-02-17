package fi.ana.logic;

import java.awt.event.KeyEvent;
import java.util.*;
import fi.ana.gui.GraphicalUI;

/**
 *
 * Handles all of the player movement logic, starts the UI, and acts as a hub
 * for all the logic of the game. Will be refactored to multiple classes at some
 * point (probably).
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
    }

    /**
     * Starts the game.
     */
    public void start() {
        gui.run();
    }

    /**
     * Moves the game ahead one turn.
     */
    public void proceed(int x, int y) {
        if (checkIfInhabitedCoordinate(player.getX() + x, player.getY() + y)) {
            if (!resolveCombat(player.getX() + x, player.getY() + y)) {
                System.exit(0);
            }
            gui.setTextToHpArea("HP: " + player.getHp());
        }
        moveBy(player, x, y);
        moveMonstersRandomly();
        gui.refresh();
    }

    public Character getPlayer() {
        return player;
    }

    /**
     * Restarts the game, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game1() {
        map = MapMaker.makeSmallMap();
        player = new Character(1, 1, 1, 3);
        monsters = initializeMonsters(1);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    public void game2() {
        map = MapMaker.makeMediumMap();
        player = new Character(1, 1, 2, 3);
        monsters = initializeMonsters(2);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    public void game3() {
        map = MapMaker.makeLargeMap();
        player = new Character(1, 1, 3, 3);
        monsters = initializeMonsters(3);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    public GameMap getMap() {
        return map;
    }

    /**
     * Moves a Character object by the specified distance.
     *
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
     *
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
     *
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
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return
     */
    public boolean checkIfInhabitedCoordinate(int x, int y) {
        if (player != null && player.getX() == x && player.getY() == y) {
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
     *
     * @param howMany amount to be created.
     * @return filled list.
     */
    public List<Character> initializeMonsters(int howMany) {
        List<Character> monsters = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            monsters.add(new Character(-1, -1, 1, 2));
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

    public boolean resolveCombat(int x, int y) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getX() == x && monsters.get(i).getY() == y) {
                if (monsters.get(i).getHp() < player.getHp()) {
                    player.setHP(player.getHp() - monsters.get(i).getHp());
                    monsters.remove(i);
                } else {
                    return false;
                }
            }
        }
        map.setValue(x, y, 0);
        return true;
    }
}
