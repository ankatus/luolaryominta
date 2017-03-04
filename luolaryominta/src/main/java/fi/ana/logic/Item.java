
package fi.ana.logic;

/**
 *
 * @author katantti
 */
public abstract class Item extends Entity {
    
    public Item(int x, int y) {
        super(x, y);
    }
    
    public abstract void interact(PlayerCharacter player);
}
