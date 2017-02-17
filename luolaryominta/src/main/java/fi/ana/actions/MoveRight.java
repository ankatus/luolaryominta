package fi.ana.actions;

import fi.ana.logic.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class MoveRight extends AbstractAction {

    private Game game;

    public MoveRight(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(1, 0);
    }
}
