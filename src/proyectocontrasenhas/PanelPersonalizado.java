/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

/**
 *
 * @author micro
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class PanelPersonalizado extends JPanel {

    private GestionInicioSesion gis= new GestionInicioSesion();
    private UUIDManager uuidM= new UUIDManager();
    String uuid=uuidM.getOrCreateUUID().toString();
    String color=gis.configurarColor(uuid);
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        // Obtener dimensiones del panel
        int width = getWidth();
        int height = getHeight();

        // Crear el gradiente
        GradientPaint gradient = new GradientPaint(1, 2,getColorByName(color), width, height, Color.getHSBColor(0.00f, 0.00f, 0.00f));

        // Dibujar el gradiente
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

    // Código generado por NetBeans para inicialización del panel
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    // End of variables declaration   

    private void setComponentZOrder(AbsoluteConstraints absConstraints, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      private Color getColorByName(String colorName){
          if(colorName==null){
           return Color.CYAN;
          }
        switch (colorName) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "green": return Color.GREEN;
            case "cyan": return Color.CYAN;
            case "yellow": return Color.YELLOW;
            case "black": return Color.BLACK;
            case "graydark": return Color.DARK_GRAY;
            case "gray": return Color.GRAY;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "magenta": return Color.MAGENTA;
            case "graylight": return Color.LIGHT_GRAY;
            default:
              return null;
                 
        }
    }
}
