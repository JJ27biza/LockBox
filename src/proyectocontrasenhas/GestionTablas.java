/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author micro
 */
public class GestionTablas {

    private PanelPrincipal panel;

    public GestionTablas(PanelPrincipal panel) {
        this.panel = panel;
    }
  

    public void añadiTablaVisualizar() {
        limpiartablaUsuario();

        DefaultTableModel model = (DefaultTableModel) panel.tbUsuario.getModel();

        Object obj[] = {"", "", "", ""};
        for (int a = 0; a < panel.arrayCuentas.size(); a++) {

            model.addRow(obj);
            panel.tbUsuario.setValueAt(panel.arrayCuentas.get(a).getUsuario().toString(), a, panel.FILA_0);

            panel.tbUsuario.setValueAt(panel.arrayCuentas.get(a).getCorreo().toString(), a, panel.FILA_1);

            panel.tbUsuario.setValueAt(panel.arrayCuentas.get(a).getReferencia().toString(), a, panel.FILA_2);

            panel.tbUsuario.setValueAt(panel.comprobarInicio(a), a, panel.FILA_3);

        }

    }

    public void limpiartablaUsuario() {
        DefaultTableModel temp = (DefaultTableModel) panel.tbUsuario.getModel();
        panel.tbUsuario.clearSelection();
        int rowCount = temp.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            temp.removeRow(i);
        }
    }
    public void añadirTablaEliminar() {

      limpiartablaEliminar();
        DefaultTableModel model = (DefaultTableModel) panel.tbEliminar.getModel();

        Object obj[] = {"", "", "", ""};
        for (int a = 0; a < panel.arrayCuentas.size(); a++) {
            model.addRow(obj);

            panel.tbEliminar.setValueAt(panel.arrayCuentas.get(a).getUsuario().toString(), a, panel.FILA_0);

            panel.tbEliminar.setValueAt(panel.arrayCuentas.get(a).getCorreo().toString(), a,panel. FILA_1);

            panel.tbEliminar.setValueAt(panel.arrayCuentas.get(a).getReferencia().toString(), a, panel.FILA_2);

            panel.tbEliminar.setValueAt(panel.comprobarInicio(a), a, panel.FILA_3);

        }

    }
     public void añadirTablaModificar() {

        limpiartablaModificar();
        DefaultTableModel model = (DefaultTableModel)  panel.tbModificar.getModel();

        Object obj[] = {"", "", "", ""};
        for (int a = 0; a <  panel.arrayCuentas.size(); a++) {
            model.addRow(obj);

             panel.tbModificar.setValueAt( panel.arrayCuentas.get(a).getUsuario().toString(), a,  panel.FILA_0);

             panel.tbModificar.setValueAt( panel.arrayCuentas.get(a).getCorreo().toString(), a,  panel.FILA_1);

             panel.tbModificar.setValueAt( panel.arrayCuentas.get(a).getReferencia().toString(), a,  panel.FILA_2);

             panel.tbModificar.setValueAt( panel.comprobarInicio(a), a,  panel.FILA_3);

        }

    }
     public void datosTablaACamposVisualizar() {
        panel.tbUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    JTable jTable = (JTable) mouseEvent.getSource();
                    int selectedRow = jTable.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    String referencia = jTable.getValueAt(selectedRow, 2).toString();
                    String usuario = jTable.getValueAt(selectedRow, 0).toString();
                    String correo = jTable.getValueAt(selectedRow, 1).toString();

                    panel.actualizarCamposTexto(referencia, usuario, correo);
                }
            }
        });
    }
      public void datosTablaACamposModificar() {
         panel.tbModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    JTable jTable = (JTable) mouseEvent.getSource();
                    int selectedRow = jTable.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    String referencia = jTable.getValueAt(selectedRow, 2).toString();
                    String usuario = jTable.getValueAt(selectedRow, 0).toString();

                     panel.buttonGroup2.clearSelection();

                     panel.actualizarCamposModificar(referencia, usuario);
                }
            }
        });
    }
       public void referenciaEliminarCuenta() {
         panel.tbEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable jTable = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = jTable.rowAtPoint(point);
                int column = 2;

                if (mouseEvent.getClickCount() == 1) {

                    if (row >= 0 && row < jTable.getRowCount() && column >= 0 && column < jTable.getColumnCount()) {
                        Object value = jTable.getValueAt(row, column);

                        if (value != null) {
                             panel.refeEliminar = value.toString();
                        } else {
                             panel.refeEliminar = null;
                            JOptionPane.showMessageDialog(null, "La celda seleccionada está vacía.");
                        }
                    }
                }
            }
        });
    }
       public void referenciaModificar() {
         panel.tbModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable jTable = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = jTable.rowAtPoint(point);
                int column = 2;

                if (mouseEvent.getClickCount() == 1) {

                    if (row >= 0 && row < jTable.getRowCount() && column >= 0 && column < jTable.getColumnCount()) {
                        Object value = jTable.getValueAt(row, column);

                        if (value != null) {
                            panel. refeModificar = value.toString();
                        } else {
                            panel. refeModificar = null;
                            JOptionPane.showMessageDialog(null, "La celda seleccionada está vacía.");
                        }
                    }
                }
            }
        });

    }
        private void limpiartablaEliminar() {
        DefaultTableModel temp = (DefaultTableModel) panel.tbEliminar.getModel();
        panel.tbEliminar.clearSelection();
        int rowCount = temp.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            temp.removeRow(i);
        }

    }
         public void limpiartablaModificar() {
        DefaultTableModel temp = (DefaultTableModel)  panel.tbModificar.getModel();
         panel.tbModificar.clearSelection();
        int rowCount = temp.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            temp.removeRow(i);
        }

    }
        


   
}
