package fi.ana.logic;
import fi.ana.pathfinding.*;
/**
 * Stores all the information related to a character.
 * 
 */
public class GameCharacter {

    private int x;
    private int y;
    private int hp;
    private int type;
    private Path path;
    
    /**
     * Holds data associated with the characters in-game.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param hp hp.
     * @param type type.
     */
    public GameCharacter(int x, int y, int hp, int type) {
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
    
    public Path getPath() {
        return path;
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
    
    public void setPath(Path path) {
        this.path = path;
    }
}
