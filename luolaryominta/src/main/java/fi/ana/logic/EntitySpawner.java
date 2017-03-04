package fi.ana.logic;

import fi.ana.pathfinding.Path;
import fi.ana.pathfinding.PathfinderCoordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitySpawner {

    private Game game;

    public EntitySpawner(Game game) {
        this.game = game;
    }

    public List<Item> spawnHealthPacks(int howMany) {
        ArrayList<Item> list = new ArrayList();
        for (int i = 0; i < howMany; i++) {
            HealthPack healthPack = new HealthPack(-1, -1);
            placeEntityRandomly(healthPack);
            list.add(healthPack);
        }
        return list;
    }

    public List<Monster> spawnMonsters(int howMany) {
        ArrayList<Monster> list = new ArrayList();
        for (int i = 0; i < howMany; i++) {
            Monster monster = new Monster(-1, -1, 1, null);
            placeEntityRandomly(monster);
            list.add(monster);
        }
        return list;
    }

    private void placeEntityRandomly(Entity e) {
        Random r = new Random();
        int x;
        int y;
        while (true) {
            x = r.nextInt(game.getMap().getSize() - 2);
            y = r.nextInt(game.getMap().getSize() - 2);
            if (!game.checkIfInhabitedCoordinate(x, y) && game.checkIfPassableCoordinate(x, y)) {
                game.moveTo(e, x, y);
                break;
            }
        }
    }

}
