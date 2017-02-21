package fi.ana.logic;


/**
 * Contains static methods to create a GameMap object.
 *
 */
public final class MapMaker {

    private MapMaker() {
    }

    /**
     * Creates a small map.
     * @return 
     */
    public static GameMap makeSmallMap() {
        GameMap map = new GameMap(27);
        fillBorders(map);
        fillRow(map, 7, 2, 15);
        fillColumn(map, 6, 3, 15);
        return map;
    }

    /**
     * Creates a medium map.
     * @return 
     */
    public static GameMap makeMediumMap() {
        GameMap map = new GameMap(35);
        fillBorders(map);
        fillRow(map, 7, 2, 15);
        fillColumn(map, 6, 3, 15);
        return map;
    }

    /**
     * Creates a large map.
     * @return 
     */
    public static GameMap makeLargeMap() {
        GameMap map = new GameMap(45);
        fillBorders(map);
        fillRow(map, 7, 2, 15);
        fillColumn(map, 6, 3, 15);
        return map;
    }

    /**
     * Fills the map's borders with walls.
     * @param map 
     */
    private static void fillBorders(GameMap map) {
        fillRow(map, 0, 0, map.getSize() - 1);
        fillRow(map, map.getSize() - 1, 0, map.getSize() - 1);
        fillColumn(map, 0, 0, map.getSize() - 1);
        fillColumn(map, map.getSize() - 1, 0, map.getSize() - 1);
    }

    /**
     * Changes a row of values on the map to the specified value.
     * @param map
     * @param row
     * @param start
     * @param end
     * @param value 
     */
    private static void fillRow(GameMap map, int row, int start, int end) {
        for (int i = start; i <= end; i++) {
            map.setValue(i, row, true);
        }
    }

    /**
     * Changes a column of values on the map to the specified value.
     * @param map
     * @param row
     * @param start
     * @param end
     * @param value 
     */
    private static void fillColumn(GameMap map, int column, int top, int bottom) {
        for (int i = top; i <= bottom; i++) {
            map.setValue(column, i, true);
        }
    }

}
