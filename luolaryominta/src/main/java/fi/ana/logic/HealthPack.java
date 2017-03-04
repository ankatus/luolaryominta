
package fi.ana.logic;

/**
 * Interacts with the player when stepped on.
 * @author katantti
 */
public class HealthPack extends Item {


    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public HealthPack(int x, int y) {
        super(x, y);
    }

    @Override
    public void interact(PlayerCharacter player) {
        player.setHp(player.getHp() + 1);
    }

}
