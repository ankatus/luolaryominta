
package fi.ana.logic;

/**
 * Interacts with the player when stepped on.
 * @author katantti
 */
public class HealthPack implements Item {

    private int x;
    private int y;

    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
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
