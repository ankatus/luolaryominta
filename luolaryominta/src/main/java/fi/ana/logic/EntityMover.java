package fi.ana.logic;

import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.Random;
import java.util.List;
import fi.ana.pathfinding.*;
import java.util.ArrayList;

/**
 * Handles Entity moving.
 *
 */
public class EntityMover {

    private Game game;
    private Random r;

    /**
     * Constructor.
     * @param game Game-object to get information from.
     */
    public EntityMover(Game game) {
        r = new Random();
        this.game = game;
    }

    /**
     * Moves a monster in a random position around its current position.
     *
     * @param m monster to be moved.
     */
    public void moveRandomly(Monster m) {
        int x;
        int y;
        while (true) {
            x = r.nextInt(3) + m.getX() - 1;
            y = r.nextInt(3) + m.getY() - 1;
            if (game.checkIfPassableCoordinate(x, y)) {
                if (!game.checkIfInhabitedCoordinate(x, y)) {
                    moveTo(m, x, y);
                    break;
                }
            }
        }
    }

    /**
     * Moves a monster along it's path.
     * @param monster monster to be moved.
     */
    public void moveOnPath(Monster monster) {
        if (monster.getPath() != null) {
            int x = monster.getPath().getPathfinderCoordinate(0).getX();
            int y = monster.getPath().getPathfinderCoordinate(0).getY();
            if (game.checkIfInhabitedCoordinate(x, y) && game.getPlayer().getX() != x && game.getPlayer().getY() != y) {
                return;
            }
            moveTo(monster, monster.getPath().getPathfinderCoordinate(0).getX(), monster.getPath().getPathfinderCoordinate(0).getY());
            monster.getPath().remove(0);
        }
    }

    /**
     * Gets a new path for a monster.
     * @param monster monster.
     */
    public void getNewPath(Monster monster) {
        PathfinderCoordinate start = new PathfinderCoordinate(monster.getX(), monster.getY(), null);
        PathfinderCoordinate end = new PathfinderCoordinate(game.getPlayer().getX(), game.getPlayer().getY(), null);
        monster.setPath(Pathfinder.findPath(start, end, game.getMap()));
    }  

    /**
     * Moves monsters along their path.
     * @param game game.
     */
    public void moveMonsters(Game game) {
        for (Monster monster : game.getMonsters()) {
            moveOnPath(monster);
        }
    }

    /**
     * Gets new paths for monsters to follow.
     * @param game game.
     */
    public void updateMonsterPaths(Game game) {
        for (Monster monster : game.getMonsters()) {
            getNewPath(monster);
        }
    }
    
    
    /**
     * Moves an entity to the specified position, if possible.
     * @param e entity to be moved.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void moveTo(Entity e, int x, int y) {
        if (!game.checkIfPassableCoordinate(x, y)) {
            return;
        }
        e.setX(x);
        e.setY(y);
    }

}
