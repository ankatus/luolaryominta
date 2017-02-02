package fi.ana.logic;

import java.util.*;

public class GameMap {

    private int[][] mapValues;
    private List<Integer> acceptableValues;

    public GameMap(int size, List<Integer> acceptableValues) {
        mapValues = new int[size][size];
        this.acceptableValues = acceptableValues;
    }

    public int getSize() {
        return mapValues.length;
    }
    
    public void setValue(int x, int y, int value) {

        if (!isValidCoordinate(x, y)) {
            return;
        }
        
        if (!acceptableValues.contains(value)) {
            return;
        }

        mapValues[y][x] = value;
    }

    public int getValue(int x, int y) {
        
        if (!isValidCoordinate(x, y)) {
            return -1;
        }
        
        return mapValues[y][x];
    }

    public boolean isValidCoordinate(int x, int y) {

        int lastIndex = mapValues.length - 1;

        if (x > lastIndex || x < 0 || y > lastIndex || y < 0) {
            return false;
        }
        
        return true;
    }
    
    public void clear() {
        for (int i = 0; i < mapValues.length; i++) {
            for (int j = 0; j < mapValues.length; j++) {
                mapValues[i][j] = 0;
            }
        }
    }

}
