package fi.ana.gui;

import fi.ana.actions.*;
import java.awt.Dimension;
import javax.swing.*;
import fi.ana.logic.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * Creates the UI in which the game runs.
 */
public class GraphicalUI implements Runnable {

    private Game game;
    private JFrame frame;
    private JTextArea hpArea;
    private JTextArea countdownArea;
    private JTextArea turncountArea;

    /**
     * Constructor.
     *
     * @param game game.
     */
    public GraphicalUI(Game game) {
        this.game = game;
    }

    /**
     * Returns the JFrame object belonging to this class.
     *
     * @return
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the text of the hp area.
     * @param s text.
     */
    public void setTextToHpArea(String s) {
        hpArea.setText(s);
    }

    /**
     * Sets the text of the countdown area.
     * Not in use currently.
     * @param s text.
     */
    public void setTextToCountdownArea(String s) {
        countdownArea.setText(s);
    }

    /**
     * Sets the text of the turn count area.
     * @param s text.
     */
    public void setTextTurnCountArea(String s) {
        turncountArea.setText(s);
    }

    /**
     * Starts the UI.
     */
    @Override
    public void run() {
        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(900, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createStartScreen(frame);
        createMenu(frame);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createGameView(Container container) {
        GraphicsArea ga = new GraphicsArea(game);
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "northwest");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "northeast");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "southeast");
        ga.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "southwest");
        ga.getActionMap().put("up", new MoveUp(game));
        ga.getActionMap().put("right", new MoveRight(game));
        ga.getActionMap().put("down", new MoveDown(game));
        ga.getActionMap().put("left", new MoveLeft(game));
        ga.getActionMap().put("northwest", new MoveNorthwest(game));
        ga.getActionMap().put("northeast", new MoveNortheast(game));
        ga.getActionMap().put("southeast", new MoveSoutheast(game));
        ga.getActionMap().put("southwest", new MoveSouthwest(game));
        container.add(ga, 0);
    }

    private void createStartScreen(Container container) {
        StartScreen startScreen = new StartScreen();
        container.add(startScreen);
    }

    private void createGameOverScreen(Container container, int turns) {
        JPanel gameOver = new JPanel();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("youdied.png");
            BufferedImage image = ImageIO.read(is);
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            gameOver.add(label);
            JTextArea teksti = new JTextArea("And survived for " + turns + " turns");
            teksti.setFont(teksti.getFont().deriveFont(50f));
            gameOver.add(teksti);
            container.add(gameOver, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createWinScreen(Container container, int turns) {
        JPanel win = new JPanel();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("youone.png");
            BufferedImage image = ImageIO.read(is);
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            win.add(label);
            JTextArea teksti = new JTextArea("And it took you " + turns + " turns");
            teksti.setFont(teksti.getFont().deriveFont(50f));
            win.add(teksti);
            container.add(win, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMenu(Container container) {
        JPanel menu = new JPanel(new GridLayout(7, 0));
        menu.setSize(new Dimension(100, frame.getHeight()));
        JButton game1 = new JButton("game1");
        JButton game2 = new JButton("game2");
        JButton game3 = new JButton("game3");
        JButton randomGame = new JButton("random");
        hpArea = new JTextArea("");
        countdownArea = new JTextArea("");
        turncountArea = new JTextArea("");
        hpArea.setFont(hpArea.getFont().deriveFont(15f));
        countdownArea.setFont(hpArea.getFont().deriveFont(15f));
        turncountArea.setFont(hpArea.getFont().deriveFont(15f));
        game1.addActionListener(new Game1Listener(game));
        game2.addActionListener(new Game2Listener(game));
        game3.addActionListener(new Game3Listener(game));
        randomGame.addActionListener(new RandomGameListener(game));
        menu.add(game1);
        menu.add(game2);
        menu.add(game3);
        menu.add(randomGame);
        menu.add(hpArea);
        menu.add(countdownArea);
        menu.add(turncountArea);
        container.add(menu, BorderLayout.EAST);
    }

    /**
     * Calls the repaint() method on the UI's JFrame object and refreshes the counters.
     */
    public void refresh() {
        setTextToHpArea("HP: " + game.getPlayer().getHp());
        setTextTurnCountArea("Turn: " + game.getTurnCount());
        frame.repaint();
    }

    /**
     * Replaces content currently shown in the frame with the game view.
     */
    public void startGame() {
        frame.getContentPane().remove(0);
        createGameView(frame);
    }

    /**
     * Replaces content currently shown in the frame with the game over -view.
     * @param turns how many turns the game lasted.
     */
    public void endGame(int turns) {
        frame.getContentPane().remove(0);
        createGameOverScreen(frame, turns);
    }
    
    /**
     * Replaces content currently shown in the frame with the game won -view.
     * @param turns how many turns the game lasted.
     */
    public void winGame(int turns) {
        frame.getContentPane().remove(0);
        createWinScreen(frame, turns);
    }
}
