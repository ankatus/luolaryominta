package fi.ana.logic;
import fi.ana.pathfinding.*;
/**
 * Stores all the information related to a character.
 * 
 */
public class PlayerCharacter extends Entity {

    private int hp;
    
    /**
     * Holds data associated with the characters in-game.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param hp hp.
     */
    public PlayerCharacter(int x, int y, int hp) {
        super(x, y);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

}
