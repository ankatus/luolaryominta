package fi.ana.gui;

import fi.ana.logic.*;
import java.util.*;
//temporary text UI for testing
public class TextUI {

    Game game;
    Scanner sc;

    public TextUI() {
        game = new Game();
        sc = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            drawMap(game.getMap());
            if(!askForInput()) {
                break;
            }
        }
    }
    
    private boolean askForInput() {
        System.out.println("Command:");
        String command = sc.nextLine();
        if (command.equals("x")) {
            return false;
        }
        game.movePlayer(command);
        return true;
    }

    private void drawMap(GameMap map) {
        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                if (map.getValue(x, y) == 0) {
                    System.out.print("[ ]");
                } else if (map.getValue(x, y) == 1 ) {
                    System.out.print("[X]");
                } else if (map.getValue(x, y) == 2) {
                    System.out.print("[*]");
                } else {
                    System.out.print("[0]");
                }
            }
            System.out.println("");
        }
    }
}
