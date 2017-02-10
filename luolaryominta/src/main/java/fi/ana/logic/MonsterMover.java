package fi.ana.logic;

import java.util.Random;
import java.util.List;

public class MonsterMover {

    private Game game;
    private Random r;

    public MonsterMover(Game game) {
        r = new Random();
        this.game = game;
    }

    public void arrangeMonstersRandomly(List<Character> monsters) {
        int tries = 0;
        int x;
        int y;
        for (Character c : monsters) {
            if (tries > Math.pow(game.getMap().getSize() * 2, 2)) {
                //should throw an exception methinks
                break;
            }
            x = r.nextInt(game.getMap().getSize() - 1);
            y = r.nextInt(game.getMap().getSize() - 1);
            if (game.checkIfPassableCoordinate(x, y)) {
                game.moveTo(c, x, y);
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
