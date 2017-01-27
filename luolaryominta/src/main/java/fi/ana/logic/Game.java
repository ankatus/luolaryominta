
package fi.ana.logic;

import java.util.*;

public class Game {
    
    private List<Character> monsters;
    private PlayerCharacter player;
    private Map map;
    private MapMaker mapMaker;
     
    public Game() {
        mapMaker = new MapMaker();
        map = mapMaker.makeDefaultMap();
    }
     
}
