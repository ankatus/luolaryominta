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
    private Goal goal;

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
        if (turnCount % 30 == 0 && items.size() < 5) {
            items.addAll(entitySpawner.spawnHealthPacks(1));
        }
        
        if (turnCount % 30 == 0 && monsters.size() < 5) {
            monsters.addAll(entitySpawner.spawnMonsters(1));
        }
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
        if (checkIfOnGoal()) {
            gui.winGame(turnCount);
        }
        resolveStackedItemAndPlayer();
        checkMonsterAggro();
        monsterMover.moveMonsters(this);
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

    public GameMap getVisibleMap() {
        int realX = player.getX();
        int realY = player.getY();
        int visibleX = 0;
        int visibleY = 0;
        GameMap visibleMap = new GameMap(9);
        for (int j = realY - 4; j <= realY + 4; j++) {
            visibleX = 0;
            for (int i = realX - 4; i <= realX + 4; i++) {
                if (map.isValidCoordinate(realX, realY)) {
                    visibleMap.setValue(visibleX, visibleY, map.getValue(realX, realY));
                } else {
                    visibleMap.setValue(visibleX, visibleY, true);
                }
                visibleX++;
            }
            visibleY++;
        }
        return visibleMap;
    }

    public boolean isPositionVisible(int x, int y) {

        if (x > player.getX() + 5 || x < player.getX() - 5) {
            return false;
        }

        if (y > player.getY() + 5 || y < player.getY() - 5) {
            return false;
        }

        return true;

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
    
    public Goal getGoal() {
        return goal;
    }

    /**
     * Starts game1, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game1() {
        turnCount = 0;
        map = MapMaker.makeSmallMap();
        goal = entitySpawner.spawnGoal();
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
        goal = entitySpawner.spawnGoal();
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
        map = MapMaker.makeLargeMap();
        goal = entitySpawner.spawnGoal();
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
                    player.setHp(player.getHp() - monsters.get(i).getHp());
                    monsters.remove(i);
                } else {
                    player.setHp(player.getHp() - monsters.get(i).getHp());
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

    public boolean checkIfOnGoal() {
        return player.getX() == goal.getX() && player.getY() == goal.getY();
    }

    public void checkMonsterAggro() {
        for (Monster m : monsters) {
            if (Math.abs(m.getX() - player.getX()) + Math.abs(m.getY() - player.getY()) < 6) {
                monsterMover.getNewPath(m);
            } else if (m.getPath() != null) {
                m.setPath(null);
            }
        }
    }
}
