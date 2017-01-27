package fi.ana.logic;

public abstract class Character {

    private int x;
    private int y;
    private int hp;
    private boolean alive;

    public Character(int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        alive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean takeDamage(int value) {
        if (value < 0) {
            return false;
        }
        if (value > hp) {
            hp = 0;
            return true;
        }
        hp -= value;
        return true;
    }

    public boolean move() {
        return false; //TODO
    }

    public boolean isAlive() {
        return alive;
    }

}
