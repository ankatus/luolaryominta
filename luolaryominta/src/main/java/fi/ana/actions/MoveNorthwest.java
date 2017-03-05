package fi.ana.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import fi.ana.logic.Game;

/**
 * Action for moving the player character up and to the left.
 * 
 */
public class MoveNorthwest extends AbstractAction {

    private Game game;

    /**
     * Constructor.
     * @param game game to interact with.
     */
    public MoveNorthwest(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(-1, -1);
    }

}
