package fi.ana.logic;

import java.util.*;

/**
 * Contains static methods to create a GameMap object.
 *
 */
public final class MapMaker {

    private MapMaker() {
    }

    public static GameMap makeDefaultMap() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        GameMap map = new GameMap(25, l);
        fillRow(map, 7, 2, 15, 1);
        fillColumn(map, 6, 3, 15, 1);
        return map;
    }

    public static GameMap makeGame1Map() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        GameMap map = new GameMap(27, l);
        fillBorders(map);
        fillRow(map, 7, 2, 15, 1);
        fillColumn(map, 6, 3, 15, 1);
        return map;
    }

    public static GameMap makeGame2Map() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        GameMap map = new GameMap(35, l);
        fillBorders(map);
        fillRow(map, 7, 2, 15, 1);
        fillColumn(map, 6, 3, 15, 1);
        return map;
    }

    public static GameMap makeGame3Map() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        GameMap map = new GameMap(45, l);
        fillBorders(map);
        fillRow(map, 7, 2, 15, 1);
        fillColumn(map, 6, 3, 15, 1);
        return map;
    }

    private static void fillBorders(GameMap map) {
        fillRow(map, 0, 0, map.getSize() - 1, 1);
        fillRow(map, map.getSize() - 1, 0, map.getSize() - 1, 1);
        fillColumn(map, 0, 0, map.getSize() - 1, 1);
        fillColumn(map, map.getSize() - 1, 0, map.getSize() - 1, 1);
    }

    private static void fillRow(GameMap map, int row, int start, int end, int value) {
        for (int i = start; i <= end; i++) {
            map.setValue(i, row, value);
        }
    }

    private static void fillColumn(GameMap map, int column, int top, int bottom, int value) {
        for (int i = top; i <= bottom; i++) {
            map.setValue(column, i, value);
        }
    }

}
