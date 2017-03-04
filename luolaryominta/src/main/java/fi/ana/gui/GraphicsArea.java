package fi.ana.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import fi.ana.logic.Monster;
import fi.ana.logic.Game;
import fi.ana.logic.Item;

/**
 *
 * Draws the graphical representation of the game.
 */
public class GraphicsArea extends JPanel {

    Game game;

    /**
     * Constructor.
     *
     * @param game
     */
    public GraphicsArea(Game game) {
        super.setBackground(Color.GRAY);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawMonstersPlayerAndItems(g);
    }

    /**
     * Draws an appropriately coloured square for each coordinate in the
     * GameMap.
     *
     * @param g
     */
    public void drawMap(Graphics g) {
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (game.getMap().getValue(x, y)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 15, y * 15, 14, 14);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * 15, y * 15, 15, 15);
                }
            }
        }
    }
    
    public void drawMonstersPlayerAndItems(Graphics g) {
        g.setColor(Color.RED);
        for (Monster m : game.getMonsters()) {
            g.fillRect(m.getX() * 15, m.getY() * 15, 14, 14);
        }
        g.setColor(Color.BLUE);
        g.fillRect(game.getPlayer().getX() * 15, game.getPlayer().getY() * 15, 15, 15);
        g.setColor(Color.GREEN);
        for (Item i : game.getItems()) {
            g.fillRect(i.getX() * 15, i.getY() * 15, 15, 15);
        }
    }
}
