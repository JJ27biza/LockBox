/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author micro
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class Button extends JButton{
    public Button(){
          setPreferredSize(new Dimension(100, 50));
        setContentAreaFilled(false);
         setFocusPainted(false);
    }
     @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        
        int width = getWidth();
        int height = getHeight();
        g.fillArc(0, 0, height, height, 90, 180);
        g.fillArc(width - height, 0, height, height, -90, 180);
        g.fillRect(height / 2, 0, width - height, height); 
        
        super.paintComponent(g);
    }
    
   
}
