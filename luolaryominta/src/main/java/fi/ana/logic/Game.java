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

    private List<GameCharacter> monsters;
    private GameCharacter player;
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
        updateMonsterPaths();
        moveMonsters();
        gui.refresh();
    }

    public GameCharacter getPlayer() {
        return player;
    }
    
    public List<GameCharacter> getMonsters() {
        return monsters;
    }

    /**
     * Restarts the game, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game1() {
        map = MapMaker.makeSmallMap();
        player = new GameCharacter(1, 1, 1, 3);
        monsters = initializeMonsters(1);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    public void game2() {
        map = MapMaker.makeMediumMap();
        player = new GameCharacter(1, 1, 2, 3);
        monsters = initializeMonsters(2);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    public void game3() {
        map = MapMaker.makeLargeMap();
        player = new GameCharacter(1, 1, 3, 3);
        monsters = initializeMonsters(3);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
        gui.startGame();
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
    public void moveBy(GameCharacter c, int x, int y) {
        if (!checkIfPassableCoordinate(c.getX() + x, c.getY() + y)) {
            return;
        }
        c.setX(c.getX() + x);
        c.setY(c.getY() + y);
    }

    /**
     * Moves a Character object to the specified coordinate.
     *
     * @param c Character to be moved.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void moveTo(GameCharacter c, int x, int y) {
        if (!checkIfPassableCoordinate(x, y)) {
            return;
        }
        c.setX(x);
        c.setY(y);
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
        return !map.getValue(x, y);
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
        for (GameCharacter c : monsters) {
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
    public List<GameCharacter> initializeMonsters(int howMany) {
        List<GameCharacter> monsters = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            monsters.add(new GameCharacter(-1, -1, 1, 2));
        }
        return monsters;
    }

    /**
     * Calls the MonsterMover class's moveRandomly()-method for every monster.
     */
    public void moveMonstersRandomly() {
        for (GameCharacter c : monsters) {
            monsterMover.moveRandomly(c);
        }
    }
    
    public void updateMonsterPaths() {
        for (GameCharacter monster : monsters) {
            monsterMover.getNewPath(monster);
        }
    }
    
    public void moveMonsters() {
        for (GameCharacter monster : monsters) {
            monsterMover.moveOnPath(monster);
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
        return true;
    }
}
