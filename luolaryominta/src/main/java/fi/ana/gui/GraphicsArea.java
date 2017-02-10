package fi.ana.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import fi.ana.logic.Character;
import fi.ana.logic.Game;

public class GraphicsArea extends JPanel {

    Game game;

    public GraphicsArea(Game game) {
        super.setBackground(Color.white);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    public void drawMap(Graphics g) {
        for (int y = 0; y < game.getMap().getSize(); y++) {
            for (int x = 0; x < game.getMap().getSize(); x++) {
                g.setColor(interpretColor(game.getMap().getValue(x, y)));
                g.fillRect(x * 15, y * 15, 15, 15);
            }
        }
    }

    public Color interpretColor(int type) {
        switch (type) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.BLACK;
            case 2:
                return Color.RED;
            case 3:
                return Color.BLUE;
            default:
                return null;
        }
    }

}