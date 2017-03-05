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
     * @param game game.
     */
    public GraphicsArea(Game game) {
        super.setBackground(Color.GRAY);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawMonstersPlayerGoalAndItems(g);
    }

    /**
     * Draws an appropriately coloured square for each coordinate in the
     * GameMap, if that position is visible.
     *
     * @param g Graphics-object.
     */
    public void drawMap(Graphics g) {
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                if (game.isPositionVisible(x, y)) {
                    if (game.getMap().getValue(x, y)) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x * 15, y * 15, 14, 14);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(x * 15, y * 15, 15, 15);
                    }
                } else {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * 15, y * 15, 15, 15);
                }
            }
        }
    }

    /**
     * Draws monsters, player, goal and items if they are visible.
     * @param g Graphics-object.
     */
    public void drawMonstersPlayerGoalAndItems(Graphics g) {
        g.setColor(Color.RED);
        for (Monster m : game.getMonsters()) {
            if (game.isPositionVisible(m.getX(), m.getY())) {
                g.fillRect(m.getX() * 15, m.getY() * 15, 14, 14);
            }
        }
        g.setColor(Color.BLUE);
        g.fillRect(game.getPlayer().getX() * 15, game.getPlayer().getY() * 15, 15, 15);
        g.setColor(Color.GREEN);
        for (Item i : game.getItems()) {
            if (game.isPositionVisible(i.getX(), i.getY())) {
                g.fillRect(i.getX() * 15, i.getY() * 15, 15, 15);
            }
        }
        g.setColor(Color.YELLOW);
        if (game.isPositionVisible(game.getGoal().getX(), game.getGoal().getY())) {
            g.fillRect(game.getGoal().getX() * 15, game.getGoal().getY() * 15, 14, 14);
        }
    }
}
