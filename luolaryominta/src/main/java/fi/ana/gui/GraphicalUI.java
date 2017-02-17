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
    private JTextArea hpArea;

    public GraphicalUI(Game game) {
        this.game = game;
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void setTextToHpArea(String s) {
        hpArea.setText(s);
    }

    /**
     * Starts the UI.
     */
    @Override
    public void run() {
        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(900, 700));
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
        JPanel menu = new JPanel(new GridLayout(4, 0));
        menu.setSize(new Dimension(100, frame.getHeight()));
        JButton game1 = new JButton("game1");
        JButton game2 = new JButton("game2");
        JButton game3 = new JButton("game3");
        hpArea = new JTextArea("");
        hpArea.setFont(hpArea.getFont().deriveFont(24f));
        game1.addActionListener(new game1Listener(game));
        game2.addActionListener(new game2Listener(game));
        game3.addActionListener(new game3Listener(game));
        menu.add(game1);
        menu.add(game2);
        menu.add(game3);
        menu.add(hpArea);
        container.add(menu, BorderLayout.EAST);
    }

    /**
     * Calls the repaint() method on the UI's JFrame object.
     */
    public void refresh() {
        frame.repaint();
    }
    
    public void resizeFrame(int x, int y) {
        frame.setSize(new Dimension(x, y));
    }

}
