package fi.ana.actions;

import javax.swing.AbstractAction;
import fi.ana.logic.Game;
import java.awt.event.ActionEvent;

public class MoveUp extends AbstractAction {

    private Game game;

    public MoveUp(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(0, -1);
    }

}
