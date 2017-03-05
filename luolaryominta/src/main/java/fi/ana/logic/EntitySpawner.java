package fi.ana.logic;

import fi.ana.pathfinding.Path;
import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Handles spawning entities to the map.
 * @author katantti
 */
public class EntitySpawner {

    private Game game;

    /**
     * Constructor.
     * @param game game.
     */
    public EntitySpawner(Game game) {
        this.game = game;
    }

    /**
     * Returns a list of randomly arranged HealthPacks.
     * @param howMany how many to return.
     * @return list of HealthPacks.
     */
    public List<Item> spawnHealthPacks(int howMany) {
        ArrayList<Item> list = new ArrayList();
        for (int i = 0; i < howMany; i++) {
            HealthPack healthPack = new HealthPack(-1, -1);
            placeEntityRandomly(healthPack);
            list.add(healthPack);
        }
        return list;
    }

    /**
     * Returns a list of randomly arranged monsters.
     * @param howMany how many to return.
     * @return list of monsters.
     */
    public List<Monster> spawnMonsters(int howMany) {
        ArrayList<Monster> list = new ArrayList();
        for (int i = 0; i < howMany; i++) {
            Monster monster = new Monster(-1, -1, 1, null);
            placeEntityRandomly(monster);
            list.add(monster);
        }
        return list;
    }

    /**
     * Returns a randomly arranged Goal.
     * @return Goal.
     */
    public Goal spawnGoal() {
        Goal goal = new Goal(-1, -1);
        placeEntityRandomly(goal);
        return goal;
    }

    private void placeEntityRandomly(Entity e) {
        Random r = new Random();
        int x;
        int y;
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
