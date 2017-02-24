
package fi.ana.logic;

/**
 * Interface for non-moving entities that can interact with the player.
 * @author katantti
 */
public interface Item {
    
    public int getX();
    
    public int getY();
    
    public void setX(int x);
    
    public void setY(int y);
    
    /**
     * Interacts with the player in some way.
     * @param c character to interact with.
     * @return true or false.
     */
    public boolean interact(GameCharacter c);
}
