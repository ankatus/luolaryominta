package fi.ana.logic;
import fi.ana.pathfinding.*;

/**
 * Entity containing fields for hp and path.
 * @author katantti
 */
public class Monster extends Entity {

    private int hp;
    private Path path;
    
    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param hp amount of hp.
     * @param path path to follow.
     */
    public Monster(int x, int y, int hp, Path path) {
        super(x, y);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
    
    public Path getPath() {
        return path;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public void setPath(Path path) {
        this.path = path;
    }

}
