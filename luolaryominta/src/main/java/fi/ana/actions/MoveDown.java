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
        game.moveBy(game.getPlayer(), 0, 1);
        game.proceed();
    }

}
