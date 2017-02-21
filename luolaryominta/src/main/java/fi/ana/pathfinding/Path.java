package fi.ana.pathfinding;

import java.util.*;
import fi.ana.logic.*;

public class Path {

    private List<PathfinderCoordinate> path;

    public Path(List<PathfinderCoordinate> path) {
        this.path = path;
    }

    public void add(PathfinderCoordinate pc) {
        add(pc, 0);
    }

    public void add(PathfinderCoordinate pc, int index) {
        path.add(index, pc);
    }

    public void remove(int index) {
        path.remove(index);
    }

    public PathfinderCoordinate getPathfinderCoordinate(int index) {
        return path.get(index);
    }

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
