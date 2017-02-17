package fi.ana.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import fi.ana.logic.Game;

public class MoveDown extends AbstractAction {

    private Game game;

    public MoveDown(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.proceed(0, 1);
    }

}
