package fi.ana.logic;

/**
 * Contains the game's map grid in a two-dimensional array.
 * 
 */
public class GameMap {

    private boolean[][] mapValues;

    /**
     * Constructor.
     * @param size map side length. 
     */
    public GameMap(int size) {
        mapValues = new boolean[size][size];
    }

    public int getSize() {
        return mapValues.length;
    }
    
    /**
     * Sets the value in the specified position.
     * If the position is not on the map, does nothing.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param wall value.
     */
    public void setValue(int x, int y, boolean wall) {
        
        if (!isValidCoordinate(x, y)) {
            return;
        }

        mapValues[y][x] = wall;
    }

    /**
     * Returns the value of the specified position.
     * If the position is not on the map, returns false.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true or false.
     */
    public boolean getValue(int x, int y) {
        
        if (!isValidCoordinate(x, y)) {
            return false;
        }
        
        return mapValues[y][x];
    }

    /**
     * Checks if the specified position is on the map.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true or false.
     */
    public boolean isValidCoordinate(int x, int y) {

        int lastIndex = mapValues.length - 1;

        if (x > lastIndex || x < 0 || y > lastIndex || y < 0) {
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Sets all values to false.
     */
    public void clear() {
        for (int i = 0; i < mapValues.length; i++) {
            for (int j = 0; j < mapValues.length; j++) {
                mapValues[i][j] = false;
            }
        }
    }

}
