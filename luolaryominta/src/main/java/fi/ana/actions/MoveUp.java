
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
        game.moveBy(game.getPlayer(), 0, -1);
        game.proceed();
    }
    
    
}
