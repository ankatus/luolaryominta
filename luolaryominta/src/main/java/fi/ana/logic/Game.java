package fi.ana.logic;

import java.awt.event.KeyEvent;
import java.util.*;
import fi.ana.gui.GraphicalUI;

public class Game {

    private List<Character> monsters;
    private Character player;
    private GameMap map;
    private MapMaker mapMaker;
    private MonsterMover monsterMover;
    private GraphicalUI gui;

    public Game() {
        gui = new GraphicalUI(this);
        monsterMover = new MonsterMover(this);
        mapMaker = new MapMaker();
        map = mapMaker.makeDefaultMap();
        player = new Character(0, 0, 0, 3);
    }

    public void start(int howManyMonsters) {
        gui.run();
        monsters = initializeMonsters(howManyMonsters);
        monsterMover.arrangeMonstersRandomly(monsters);
        moveBy(player, 0, 0);
    }

    public void proceed() {
        moveMonstersRandomly();
        gui.repaintFrame();
    }

    public Character getPlayer() {
        return player;
    }

    public void restart() {
        map = mapMaker.makeDefaultMap();
        monsters = initializeMonsters(5);
        monsterMover.arrangeMonstersRandomly(monsters);
        player = new Character(0, 0, 0, 3);
        moveBy(player, 0, 0);
        gui.repaintFrame();
    }

    public GameMap getMap() {
        return map;
    }

    public void moveBy(Character c, int x, int y) {
        if (!checkIfPassableCoordinate(c.getX() + x, c.getY() + y)) {
            return;
        }
        map.setValue(c.getX(), c.getY(), 0);
        c.setX(c.getX() + x);
        c.setY(c.getY() + y);
        map.setValue(c.getX(), c.getY(), c.getType());
    }

    public void moveTo(Character c, int x, int y) {
        if (!checkIfPassableCoordinate(x, y)) {
            return;
        }
        map.setValue(c.getX(), c.getY(), 0);
        c.setX(x);
        c.setY(y);
        map.setValue(c.getX(), c.getY(), c.getType());
    }

    public boolean checkIfPassableCoordinate(int x, int y) {
        if (!map.isValidCoordinate(x, y)) {
            return false;
        }
        return map.getValue(x, y) != 1;
    }

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

    public List<Character> initializeMonsters(int howMany) {
        List<Character> monsters = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            monsters.add(new Character(0, 0, 5, 2));
        }
        return monsters;
    }

    public void moveMonstersRandomly() {
        for (Character c : monsters) {
            monsterMover.moveRandomly(c);
        }
    }
}
