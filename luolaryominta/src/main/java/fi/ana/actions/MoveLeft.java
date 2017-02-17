package fi.ana.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import fi.ana.logic.Game;

/**
 * Action for moving the player character left.
 * 
 */
public class MoveLeft extends AbstractAction {

    private Game game;

    /**
     * Constructor.
     * @param game 
     */
    public MoveLeft(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(-1, 0);
    }

}
