package fi.ana.logic;

import java.awt.event.KeyEvent;
import java.util.*;
import fi.ana.gui.GraphicalUI;
import fi.ana.pathfinding.Path;

/**
 *
 * Handles all of the player movement logic, starts the UI, and acts as a hub
 * for all the logic of the game. Will be refactored to multiple classes at some
 * point (probably).
 */
public class Game {

    private EntitySpawner entitySpawner;
    private List<Monster> monsters;
    private List<Item> items;
    private PlayerCharacter player;
    private GameMap map;
    private MonsterMover monsterMover;
    private GraphicalUI gui;
    private int turnCount;

    /**
     * Constructor.
     */
    public Game() {
        gui = new GraphicalUI(this);
        monsters = new ArrayList();
        items = new ArrayList();
        monsterMover = new MonsterMover(this);
        entitySpawner = new EntitySpawner(this);
    }

    /**
     * Starts the game.
     */
    public void start() {
        gui.run();
    }

    /**
     * Moves the game ahead one turn and moves the player.
     *
     * @param x amount of spaces the player should move on the x-axis.
     * @param y amount of spaces the player should move on the y-axis.
     */
    public void proceed(int x, int y) {
        turnCount++;
        gui.setTextTurnCountArea("Turn: " + turnCount);
        if (checkIfInhabitedCoordinate(player.getX() + x, player.getY() + y)) {
            if (!resolveCombat(player.getX() + x, player.getY() + y)) {
                gui.setTextToHpArea("HP: " + player.getHp());
                gui.endGame(turnCount);
            }
            gui.setTextToHpArea("HP: " + player.getHp());
        }
        moveBy(player, x, y);
        resolveStackedItemAndPlayer();
        checkMonsterAggro();
        moveMonsters();
        if (!resolveStackedMonsterAndPlayer()) {
            gui.setTextToHpArea("HP: " + player.getHp());
            gui.endGame(turnCount);
        }
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.refresh();
    }

    /**
     * Returns the PlayerCharacter object associated with the player.
     *
     * @return PlayerCharacter object.
     */
    public PlayerCharacter getPlayer() {
        return player;
    }

    /**
     * Returns the GameMap object currently used to store the game's map.
     *
     * @return a GameMap object.
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Returns a list of objects that implement the Item-interface.
     *
     * @return A list of objects.
     */
    public List<Item> getItems() {
        return items;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Starts game1, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game1() {
        turnCount = 0;
        map = MapMaker.makeSmallMap();
        player = new PlayerCharacter(1, 1, 1);
        monsters = entitySpawner.spawnMonsters(1);
        items = entitySpawner.spawnHealthPacks(1);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.refresh();
    }

    /**
     * Starts game2, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game2() {
        turnCount = 0;
        map = MapMaker.makeMediumMap();
        player = new PlayerCharacter(1, 1, 2);
        monsters = entitySpawner.spawnMonsters(2);
        items = entitySpawner.spawnHealthPacks(1);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.refresh();
    }

    /**
     * Starts game2, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game3() {
        turnCount = 0;
        map = MapMaker.makeSmallMap();
        player = new PlayerCharacter(1, 1, 3);
        monsters = entitySpawner.spawnMonsters(3);
        items = entitySpawner.spawnHealthPacks(1);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.refresh();
    }

    /**
     * Starts a random game, setting all values back to default and
     * re-randomising monster locations. The map will be randomised.
     */
    public void randomGame() {
        turnCount = 0;
        map = MapMaker.makeRandomMap();
        player = new PlayerCharacter(1, 1, 3);
        monsters = entitySpawner.spawnMonsters(3);
        items = entitySpawner.spawnHealthPacks(1);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.refresh();
    }

    /**
     * Moves a Character object by the specified distance.
     *
     * @param c Character to be moved.
     * @param x Distance to be moved laterally.
     * @param y Distance to be moved vertically.
     */
    public void moveBy(Entity e, int x, int y) {
        if (!checkIfPassableCoordinate(e.getX() + x, e.getY() + y)) {
            return;
        }
        e.setX(e.getX() + x);
        e.setY(e.getY() + y);
    }

    /**
     * Moves a Character object to the specified coordinate.
     *
     * @param c Character to be moved.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void moveTo(Entity e, int x, int y) {
        if (!checkIfPassableCoordinate(x, y)) {
            return;
        }
        e.setX(x);
        e.setY(y);
    }

    /**
     * Checks if the specified location is 1) on the map 2) not obstructed.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true or false
     */
    public boolean checkIfPassableCoordinate(int x, int y) {
        if (!map.isValidCoordinate(x, y)) {
            return false;
        }
        return !map.getValue(x, y);
    }

    /**
     * Checks if the specified location is currently inhabited by a Character.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true or false
     */
    public boolean checkIfInhabitedCoordinate(int x, int y) {
        if (player != null && player.getX() == x && player.getY() == y) {
            return true;
        }
        for (Monster m : monsters) {
            if (m.getX() == x && m.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets new paths for monsters to follow.
     */
    public void updateMonsterPaths() {
        for (Monster monster : monsters) {
            monsterMover.getNewPath(monster);
        }
    }

    /**
     * Moves monsters along their path.
     */
    public void moveMonsters() {
        for (Monster monster : monsters) {
            monsterMover.moveOnPath(monster);
        }
    }

    /**
     * Resolves what happens in "combat" between the player and a monster in the
     * specified coordinates. Returns true if the player survives, false if they
     * die
     *
     * @param x x-coordinate of monster to combat.
     * @param y y-coordinate of monster to combat.
     * @return true or false.
     */
    public boolean resolveCombat(int x, int y) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getX() == x && monsters.get(i).getY() == y) {
                if (monsters.get(i).getHp() < player.getHp()) {
                    player.setHP(player.getHp() - monsters.get(i).getHp());
                    monsters.remove(i);
                } else {
                    player.setHP(player.getHp() - monsters.get(i).getHp());
                    monsters.remove(i);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Calls the resolveCombat method on the player's coordinates.
     *
     * @return true or false.
     */
    public boolean resolveStackedMonsterAndPlayer() {
        return resolveCombat(player.getX(), player.getY());
    }

    /**
     * Calls the interact()-method of any items stacked with the player.
     *
     * @return true or false;
     */
    public void resolveStackedItemAndPlayer() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getX() == player.getX() && item.getY() == player.getY()) {
                items.get(i).interact(player);
                items.remove(i);
            }
        }
    }

    public void checkMonsterAggro() {
        for (Monster m : monsters) {
            if (Math.abs(m.getX() - player.getX()) + Math.abs(m.getY() - player.getY()) < 10) {
                monsterMover.getNewPath(m);
            } else if (m.getPath() != null) {
                m.getPath().getPath().clear();
            }
        }
    }
}
