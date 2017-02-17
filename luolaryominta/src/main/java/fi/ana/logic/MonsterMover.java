package fi.ana.logic;

import java.util.Random;
import java.util.List;

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

    public void arrangeMonstersRandomly(List<Character> monsters) {
        int x;
        int y;
        for (Character c : monsters) {
            while (true) {
                x = r.nextInt(game.getMap().getSize() - 1);
                y = r.nextInt(game.getMap().getSize() - 1);
                if (!game.checkIfInhabitedCoordinate(x, y) && game.checkIfPassableCoordinate(x, y)) {
                    game.moveTo(c, x, y);
                    break;
                }
            }
        }
    }

    public void moveRandomly(Character c) {
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
}
