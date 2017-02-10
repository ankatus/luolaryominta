package fi.ana.logic;

public class Character {

    private int x;
    private int y;
    private int hp;
    private int type;
    
    public Character(int x, int y, int hp, int type) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.type = type;
    }

    public int getType() {
        return type;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }
}
