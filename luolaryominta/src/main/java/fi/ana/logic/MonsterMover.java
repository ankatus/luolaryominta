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

    public MonsterMover(Game game) {
        r = new Random();
        this.game = game;
    }

    /**
     * Moves all the characters in the input list to random positions.
     * Characters cannot be moved out of bounds, in walls or on top of other
     * characters.
     *
     * @param monsters
     */
    public void arrangeMonstersRandomly(List<GameCharacter> monsters) {
        int x;
        int y;
        for (GameCharacter c : monsters) {
            while (true) {
                x = r.nextInt(game.getMap().getSize() - 2);
                y = r.nextInt(game.getMap().getSize() - 2);
                if (!game.checkIfInhabitedCoordinate(x, y) && game.checkIfPassableCoordinate(x, y)) {
                    game.moveTo(c, x, y);
                    ArrayList<PathfinderCoordinate> list = new ArrayList();
                    list.add(new PathfinderCoordinate(c.getX(), c.getY(), null));
                    c.setPath(new Path(list));
                    break;
                }
            }
        }
    }

    /**
     * Moves a character in a random position around its current position.
     *
     * @param c
     */
    public void moveRandomly(GameCharacter c) {
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

    public void moveOnPath(GameCharacter monster) {
        if (!monster.getPath().getPath().isEmpty()) {
            int x = monster.getPath().getPathfinderCoordinate(0).getX();
            int y = monster.getPath().getPathfinderCoordinate(0).getY();
            if (game.checkIfInhabitedCoordinate(x, y) && game.getPlayer().getX() != x && game.getPlayer().getY() != y) {
                return;
            }
            game.moveTo(monster, monster.getPath().getPathfinderCoordinate(0).getX(), monster.getPath().getPathfinderCoordinate(0).getY());
            monster.getPath().remove(0);
        }
    }

    public void getNewPath(GameCharacter monster) {
        PathfinderCoordinate start = new PathfinderCoordinate(monster.getX(), monster.getY(), null);
        PathfinderCoordinate end = new PathfinderCoordinate(game.getPlayer().getX(), game.getPlayer().getY(), null);
        monster.setPath(Pathfinder.findPath(start, end, game.getMap()));
    }

    public void arrangeItemsRandomly(List<Item> items) {
        int x;
        int y;
        for (Item i : items) {
            while (true) {
                x = r.nextInt(game.getMap().getSize() - 2);
                y = r.nextInt(game.getMap().getSize() - 2);
                if (!game.checkIfInhabitedCoordinate(x, y) && game.checkIfPassableCoordinate(x, y)) {
                    i.setX(x);
                    i.setY(y);
                    break;
                }
            }
        }
    }
}
