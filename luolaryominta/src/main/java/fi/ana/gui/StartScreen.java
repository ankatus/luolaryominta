/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ana.gui;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * Class for displaying the start screen.
 * @author katantti
 */
public class StartScreen extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        drawStartScreen(g);
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(70, 175, 15, 15);
        g.fillRect(70, 160, 15, 15);
        g.fillRect(85, 145, 15, 15);
        g.fillRect(85, 130, 15, 15);
        g.fillRect(100, 115, 15, 15);
        g.fillRect(100, 100, 15, 15);
        g.fillRect(115, 100, 15, 15);
        g.fillRect(115, 115, 15, 15);
        g.fillRect(130, 130, 15, 15);
        g.fillRect(130, 145, 15, 15);
        g.fillRect(145, 160, 15, 15);
        g.fillRect(145, 175, 15, 15);
        g.fillRect(160, 175, 15, 15);
        g.fillRect(160, 160, 15, 15);
        g.fillRect(175, 145, 15, 15);
        g.fillRect(175, 130, 15, 15);
        g.fillRect(190, 115, 15, 15);
        g.fillRect(190, 100, 15, 15);
        g.fillRect(205, 100, 15, 15);
        g.fillRect(205, 115, 15, 15);
        g.fillRect(220, 130, 15, 15);
        g.fillRect(220, 145, 15, 15);
        g.fillRect(235, 160, 15, 15);
        g.fillRect(235, 175, 15, 15);
        
        g.fillRect(280, 130, 15, 15);
        g.fillRect(295, 115, 15, 15);
        g.fillRect(310, 100, 15, 15);
        g.fillRect(325, 100, 15, 15);
        g.fillRect(340, 100, 15, 15);
        g.fillRect(355, 115, 15, 15);
        g.fillRect(370, 130, 15, 15);
        g.fillRect(370, 145, 15, 15);
        g.fillRect(355, 160, 15, 15);
        g.fillRect(340, 175, 15, 15);
        g.fillRect(325, 175, 15, 15);
        g.fillRect(310, 175, 15, 15);
        g.fillRect(295, 160, 15, 15);
        g.fillRect(280, 145, 15, 15);

        g.fillRect(410, 100, 15, 15);
        g.fillRect(410, 130, 15, 15);
        g.fillRect(410, 145, 15, 15);
        g.fillRect(410, 160, 15, 15);
        g.fillRect(410, 175, 15, 15);

    }
}
