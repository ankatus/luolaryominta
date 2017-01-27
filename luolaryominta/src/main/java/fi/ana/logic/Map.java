package fi.ana.logic;

import java.util.*;

public class Map {

    private int[][] mapValues;
    private List<Integer> acceptableValues;

    public Map(int dimension, List<Integer> acceptableValues) {
        mapValues = new int[dimension][dimension];
        this.acceptableValues = acceptableValues;
    }

    public void setValue(int x, int y, int value) {

        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("Coordinate out of bounds");
        }
        
        if (!acceptableValues.contains(value)) {
            throw new IllegalArgumentException("Value not acceptable");
        }

        mapValues[y][x] = value;
    }

    public int getValue(int x, int y) {
        
        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("Coordinate out of bounds");
        }
        
        return mapValues[y][x];
    }

    private boolean isValidCoordinate(int x, int y) {

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
