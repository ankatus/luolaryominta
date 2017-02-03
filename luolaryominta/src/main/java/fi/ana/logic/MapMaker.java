package fi.ana.logic;

import java.util.*;

public class MapMaker {

    public MapMaker() {
    }

    public GameMap makeDefaultMap() {
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        GameMap map = new GameMap(25, l);
        populateRow(map, 7, 2, 15, 1);
        populateColumn(map, 6, 3, 15, 1);
        return map;
    }

    private void populateRow(GameMap map, int row, int start, int end, int value) {
        for (int i = start; i <= end; i++) {
            map.setValue(i, row, value);
        }
    }

    private void populateColumn(GameMap map, int column, int top, int bottom, int value) {
        for (int i = top; i <= bottom; i++) {
            map.setValue(column, i, value);
        }
    }

}
