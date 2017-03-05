package fi.ana.logic;
import fi.ana.pathfinding.*;
/**
 * Entity containing a field for hp. Represents the player.
 * 
 */
public class PlayerCharacter extends Entity {

    private int hp;
    
    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param hp amount of hp.
     */
    public PlayerCharacter(int x, int y, int hp) {
        super(x, y);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
