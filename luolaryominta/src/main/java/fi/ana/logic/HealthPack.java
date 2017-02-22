/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.logic;

/**
 *
 * @author katantti
 */
public class HealthPack implements Item {

    private int x;
    private int y;

    public HealthPack(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean interact(GameCharacter c) {
        c.setHP(c.getHp() + 1);
        return true;
    }

}
