package fi.ana.logic;

import java.awt.event.KeyEvent;
import java.util.*;
import fi.ana.gui.GraphicalUI;
import fi.ana.pathfinding.Path;

/**
 * Smushes all the logic together, starts games and proceeds to the next turn.
 *
 */
public class Game {

    private EntitySpawner entitySpawner;
    private List<Monster> monsters;
    private List<Item> items;
    private PlayerCharacter player;
    private GameMap map;
    private EntityMover monsterMover;
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
        monsterMover = new EntityMover(this);
        entitySpawner = new EntitySpawner(this);
    }

    /**
     * Starts the game UI.
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
        spawnEntitiesIfValidTurn();
        turnCount++;
        if (checkIfInhabitedCoordinate(player.getX() + x, player.getY() + y)) {
            if (!resolveCombat(player.getX() + x, player.getY() + y)) {
                gui.endGame(turnCount);
            }
        }
        monsterMover.moveTo(player, player.getX() + x, player.getY() + y);
        if (checkIfOnGoal()) {
            gui.winGame(turnCount);
        }
        resolveStackedItemAndPlayer();
        checkMonsterAggro();
        monsterMover.moveMonsters(this);
        if (!resolveStackedMonsterAndPlayer()) {
            gui.endGame(turnCount);
        }
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
     * Returns how many turns have passed.
     *
     * @return turn count.
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Checks if the specified position should be visible to the player.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true if visible, false if not.
     */
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
     * Returns a list of items currently in the game.
     *
     * @return A list of Item-objects.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Returns a list of monsters currently in the game.
     *
     * @return A list of Monster-objects.
     */
    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Returns the Goal-object of the game.
     *
     * @return A Goal-object.
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * Starts game1, setting all values back to default game1-values and
     * re-randomising monster locations.
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
     * Starts game2, setting all values back to default game2-values and
     * re-randomising monster locations.
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
     * Starts game3, setting all values back to default game3-values and
     * re-randomising monster locations.
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
     * Starts a random game, setting all values back to default random game
     * -values and re-randomising monster locations. The map will be randomised.
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
     * Checks if the specified position is a valid position for moving into.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true if passable, false if not.
     */
    public boolean checkIfPassableCoordinate(int x, int y) {
        if (!map.isValidCoordinate(x, y)) {
            return false;
        }
        return !map.getValue(x, y);
    }

    /**
     * Checks if the specified location is currently inhabited by a player or a
     * monster.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true if inhabited, false if not.
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
     * specified coordinates.
     *
     * @param x x-coordinate of monster to combat.
     * @param y y-coordinate of monster to combat.
     * @return true if the player survives, false if not.
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
     * @return true if the player survives, false if not.
     */
    public boolean resolveStackedMonsterAndPlayer() {
        return resolveCombat(player.getX(), player.getY());
    }

    /**
     * Calls the interact()-method of any items stacked with the player.
     *
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

    /**
     * Checks if the player character is on the goal.
     * @return true if the player is on the goal, fals if not.
     */
    public boolean checkIfOnGoal() {
        return player.getX() == goal.getX() && player.getY() == goal.getY();
    }

    /**
     * Checks if the player is inside aggro range of any monster, and activates their pathfinding if so.
     */
    public void checkMonsterAggro() {
        for (Monster m : monsters) {
            if (Math.abs(m.getX() - player.getX()) + Math.abs(m.getY() - player.getY()) < 6) {
                monsterMover.getNewPath(m);
            } else if (m.getPath() != null) {
                m.setPath(null);
            }
        }
    }

    /**
     * Spawns a new monster and/or healthpack if correct turn, and if there are not too many.
     */
    public void spawnEntitiesIfValidTurn() {
        if (turnCount % 30 == 0 && items.size() < 5) {
            items.addAll(entitySpawner.spawnHealthPacks(1));
        }

        if (turnCount % 30 == 0 && monsters.size() < 5) {
            monsters.addAll(entitySpawner.spawnMonsters(1));
        }
    }
}
