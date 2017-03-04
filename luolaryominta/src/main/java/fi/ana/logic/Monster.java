package fi.ana.logic;
import fi.ana.pathfinding.*;

public class Monster extends Entity {

    private int hp;
    private Path path;
    
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
