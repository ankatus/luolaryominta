package fi.ana.logic;

import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.Random;
import java.util.List;
import fi.ana.pathfinding.*;
import java.util.ArrayList;

/**
 * Handles monster moving.
 *
 */
public class MonsterMover {

    private Game game;
    private Random r;

    /**
     * Constructor.
     * @param game 
     */
    public MonsterMover(Game game) {
        r = new Random();
        this.game = game;
    }

    /**
     * Moves a character in a random position around its current position.
     *
     * @param c character to be moved.
     */
    public void moveRandomly(Monster c) {
        int x;
        int y;
        while (true) {
            x = r.nextInt(3) + c.getX() - 1;
            y = r.nextInt(3) + c.getY() - 1;
            if (game.checkIfPassableCoordinate(x, y)) {
                if (!game.checkIfInhabitedCoordinate(x, y)) {
                    game.moveTo(c, x, y);
                    break;
                }
            }
        }
    }

    /**
     * Moves a character along it's path.
     * @param monster character to be moved.
     */
    public void moveOnPath(Monster monster) {
        if (monster.getPath() != null) {
            int x = monster.getPath().getPathfinderCoordinate(0).getX();
            int y = monster.getPath().getPathfinderCoordinate(0).getY();
            if (game.checkIfInhabitedCoordinate(x, y) && game.getPlayer().getX() != x && game.getPlayer().getY() != y) {
                return;
            }
            game.moveTo(monster, monster.getPath().getPathfinderCoordinate(0).getX(), monster.getPath().getPathfinderCoordinate(0).getY());
            monster.getPath().remove(0);
        }
    }

    /**
     * Gets a new path for a character.
     * @param monster character.
     */
    public void getNewPath(Monster monster) {
        PathfinderCoordinate start = new PathfinderCoordinate(monster.getX(), monster.getY(), null);
        PathfinderCoordinate end = new PathfinderCoordinate(game.getPlayer().getX(), game.getPlayer().getY(), null);
        monster.setPath(Pathfinder.findPath(start, end, game.getMap()));
    }

    /**
     * Arranges Item-implementing objects randomly.
     * @param items items to be arranged.
     */
    public void arrangeEntitiesRandomly(List<Entity> list) {
        int x;
        int y;
        for (Entity e : list) {
            while (true) {
                x = r.nextInt(game.getMap().getSize() - 2);
                y = r.nextInt(game.getMap().getSize() - 2);
                if (!game.checkIfInhabitedCoordinate(x, y) && game.checkIfPassableCoordinate(x, y)) {
                    e.setX(x);
                    e.setY(y);
                    break;
                }
            }
        }
    }

    /**
     * Moves monsters along their path.
     */
    public void moveMonsters(Game game) {
        for (Monster monster : game.getMonsters()) {
            moveOnPath(monster);
        }
    }

    /**
     * Gets new paths for monsters to follow.
     */
    public void updateMonsterPaths(Game game) {
        for (Monster monster : game.getMonsters()) {
            getNewPath(monster);
        }
    }
}
