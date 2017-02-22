package fi.ana.logic;

import java.util.Random;

/**
 * Contains static methods to create a GameMap object.
 *
 */
public final class MapMaker {

    private MapMaker() {
    }

    /**
     * Creates a small map.
     *
     * @return
     */
    public static GameMap makeSmallMap() {
        GameMap map = new GameMap(27);
        fillBorders(map);
        fillRow(map, 7, 2, 15);
        fillColumn(map, 6, 3, 15);
        fillColumn(map, 15, 10, 20);
        fillRow(map, 20, 10, 22);
        return map;
    }

    /**
     * Creates a medium map.
     *
     * @return
     */
    public static GameMap makeMediumMap() {
        GameMap map = new GameMap(35);
        fillBorders(map);
        fillRow(map, 7, 2, 15);
        fillColumn(map, 6, 3, 15);
        fillColumn(map, 20, 6, 10);
        fillColumn(map, 15, 17, 25);
        fillRow(map, 14, 7, 11);
        fillRow(map, 23, 15, 20);
        return map;
    }

    /**
     * Creates a large map.
     *
     * @return
     */
    public static GameMap makeLargeMap() {
        GameMap map = new GameMap(45);
        fillBorders(map);
        fillRow(map, 15, 30, 40);
        fillRow(map, 10, 2, 15);
        fillRow(map, 40, 2, 10);
        fillRow(map, 30, 30, 40);
        fillRow(map, 35, 5, 15);
        fillColumn(map, 15, 3, 15);
        fillColumn(map, 15, 3, 15);
        fillColumn(map, 10, 13, 15);
        fillColumn(map, 10, 25, 35);
        fillColumn(map, 35, 10, 25);
        fillColumn(map, 20, 5, 20);
        return map;
    }

    public static GameMap makeRandomMap() {
        GameMap map = new GameMap(45);
        fillBorders(map);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(45) + 1;
            int r2 = random.nextInt((45 - r + 1) + r);
            int r3 = random.nextInt(45) + 1;
            if (r % 2 == 0) {
                fillColumn(map, r3, r, r2);
            } else {
                fillRow(map, r3, r, r2);
            }
        }
        return map;
    }

    /**
     * Fills the map's borders with walls.
     *
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
     *
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
     *
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
