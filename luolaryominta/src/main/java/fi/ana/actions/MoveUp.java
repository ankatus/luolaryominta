package fi.ana.actions;

import javax.swing.AbstractAction;
import fi.ana.logic.Game;
import java.awt.event.ActionEvent;

/**
 * Action for moving the player character up.
 * 
 */
public class MoveUp extends AbstractAction {

    private Game game;

    /**
     * Constructor.
     * @param game 
     */
    public MoveUp(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(0, -1);
    }

}
