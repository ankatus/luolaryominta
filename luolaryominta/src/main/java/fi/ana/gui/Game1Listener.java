package fi.ana.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fi.ana.logic.Game;

/**
 * 
 * ActionListener for the game1 button.
 */

public class Game1Listener implements ActionListener {

    private Game game;

    /**
     * Constructor.
     * @param game game.
     */
    public Game1Listener(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.game1();

    }

}
