package fi.ana.logic;

import java.util.Random;
import java.util.List;

public class MonsterMover {

    private Game g;
    private Random r;

    public MonsterMover(Game g) {
        r = new Random();
        this.g = g;
    }

    public void arrangeMonstersRandomly(List<Character> monsters) {
        int tries = 0;
        int x;
        int y;
        for (Character c : monsters) {
            if (tries > Math.pow(g.getMap().getSize() * 2, 2)) {
                //should throw an exception methinks
                break;
            }
            x = r.nextInt(g.getMap().getSize() - 1);
            y = r.nextInt(g.getMap().getSize() - 1);
            if (g.checkIfPassableCoordinate(x, y)) {
                g.moveTo(c, x, y);
            }
        }
    }
    
    public void moveRandomly(Character c, boolean canRunIntoWalls) {
        int x;
        int y;
        if (canRunIntoWalls) {
            x = r.nextInt(3) + c.getX() - 1;
            y = r.nextInt(3) + c.getY() - 1;
            g.moveTo(c, x, y);
        } else {
            while (true) {
                x = r.nextInt(3) + c.getX() - 1;
                y = r.nextInt(3) + c.getY() - 1;
                if (g.checkIfPassableCoordinate(x, y)) {
                    g.moveTo(c, x, y);
                    break;
                }
            }
        }
    }
}
