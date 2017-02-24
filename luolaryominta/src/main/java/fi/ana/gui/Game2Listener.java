package fi.ana.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fi.ana.logic.Game;

/**
 * 
 * ActionListener for the game2 button.
 */

public class Game2Listener implements ActionListener {

    private Game game;

    /**
     * Constructor.
     * @param game game.
     */
    public Game2Listener(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.game2();

    }

}
