package fi.ana.logic;

/**
 * Contains the game's map grid in a two-dimensional array.
 * Might be changed to contain an array of coordinate-objects instead.
 */
public class GameMap {

    private int[][] mapValues;

    public GameMap(int size) {
        mapValues = new int[size][size];
    }

    public int getSize() {
        return mapValues.length;
    }
    
    /**
     * Sets the value in the specified position.
     * If the position is not on the map, does nothing.
     * @param x
     * @param y
     * @param value 
     */
    public void setValue(int x, int y, int value) {

        if (value < 0 || value > 4) {
            return;
        } 
        if (!isValidCoordinate(x, y)) {
            return;
        }

        mapValues[y][x] = value;
    }

    /**
     * Returns the value of the specified position.
     * If the position is not on the map, returns -1.
     * @param x
     * @param y
     * @return 
     */
    public int getValue(int x, int y) {
        
        if (!isValidCoordinate(x, y)) {
            return -1;
        }
        
        return mapValues[y][x];
    }

    /**
     * Checks if the specified position is on the map.
     * @param x
     * @param y
     * @return 
     */
    public boolean isValidCoordinate(int x, int y) {

        int lastIndex = mapValues.length - 1;

        if (x > lastIndex || x < 0 || y > lastIndex || y < 0) {
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Sets all values to 0.
     */
    public void clear() {
        for (int i = 0; i < mapValues.length; i++) {
            for (int j = 0; j < mapValues.length; j++) {
                mapValues[i][j] = 0;
            }
        }
    }

}
