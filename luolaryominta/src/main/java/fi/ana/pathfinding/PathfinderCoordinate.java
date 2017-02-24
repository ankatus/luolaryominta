/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.pathfinding;

/**
 * Class for containing a single coordinate to be used in pathfinding.
 * @author katantti
 */
public class PathfinderCoordinate {

    private int x;
    private int y;
    private int fValue;
    private int gValue;
    private int hValue;
    private PathfinderCoordinate parent;

    public PathfinderCoordinate(int x, int y, int fValue, int gValue, int hValue, PathfinderCoordinate parent) {
        this.x = x;
        this.y = y;
        this.fValue = fValue;
        this.gValue = gValue;
        this.hValue = hValue;
        this.parent = parent;
    }

    public PathfinderCoordinate(int x, int y, PathfinderCoordinate parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFValue() {
        return fValue;
    }

    public int getGValue() {
        return gValue;
    }

    public int getHValue() {
        return hValue;
    }

    public PathfinderCoordinate getParent() {
        return parent;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFValue(int fValue) {
        this.fValue = fValue;
    }

    public void setGValue(int gValue) {
        this.gValue = gValue;
    }

    public void setHValue(int hValue) {
        this.hValue = hValue;
    }

    public void setParent(PathfinderCoordinate parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PathfinderCoordinate other = (PathfinderCoordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
