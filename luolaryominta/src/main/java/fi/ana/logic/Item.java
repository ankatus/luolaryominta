
package fi.ana.logic;

/**
 * Class for items that interact with the player.
 * @author katantti
 */
public abstract class Item extends Entity {
    
    /**
     * Constuctor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public Item(int x, int y) {
        super(x, y);
    }
    
    /**
     * Interacts with the player character in some way.
     * @param player player character to interact with.
     */
    public abstract void interact(PlayerCharacter player);
}
