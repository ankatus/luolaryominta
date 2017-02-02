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
        move(player, 0, 0); //update player to map
    }

    public GameMap getMap() {
        return map;
    }

    public void movePlayer(String direction) {
        direction = direction.toLowerCase();
        if (direction.equals("w")) {
            if (checkIfPassableCoordinate(player.getX(), player.getY() - 1)) {
                move(player, 0, -1);
            }
        } else if (direction.equals("d")) {
            if (checkIfPassableCoordinate(player.getX() + 1, player.getY())) {
                move(player, 1, 0);
            }
        } else if (direction.equals("s")) {
            if (checkIfPassableCoordinate(player.getX(), player.getY() + 1)) {
                move(player, 0, 1);
            }
        } else if (direction.equals("a")) {
            if (checkIfPassableCoordinate(player.getX() - 1, player.getY())) {
                move(player, -1, 0);
            }
        }
    }

    private void move(Character c, int x, int y) {
        map.setValue(c.getX(), c.getY(), 0);
        c.setX(c.getX() + x);
        c.setY(c.getY() + y);
        map.setValue(c.getX(), c.getY(), c.getType());
    }
    
    private boolean checkIfPassableCoordinate(int x, int y) {
        if (!map.isValidCoordinate(x, y)) {
            return false;
        }
        return map.getValue(x, y) != 1;
    }
}
