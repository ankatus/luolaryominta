package fi.ana.pathfinding;

import fi.ana.logic.*;
import java.util.*;

public class Pathfinder {

    public static Path findPath(PathfinderCoordinate start, PathfinderCoordinate end, GameMap map) {
        ArrayList<PathfinderCoordinate> openList = new ArrayList();
        ArrayList<PathfinderCoordinate> closedList = new ArrayList();
        PathfinderCoordinate current = start;
        openList.add(current);
        boolean noRoute = false;

        while (true) {

            if (current.equals(end)) {
                closedList.add(current);
                break;
            }

            for (int y = current.getY() - 1; y <= current.getY() + 1; y++) {

                for (int x = current.getX() - 1; x <= current.getX() + 1; x++) {
                    PathfinderCoordinate pc = new PathfinderCoordinate(x, y, current);
                    if (pc.equals(current)) {
                        continue;
                    }
                    if (!map.isValidCoordinate(x, y)) {
                        continue;
                    }
                    if (map.getValue(x, y) == true) {
                        continue;
                    }
                    if (closedList.contains(pc)) {
                        continue;
                    }

                    if (openList.contains(pc)) {

                        if (gCost(current, pc) < searchFromListByObject(pc, openList).getGValue()) {

                            PathfinderCoordinate inOpenList = searchFromListByObject(pc, openList);
                            inOpenList.setParent(current);
                            inOpenList.setGValue(gCost(current, inOpenList));
                            inOpenList.setFValue(inOpenList.getGValue() + inOpenList.getHValue());

                        }

                    } else {

                        pc.setFValue(gCost(current, pc) + hCost(pc, end));
                        pc.setGValue(gCost(current, pc));
                        pc.setHValue(hCost(pc, end));
                        openList.add(pc);

                    }
                }
            }

            openList.remove(current);
            closedList.add(current);
            current = findLowestFCostCoordinate(openList);
            openList.add(current);

        }

        Path path = new Path(new ArrayList());
        PathfinderCoordinate addToPath = closedList.get(closedList.size() - 1);
        int index = closedList.size() - 1;
        PathfinderCoordinate[] list = new PathfinderCoordinate[closedList.size()];
        while (true) {
            list[index] = addToPath;
            if (addToPath.getParent() == null) {
                break;
            }
            addToPath = addToPath.getParent();
            index--;
        }
        for (int i = list.length - 1; i >= 0; i--) {
            path.add(list[i]);
        }
        return path;

    }

    private static int gCost(PathfinderCoordinate a, PathfinderCoordinate b) {
        if (a.getX() == b.getX()) {
            return 10;
        }
        if (a.getY() == b.getY()) {
            return 10;
        }
        return 14;
    }

    private static int hCost(PathfinderCoordinate a, PathfinderCoordinate b) {
        return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY())) * 10;
    }

    private static PathfinderCoordinate findLowestFCostCoordinate(List<PathfinderCoordinate> list) {
        PathfinderCoordinate lowest = list.get(0);
        for (PathfinderCoordinate pc : list) {
            if (pc.getFValue() < lowest.getFValue()) {
                lowest = pc;
            }
        }
        return lowest;
    }

    private static PathfinderCoordinate searchFromListByObject(PathfinderCoordinate pc, List<PathfinderCoordinate> list) {
        for (PathfinderCoordinate i : list) {
            if (i.equals(pc)) {
                return i;
            }
        }
        return null;
    }

}
