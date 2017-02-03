package fi.ana.logic;

import java.util.*;

public class Game {

    private List<Character> monsters;
    private Character player;
    private GameMap map;
    private MapMaker mapMaker;

    public Game() {
        mapMaker = new MapMaker();
        map = mapMaker.makeDefaultMap();
        player = new Character(0, 0, 5, 3);
        moveBy(player, 0, 0); //update player to map
    }

    public Character getPlayer() {
        return player;
    }

    public GameMap getMap() {
        return map;
    }

    //probably make a class for this
    public void interpretCommand(String direction) {
        direction = direction.toLowerCase();
        switch (direction) {
            case "w":
                moveBy(player, 0, -1);
                break;
            case "d":
                moveBy(player, 1, 0);
                break;
            case "s":
                moveBy(player, 0, 1);
                break;
            case "a":
                moveBy(player, -1, 0);
                break;
            default:
                break;
        }
    }

    public void moveBy(Character c, int x, int y) {
        if (!checkIfPassableCoordinate(player.getX() + x, player.getY() + y)) {
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
}
