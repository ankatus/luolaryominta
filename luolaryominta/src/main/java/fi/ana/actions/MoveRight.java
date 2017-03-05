package fi.ana.actions;

import fi.ana.logic.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action for moving the player character right.
 * 
 */
public class MoveRight extends AbstractAction {

    private Game game;

    /**
     * Constructor.
     * @param game game to interact with.
     */
    public MoveRight(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(1, 0);
    }
}
