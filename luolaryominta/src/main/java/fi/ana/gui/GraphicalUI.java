package fi.ana.gui;

import fi.ana.actions.*;
import java.awt.Dimension;
import javax.swing.*;
import fi.ana.logic.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

/**
 * 
 * Creates the UI in which the game runs.
 */

public class GraphicalUI implements Runnable {

    private Game game;
    private JFrame frame;

    public GraphicalUI(Game game) {
        this.game = game;
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Starts the UI.
     */
    @Override
    public void run() {
        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(375, 375));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createGameView(frame);
        createMenu(frame);
        frame.pack();
        frame.setVisible(true);
    }

    public void createGameView(Container container) {
        GraphicsArea ga = new GraphicsArea(game);
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
        ga.getActionMap().put("up", new MoveUp(game));
        ga.getActionMap().put("right", new MoveRight(game));
        ga.getActionMap().put("down", new MoveDown(game));
        ga.getActionMap().put("left", new MoveLeft(game));
        container.add(ga);
    }

    public void createMenu(Container container) {
        JButton restart = new JButton("Restart");
        restart.addActionListener(new RestartListener(game));
        container.add(restart, BorderLayout.SOUTH);
    }

    /**
     * Calls the repaint() method on the UI's JFrame object.
     */
    public void repaintFrame() {
        frame.repaint();
    }

}
