
package fi.ana.logic;


public interface Item {
    
    public int getX();
    
    public int getY();
    
    public void setX(int x);
    
    public void setY(int y);
    
    public boolean interact(GameCharacter c);
}
