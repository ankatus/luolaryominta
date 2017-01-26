package fi.ana.logic;

import java.util.*;

public class Map {

    private int[][] mapValues;
    private List<Integer> acceptableValues;

    public Map(int dimension, List<Integer> acceptableValues) {
        mapValues = new int[dimension][dimension];
        this.acceptableValues = acceptableValues;
    }

    public boolean setValue(int x, int y, int value) {

        if (!isValidCoordinate(x, y)) {
            return false;
        }
        
        if (!acceptableValues.contains(value)) {
            return false;
        }

        mapValues[y][x] = value;
        return true;
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

}
