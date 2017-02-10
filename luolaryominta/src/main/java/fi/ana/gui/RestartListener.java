package fi.ana.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fi.ana.logic.Game;

/**
 * 
 * ActionListener for the restart button.
 */

public class RestartListener implements ActionListener {

    private Game game;

    public RestartListener(Game game) {
        this.game = game;
    }

    /**
     * Restarts the game when the restart button is pressed.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.restart();

    }

}
