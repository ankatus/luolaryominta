/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.gui;

import fi.ana.logic.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the random game -button.
 * @author katantti
 */
public class RandomGameListener implements ActionListener {

    private Game game;
    
    /**
     * Constructor.
     * @param game game.
     */
    public RandomGameListener(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.randomGame();
    }
    
}
