/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

/**
 * Abstract class representing a basic entity with a position.
 * @author katantti
 */
public abstract class Entity {
    
    private int x;
    private int y;
    
    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
