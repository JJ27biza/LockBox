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
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class Panel1 extends JPanel {
     public Graphics2D graphics;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(100, 100);
        int width = getWidth();
        int height = getHeight();
       graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
            graphics.setColor(Color.BLACK);
       
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
          
    }
}
