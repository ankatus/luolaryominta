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
 *
 * @author katantti
 */
public class StartScreen extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
    }
}
