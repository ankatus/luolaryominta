package fi.ana.pathfinding;

import java.util.*;
import fi.ana.logic.*;

/**
 * Contains a list of PathfinderCoordinate objects.
 * @author katantti
 */
public class Path {

    private List<PathfinderCoordinate> path;

    /**
     * Constructor.
     * @param path list of PathfinderCoordinate-objects.
     */
    public Path(List<PathfinderCoordinate> path) {
        this.path = path;
    }

    /**
     * Adds a coordinate to the list's beginning.
     * @param pc coordinate to be added.
     */
    public void add(PathfinderCoordinate pc) {
        add(pc, 0);
    }

    /**
     * Adds a coordinate to a specified index in the list.
     * @param pc coordinate to be added.
     * @param index index.
     */
    public void add(PathfinderCoordinate pc, int index) {
        path.add(index, pc);
    }

    /**
     * Removes the object at the specified index.
     * @param index index.
     */
    public void remove(int index) {
        path.remove(index);
    }
    /**
     * Returns the object at the specified index.
     * @param index index.
     * @return PathfinderCoordinate object.
     */
    public PathfinderCoordinate getPathfinderCoordinate(int index) {
        return path.get(index);
    }

    /**
     * Returns the list containing the coordinates.
     * @return list.
     */
    public List<PathfinderCoordinate> getPath() {
        return path;
    }

    @Override
    public String toString() {
        String s = "";
        for (PathfinderCoordinate pc : path) {
            s += pc.toString();
        }
        return s;
    }
}
