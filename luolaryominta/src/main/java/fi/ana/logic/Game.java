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

    private List<GameCharacter> monsters;
    private List<Item> items;
    private GameCharacter player;
    private GameMap map;
    private MonsterMover monsterMover;
    private GraphicalUI gui;
    private int countdown;
    private int turnCount;
 
    /**
     * Constructor.
     */
    public Game() {
        gui = new GraphicalUI(this);
        monsters = new ArrayList();
        items = new ArrayList();
        monsterMover = new MonsterMover(this);
    }

    /**
     * Starts the game.
     */
    public void start() {
        gui.run();
    }

    /**
     * Moves the game ahead one turn and moves the player.
     * @param x amount of spaces the player should move on the x-axis.
     * @param y amount of spaces the player should move on the y-axis.
     */
    public void proceed(int x, int y) {
        countdown--;
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
        if (!resolveStackedItemAndPlayer()) {
            gui.setTextToHpArea("HP: " + player.getHp());
            gui.endGame(turnCount);
        }
        if (countdown % 1 == 0) {
            updateMonsterPaths();
        }
        moveMonsters();
        if (!resolveStackedMonsterAndPlayer()) {
            gui.setTextToHpArea("HP: " + player.getHp());
            gui.endGame(turnCount);
        }
        gui.setTextToHpArea("HP: " + player.getHp());
        if (countdown == 0) {
            countdown = 20;
            if (items.size() < 4) {
                spawnHealthPack();
            }
            spawnMonster();

        }
        gui.setTextToCountdownArea("Spawn: " + countdown);
        gui.refresh();
    }

    /**
     * Returns the GameCharacter object associated with the player.
     * @return GameCharacter object.
     */
    public GameCharacter getPlayer() {
        return player;
    }

    /**
     * Returns a list of the GameCharacter objects associated with the monsters.
     * @return GameCharacter object.
     */
    public List<GameCharacter> getMonsters() {
        return monsters;
    }

    /**
     * Returns a list of objects that implement the Item-interface.
     * @return A list of objects.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Starts game1, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game1() {
        countdown = 10;
        turnCount = 0;
        map = MapMaker.makeSmallMap();
        player = new GameCharacter(1, 1, 1, 3);
        monsters = initializeMonsters(1);
        monsterMover.arrangeMonstersRandomly(monsters);
        items = initializeHealthPacks(1);
        monsterMover.arrangeItemsRandomly(items);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.setTextToCountdownArea("Spawn: " + countdown);
        gui.refresh();
    }

    /**
     * Starts game2, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game2() {
        countdown = 10;
        turnCount = 0;
        map = MapMaker.makeMediumMap();
        player = new GameCharacter(1, 1, 2, 3);
        monsters = initializeMonsters(2);
        monsterMover.arrangeMonstersRandomly(monsters);
        items = initializeHealthPacks(2);
        monsterMover.arrangeItemsRandomly(items);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.setTextToCountdownArea("Spawn: " + countdown);
        gui.refresh();
    }

    /**
     * Starts game2, setting all values back to default and re-randomising
     * monster locations.
     */
    public void game3() {
        countdown = 10;
        turnCount = 0;
        map = MapMaker.makeLargeMap();
        player = new GameCharacter(1, 1, 3, 3);
        monsters = initializeMonsters(3);
        monsterMover.arrangeMonstersRandomly(monsters);
        items = initializeHealthPacks(3);
        monsterMover.arrangeItemsRandomly(items);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.setTextToCountdownArea("Spawn: " + countdown);
        gui.refresh();
    }
    
    /**
     * Starts a random game, setting all values back to default and re-randomising
     * monster locations. The map will be randomised.
     */
    public void randomGame() {
        countdown = 10;
        turnCount = 0;
        map = MapMaker.makeRandomMap();
        player = new GameCharacter(1, 1, 3, 3);
        monsters = initializeMonsters(3);
        monsterMover.arrangeMonstersRandomly(monsters);
        items = initializeHealthPacks(3);
        monsterMover.arrangeItemsRandomly(items);
        moveBy(player, 0, 0);
        gui.startGame();
        gui.setTextToHpArea("HP: " + player.getHp());
        gui.setTextTurnCountArea("Turn: " + turnCount);
        gui.setTextToCountdownArea("Spawn: " + countdown);
        gui.refresh();
    }

    /**
     * Returns the GameMap object currently used to store the game's map.
     * @return a GameMap object.
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Moves a Character object by the specified distance.
     *
     * @param c Character to be moved.
     * @param x Distance to be moved laterally.
     * @param y Distance to be moved vertically.
     */
    public void moveBy(GameCharacter c, int x, int y) {
        if (!checkIfPassableCoordinate(c.getX() + x, c.getY() + y)) {
            return;
        }
        c.setX(c.getX() + x);
        c.setY(c.getY() + y);
    }

    /**
     * Moves a Character object to the specified coordinate.
     *
     * @param c Character to be moved.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void moveTo(GameCharacter c, int x, int y) {
        if (!checkIfPassableCoordinate(x, y)) {
            return;
        }
        c.setX(x);
        c.setY(y);
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
        for (GameCharacter c : monsters) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fills the monsters list with GameCharacter objects.
     *
     * @param howMany amount to be created.
     * @return filled list.
     */
    public List<GameCharacter> initializeMonsters(int howMany) {
        List<GameCharacter> monsters = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            monsters.add(new GameCharacter(-1, -1, 1, 2));
        }
        return monsters;
    }

    /**
     * Gets new paths for monsters to follow.
     */
    public void updateMonsterPaths() {
        for (GameCharacter monster : monsters) {
            monsterMover.getNewPath(monster);
        }
    }

    /**
     * Moves monsters along their path.
     */
    public void moveMonsters() {
        for (GameCharacter monster : monsters) {
            monsterMover.moveOnPath(monster);
        }
    }

    /**
     * Resolves what happens in "combat" between the player and a monster in the specified coordinates.
     * Returns true if the player survives, false if they die
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
     * @return true or false.
     */
    public boolean resolveStackedMonsterAndPlayer() {
        return resolveCombat(player.getX(), player.getY());
    }

    /**
     * Calls the interact()-method of any items stacked with the player.
     * @return true or false;
     */
    public boolean resolveStackedItemAndPlayer() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getX() == player.getX() && items.get(i).getY() == player.getY()) {
                if (!items.get(i).interact(player)) {
                    return false;
                }
                items.remove(i);
            }
        }
        return true;
    }

    /**
     * Fills the items-list with HealthPack-objects.
     * @param howMany how many objects to initialize.
     * @return list of items.
     */
    public List<Item> initializeHealthPacks(int howMany) {
        List<Item> returnList = new ArrayList();
        for (int i = 0; i < howMany; i++) {
            returnList.add(new HealthPack(-1, -1));
        }
        return returnList;
    }

    /**
     * Spawns a new monster at a random location.
     */
    public void spawnMonster() {
        ArrayList<GameCharacter> list = new ArrayList();
        list.add(new GameCharacter(-1, -1, 1, 2));
        monsterMover.arrangeMonstersRandomly(list);
        monsters.addAll(list);
    }

    /**
     * Spawns a new HealthPack at a random location.
     */
    public void spawnHealthPack() {
        ArrayList<Item> list = new ArrayList();
        list.add(new HealthPack(-1, -1));
        monsterMover.arrangeItemsRandomly(list);
        items.addAll(list);
    }
}
