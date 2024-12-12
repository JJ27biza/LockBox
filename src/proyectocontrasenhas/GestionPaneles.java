/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author micro
 */
public class GestionPaneles {

    private PanelPrincipal panel;

    public GestionPaneles(PanelPrincipal panel) {
        this.panel = panel;
    }

    public void cambioPaneles() {
        panel.lblMenuCrear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelCrear();

            }
        });
        panel.lblImgCrear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelCrear();

            }
        });

        panel.lblInicio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelInicio();
            }
        });
        panel.lblImgInicio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelInicio();

            }
        });
        panel.lblImgEliminar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelEliminar();
            }
        });
        panel.lblMenuEliminar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelEliminar();

            }
        });
        panel.lblImgVer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelVer();

            }
        });
        panel.lblMenuVer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelVer();

            }
        });
        panel.lblImgModificar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelModificar();

            }
        });
        panel.lblMenuModificar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelModificar();
            }
        });
        panel.lblImgAyuda.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelAyuda();

            }
        });
        panel.lblMenuAyuda.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                activarPanelAyuda();

            }
        });
        panel.lblImgConfi.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelConfigurar();

            }
        });
        panel.lblMenuConfi.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activarPanelConfigurar();

            }
        });
    }

    private void activarPanelConfigurar() {
        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(true);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void activarPanelAyuda() {
        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(true);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void activarPanelModificar() {
        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(true);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void activarPanelCrear() {
        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(true);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();

    }

    private void activarPanelInicio() {
        panel.panelComenzar.setVisible(true);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void activarPanelEliminar() {
        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(true);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void activarPanelVer() {

        panel.panelComenzar.setVisible(false);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(true);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);
        panel.updateContenido();
        eliminarTextBox();

    }

    private void eliminarTextBox() {
        panel.txt3Correo.setText("");
        panel.txt3buscar.setText("");
        panel.txt3contraseña.setText("");
        panel.txt3usuario.setText("");
        panel.txt5Area.setText("");
        panel.txt5Contraseña.setText("");
        panel.txt5Correo.setText("");
        panel.txt5Usuario.setText("");
        panel.txt6Correo.setText("");
        panel.txtA.setText("");
        panel.txtA3Descrip.setText("");
        panel.txtA6Descrip.setText("");

    }

    public void inicioPaneles() {
        panel.panelComenzar.setVisible(true);
        panel.panelCrear.setVisible(false);
        panel.panelVisualizar.setVisible(false);
        panel.panelEliminar.setVisible(false);
        panel.panelModificar.setVisible(false);
        panel.panelAyuda.setVisible(false);
        panel.panelConfiguracion.setVisible(false);

    }

    public void minimizarBordeVentana() {
        panel.jFrame2.setSize(panel.jFrame2.getMinimumSize().width, panel.jFrame2.getMinimumSize().height);
        panel.panelPersonalizado2.setSize(panel.jFrame2.getWidth(), panel.jFrame2.getHeight());

        panel.lblPrincipal.setBounds(panel.lblPrincipal.getX(), panel.lblPrincipal.getY(), panel.jFrame2.getWidth(), panel.lblPrincipal.getHeight());
        panel.lblMenu.setBounds(panel.lblMenu.getX(), panel.lblMenu.getY(), panel.lblMenu.getWidth(), panel.jFrame2.getHeight());

        int xButtonPositionCerrar = panel.jFrame2.getWidth() - panel.btnCerrar.getWidth() - 10;
        int yButtonPositionCerrar = 10;
        int xButtonPositionMaximizar = panel.jFrame2.getWidth() - panel.btnMaximizar.getWidth() - 40;
        int yButtonPositionMaximizar = 10;
        int xButtonPositionMinimizar = panel.jFrame2.getWidth() - panel.btnMinimizar.getWidth() - 70;
        int yButtonPositionMinimizar = 10;

        panel.btnCerrar.setBounds(xButtonPositionCerrar, yButtonPositionCerrar, panel.btnCerrar.getWidth(), panel.btnCerrar.getHeight());
        panel.btnMinimizar.setBounds(xButtonPositionMinimizar, yButtonPositionMinimizar, panel.btnMinimizar.getWidth(), panel.btnMinimizar.getHeight());
        panel.btnMaximizar.setBounds(xButtonPositionMaximizar, yButtonPositionMaximizar, panel.btnMaximizar.getWidth(), panel.btnMaximizar.getHeight());
        minimizarComenzar();
        minimizarCreacion();
        minimizarModificar();
        minimizarEliminar();
        minimizarVisualizar();
        minimizarAyuda();
        minimizarConfigurar();
        panel.panelPersonalizado2.revalidate();
        panel.panelPersonalizado2.repaint();
    }

    public void maximizarComponentes() {

        maximizarComenzar();
        maximizarCreacion();
        maximizarModificar();
        maximizarEliminar();
        maximizarVisualizar();
        maximizarAyuda();
        maximizarConfigurar();

    }

    private void maximizarComenzar() {
        panel.panelComenzar.setBounds(panel.panelComenzar.getX(), panel.panelComenzar.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight() + 100);

        panel.txtTtleInicio.setBounds(panel.txtTtleInicio.getX(), panel.txtTtleInicio.getY() - 40, panel.txtTtleInicio.getWidth() + 50, panel.txtTtleInicio.getHeight() + 50);
        panel.txtTtleInicio.setFont(new Font("Source Code Pro", Font.BOLD, 32));

        panel.lblImgU.setBounds(panel.lblImgU.getX(), panel.lblImgU.getY() + 50, panel.lblImgU.getWidth(), panel.lblImgU.getHeight());
        panel.lblP1Nombre.setBounds(panel.lblP1Nombre.getX(), panel.lblP1Nombre.getY() + 50, panel.lblP1Nombre.getWidth(), panel.lblP1Nombre.getHeight());
        panel.lblP1Correo.setBounds(panel.lblP1Correo.getX(), panel.lblP1Correo.getY() + 50, panel.lblP1Correo.getWidth(), panel.lblP1Correo.getHeight());
        panel.lblP1Cuentas.setBounds(panel.lblP1Cuentas.getX(), panel.lblP1Cuentas.getY() + 50, panel.lblP1Cuentas.getWidth(), panel.lblP1Cuentas.getHeight());
        panel.lblP1NumeroC.setBounds(panel.lblP1NumeroC.getX(), panel.lblP1NumeroC.getY() + 50, panel.lblP1NumeroC.getWidth(), panel.lblP1NumeroC.getHeight());
        panel.lblP1P1.setBounds(panel.lblP1P1.getX(), panel.lblP1P1.getY() + 50, panel.lblP1P1.getWidth(), panel.lblP1P1.getHeight());
        panel.lblP1P2.setBounds(panel.lblP1P2.getX(), panel.lblP1P2.getY() + 50, panel.lblP1P2.getWidth(), panel.lblP1P2.getHeight());
        panel.lblP1P3.setBounds(panel.lblP1P3.getX(), panel.lblP1P3.getY() + 50, panel.lblP1P3.getWidth(), panel.lblP1P3.getHeight());
        panel.lblP1P4.setBounds(panel.lblP1P4.getX(), panel.lblP1P4.getY() + 50, panel.lblP1P4.getWidth(), panel.lblP1P4.getHeight());
        panel.lblP1P5.setBounds(panel.lblP1P5.getX(), panel.lblP1P5.getY() + 50, panel.lblP1P5.getWidth(), panel.lblP1P5.getHeight());
        panel.lblP1P6.setBounds(panel.lblP1P6.getX(), panel.lblP1P6.getY() + 50, panel.lblP1P6.getWidth(), panel.lblP1P6.getHeight());
        panel.lblP1P7.setBounds(panel.lblP1P7.getX(), panel.lblP1P7.getY() + 50, panel.lblP1P7.getWidth(), panel.lblP1P7.getHeight());
        panel.lblQR.setBounds(panel.lblQR.getX(), panel.lblQR.getY() + 50, panel.lblQR.getWidth(), panel.lblQR.getHeight());

    }

    private void minimizarComenzar() {
        panel.panelComenzar.setBounds(panel.panelComenzar.getX(), panel.panelComenzar.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight() - 50);

        panel.txtTtleInicio.setBounds(panel.txtTtleInicio.getX(), panel.txtTtleInicio.getY() + 40, panel.txtTtleInicio.getWidth() - 50, panel.txtTtleInicio.getHeight() - 50);
        panel.txtTtleInicio.setFont(new Font("Source Code Pro", Font.BOLD, 18));

        panel.lblImgU.setBounds(panel.lblImgU.getX(), panel.lblImgU.getY() - 50, panel.lblImgU.getWidth(), panel.lblImgU.getHeight());
        panel.lblP1Nombre.setBounds(panel.lblP1Nombre.getX(), panel.lblP1Nombre.getY() - 50, panel.lblP1Nombre.getWidth(), panel.lblP1Nombre.getHeight());
        panel.lblP1Correo.setBounds(panel.lblP1Correo.getX(), panel.lblP1Correo.getY() - 50, panel.lblP1Correo.getWidth(), panel.lblP1Correo.getHeight());
        panel.lblP1Cuentas.setBounds(panel.lblP1Cuentas.getX(), panel.lblP1Cuentas.getY() - 50, panel.lblP1Cuentas.getWidth(), panel.lblP1Cuentas.getHeight());
        panel.lblP1NumeroC.setBounds(panel.lblP1NumeroC.getX(), panel.lblP1NumeroC.getY() - 50, panel.lblP1NumeroC.getWidth(), panel.lblP1NumeroC.getHeight());
        panel.lblP1P1.setBounds(panel.lblP1P1.getX(), panel.lblP1P1.getY() - 50, panel.lblP1P1.getWidth(), panel.lblP1P1.getHeight());
        panel.lblP1P2.setBounds(panel.lblP1P2.getX(), panel.lblP1P2.getY() - 50, panel.lblP1P2.getWidth(), panel.lblP1P2.getHeight());
        panel.lblP1P3.setBounds(panel.lblP1P3.getX(), panel.lblP1P3.getY() - 50, panel.lblP1P3.getWidth(), panel.lblP1P3.getHeight());
        panel.lblP1P4.setBounds(panel.lblP1P4.getX(), panel.lblP1P4.getY() - 50, panel.lblP1P4.getWidth(), panel.lblP1P4.getHeight());
        panel.lblP1P5.setBounds(panel.lblP1P5.getX(), panel.lblP1P5.getY() - 50, panel.lblP1P5.getWidth(), panel.lblP1P5.getHeight());
        panel.lblP1P6.setBounds(panel.lblP1P6.getX(), panel.lblP1P6.getY() - 50, panel.lblP1P6.getWidth(), panel.lblP1P6.getHeight());
        panel.lblP1P7.setBounds(panel.lblP1P7.getX(), panel.lblP1P7.getY() - 50, panel.lblP1P7.getWidth(), panel.lblP1P7.getHeight());
        panel.lblQR.setBounds(panel.lblQR.getX(), panel.lblQR.getY() - 50, panel.lblQR.getWidth(), panel.lblQR.getHeight());

    }

    private void maximizarCreacion() {

        panel.panelCrear.setBounds(panel.panelCrear.getX(), panel.panelCrear.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleCreacion.setBounds(panel.txtTtleCreacion.getX(), panel.txtTtleCreacion.getY() - 40, panel.txtTtleCreacion.getWidth() + 50, panel.txtTtleCreacion.getHeight() + 50);
        panel.txtTtleCreacion.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.txtNombreU.setBounds(panel.txtNombreU.getX(), panel.txtNombreU.getY() + 50, panel.txtNombreU.getWidth(), panel.txtNombreU.getHeight());
        panel.etNombreU.setBounds(panel.etNombreU.getX(), panel.etNombreU.getY() + 50, panel.etNombreU.getWidth(), panel.etNombreU.getHeight());
        panel.txtContraseñaU.setBounds(panel.txtContraseñaU.getX(), panel.txtContraseñaU.getY() + 50, panel.txtContraseñaU.getWidth(), panel.txtContraseñaU.getHeight());
        panel.etContraseña.setBounds(panel.etContraseña.getX(), panel.etContraseña.getY() + 50, panel.etContraseña.getWidth(), panel.etContraseña.getHeight());
        panel.txtCorreoU.setBounds(panel.txtCorreoU.getX(), panel.txtCorreoU.getY() + 50, panel.txtCorreoU.getWidth(), panel.txtCorreoU.getHeight());
        panel.etCorreoU.setBounds(panel.etCorreoU.getX(), panel.etCorreoU.getY() + 50, panel.etCorreoU.getWidth(), panel.etCorreoU.getHeight());
        panel.btnContraseña.setBounds(panel.btnContraseña.getX(), panel.btnContraseña.getY() + 50, panel.btnContraseña.getWidth(), panel.btnContraseña.getHeight());
        panel.txtBusquedaU.setBounds(panel.txtBusquedaU.getX(), panel.txtBusquedaU.getY() + 50, panel.txtBusquedaU.getWidth(), panel.txtBusquedaU.getHeight());
        panel.etBusquedaU.setBounds(panel.etBusquedaU.getX(), panel.etBusquedaU.getY() + 50, panel.etBusquedaU.getWidth(), panel.etBusquedaU.getHeight());
        panel.txtInicioU.setBounds(panel.txtInicioU.getX(), panel.txtInicioU.getY() + 50, panel.txtInicioU.getWidth(), panel.txtInicioU.getHeight());
        panel.rbInstaU.setBounds(panel.rbInstaU.getX(), panel.rbInstaU.getY() + 50, panel.rbInstaU.getWidth(), panel.rbInstaU.getHeight());
        panel.rbMailU.setBounds(panel.rbMailU.getX(), panel.rbMailU.getY() + 50, panel.rbMailU.getWidth(), panel.rbMailU.getHeight());
        panel.rbYTU.setBounds(panel.rbYTU.getX(), panel.rbYTU.getY() + 50, panel.rbYTU.getWidth(), panel.rbYTU.getHeight());
        panel.button1.setBounds(panel.button1.getX(), panel.button1.getY() + 50, panel.button1.getWidth(), panel.button1.getHeight());
        panel.txtDescripU.setBounds(panel.txtDescripU.getX(), panel.txtDescripU.getY(), panel.txtDescripU.getWidth(), panel.txtDescripU.getHeight());
        panel.txtA.setBounds(panel.txtA.getX(), panel.txtA.getY(), panel.txtA.getWidth(), panel.txtA.getHeight());
        panel.btnCrearU.setBounds(panel.btnCrearU.getX(), panel.btnCrearU.getY() + 50, panel.btnCrearU.getWidth(), panel.btnCrearU.getHeight());
        panel.btnVisualizarContra.setBounds(panel.btnVisualizarContra.getX(), panel.btnVisualizarContra.getY() + 50, panel.btnVisualizarContra.getWidth(), panel.btnVisualizarContra.getHeight());
    }

    private void minimizarCreacion() {

        panel.panelCrear.setBounds(panel.panelCrear.getX(), panel.panelCrear.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleCreacion.setBounds(panel.txtTtleCreacion.getX(), panel.txtTtleCreacion.getY() + 40, panel.txtTtleCreacion.getWidth() - 50, panel.txtTtleCreacion.getHeight() - 50);
        panel.txtTtleCreacion.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.txtNombreU.setBounds(panel.txtNombreU.getX(), panel.txtNombreU.getY() - 50, panel.txtNombreU.getWidth(), panel.txtNombreU.getHeight());
        panel.etNombreU.setBounds(panel.etNombreU.getX(), panel.etNombreU.getY() - 50, panel.etNombreU.getWidth(), panel.etNombreU.getHeight());
        panel.txtContraseñaU.setBounds(panel.txtContraseñaU.getX(), panel.txtContraseñaU.getY() - 50, panel.txtContraseñaU.getWidth(), panel.txtContraseñaU.getHeight());
        panel.etContraseña.setBounds(panel.etContraseña.getX(), panel.etContraseña.getY() - 50, panel.etContraseña.getWidth(), panel.etContraseña.getHeight());
        panel.txtCorreoU.setBounds(panel.txtCorreoU.getX(), panel.txtCorreoU.getY() - 50, panel.txtCorreoU.getWidth(), panel.txtCorreoU.getHeight());
        panel.etCorreoU.setBounds(panel.etCorreoU.getX(), panel.etCorreoU.getY() - 50, panel.etCorreoU.getWidth(), panel.etCorreoU.getHeight());
        panel.btnContraseña.setBounds(panel.btnContraseña.getX(), panel.btnContraseña.getY() - 50, panel.btnContraseña.getWidth(), panel.btnContraseña.getHeight());
        panel.txtBusquedaU.setBounds(panel.txtBusquedaU.getX(), panel.txtBusquedaU.getY() - 50, panel.txtBusquedaU.getWidth(), panel.txtBusquedaU.getHeight());
        panel.etBusquedaU.setBounds(panel.etBusquedaU.getX(), panel.etBusquedaU.getY() - 50, panel.etBusquedaU.getWidth(), panel.etBusquedaU.getHeight());
        panel.txtInicioU.setBounds(panel.txtInicioU.getX(), panel.txtInicioU.getY() - 50, panel.txtInicioU.getWidth(), panel.txtInicioU.getHeight());
        panel.rbInstaU.setBounds(panel.rbInstaU.getX(), panel.rbInstaU.getY() - 50, panel.rbInstaU.getWidth(), panel.rbInstaU.getHeight());
        panel.rbMailU.setBounds(panel.rbMailU.getX(), panel.rbMailU.getY() - 50, panel.rbMailU.getWidth(), panel.rbMailU.getHeight());
        panel.rbYTU.setBounds(panel.rbYTU.getX(), panel.rbYTU.getY() - 50, panel.rbYTU.getWidth(), panel.rbYTU.getHeight());
        panel.button1.setBounds(panel.button1.getX(), panel.button1.getY() - 50, panel.button1.getWidth(), panel.button1.getHeight());
        panel.txtDescripU.setBounds(panel.txtDescripU.getX(), panel.txtDescripU.getY(), panel.txtDescripU.getWidth(), panel.txtDescripU.getHeight());
        panel.txtA.setBounds(panel.txtA.getX(), panel.txtA.getY(), panel.txtA.getWidth(), panel.txtA.getHeight());
        panel.btnCrearU.setBounds(panel.btnCrearU.getX(), panel.btnCrearU.getY() - 50, panel.btnCrearU.getWidth(), panel.btnCrearU.getHeight());
        panel.btnVisualizarContra.setBounds(panel.btnVisualizarContra.getX(), panel.btnVisualizarContra.getY() - 50, panel.btnVisualizarContra.getWidth(), panel.btnVisualizarContra.getHeight());

    }

    private void maximizarModificar() {

        panel.panelModificar.setBounds(panel.panelModificar.getX(), panel.panelModificar.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleModificacion.setBounds(panel.txtTtleModificacion.getX(), panel.txtTtleModificacion.getY() - 30, panel.txtTtleModificacion.getWidth() + 100, panel.txtTtleModificacion.getHeight() + 50);
        panel.txtTtleModificacion.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.lbl5Usuario.setBounds(panel.lbl5Usuario.getX(), panel.lbl5Usuario.getY() + 50, panel.lbl5Usuario.getWidth(), panel.lbl5Usuario.getHeight());
        panel.txt5Usuario.setBounds(panel.txt5Usuario.getX(), panel.txt5Usuario.getY() + 50, panel.txt5Usuario.getWidth(), panel.txt5Usuario.getHeight());
        panel.lbl5Contraseña.setBounds(panel.lbl5Contraseña.getX(), panel.lbl5Contraseña.getY() + 50, panel.lbl5Contraseña.getWidth(), panel.lbl5Contraseña.getHeight());
        panel.txt5Contraseña.setBounds(panel.txt5Contraseña.getX(), panel.txt5Contraseña.getY() + 50, panel.txt5Contraseña.getWidth(), panel.txt5Contraseña.getHeight());
        panel.lbl5Correo.setBounds(panel.lbl5Correo.getX(), panel.lbl5Correo.getY() + 50, panel.lbl5Correo.getWidth(), panel.lbl5Correo.getHeight());
        panel.txt5Correo.setBounds(panel.txt5Correo.getX(), panel.txt5Correo.getY() + 50, panel.txt5Correo.getWidth(), panel.txt5Correo.getHeight());
        panel.lbl5Auto.setBounds(panel.lbl5Auto.getX(), panel.lbl5Auto.getY() + 50, panel.lbl5Auto.getWidth(), panel.lbl5Auto.getHeight());
        panel.chkInsta.setBounds(panel.chkInsta.getX(), panel.chkInsta.getY() + 50, panel.chkInsta.getWidth(), panel.chkInsta.getHeight());
        panel.chkGmail.setBounds(panel.chkGmail.getX(), panel.chkGmail.getY() + 50, panel.chkGmail.getWidth(), panel.chkGmail.getHeight());
        panel.chkYoutube.setBounds(panel.chkYoutube.getX(), panel.chkYoutube.getY() + 50, panel.chkYoutube.getWidth(), panel.chkYoutube.getHeight());
        panel.btnDesmarcar.setBounds(panel.btnDesmarcar.getX(), panel.btnDesmarcar.getY() + 50, panel.btnDesmarcar.getWidth(), panel.btnDesmarcar.getHeight());
        panel.btnModificar.setBounds(panel.btnModificar.getX(), panel.btnModificar.getY() + 50, panel.btnModificar.getWidth(), panel.btnModificar.getHeight());
        panel.jScrollPane6.setBounds(panel.jScrollPane6.getX(), panel.jScrollPane6.getY() + 50, panel.jScrollPane6.getWidth() + 200, panel.jScrollPane6.getHeight() + 100);
        panel.btnMostrar.setBounds(panel.btnMostrar.getX(), panel.btnMostrar.getY() + 50, panel.btnMostrar.getWidth(), panel.btnMostrar.getHeight());
        panel.jScrollPane7.setBounds(panel.jScrollPane7.getX(), panel.jScrollPane7.getY() + 50, panel.jScrollPane7.getWidth() + 100, panel.jScrollPane7.getHeight());
        panel.lbl5Descrip.setBounds(panel.lbl5Descrip.getX(), panel.lbl5Descrip.getY() + 50, panel.lbl5Descrip.getWidth(), panel.lbl5Descrip.getHeight());
    }

    private void minimizarModificar() {
        panel.panelModificar.setBounds(panel.panelModificar.getX(), panel.panelModificar.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleModificacion.setBounds(panel.txtTtleModificacion.getX(), panel.txtTtleModificacion.getY() + 30, panel.txtTtleModificacion.getWidth() - 100, panel.txtTtleModificacion.getHeight() - 50);
        panel.txtTtleModificacion.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.lbl5Usuario.setBounds(panel.lbl5Usuario.getX(), panel.lbl5Usuario.getY() - 50, panel.lbl5Usuario.getWidth(), panel.lbl5Usuario.getHeight());
        panel.txt5Usuario.setBounds(panel.txt5Usuario.getX(), panel.txt5Usuario.getY() - 50, panel.txt5Usuario.getWidth(), panel.txt5Usuario.getHeight());
        panel.lbl5Contraseña.setBounds(panel.lbl5Contraseña.getX(), panel.lbl5Contraseña.getY() - 50, panel.lbl5Contraseña.getWidth(), panel.lbl5Contraseña.getHeight());
        panel.txt5Contraseña.setBounds(panel.txt5Contraseña.getX(), panel.txt5Contraseña.getY() - 50, panel.txt5Contraseña.getWidth(), panel.txt5Contraseña.getHeight());
        panel.lbl5Correo.setBounds(panel.lbl5Correo.getX(), panel.lbl5Correo.getY() - 50, panel.lbl5Correo.getWidth(), panel.lbl5Correo.getHeight());
        panel.txt5Correo.setBounds(panel.txt5Correo.getX(), panel.txt5Correo.getY() - 50, panel.txt5Correo.getWidth(), panel.txt5Correo.getHeight());
        panel.lbl5Auto.setBounds(panel.lbl5Auto.getX(), panel.lbl5Auto.getY() - 50, panel.lbl5Auto.getWidth(), panel.lbl5Auto.getHeight());
        panel.chkInsta.setBounds(panel.chkInsta.getX(), panel.chkInsta.getY() - 50, panel.chkInsta.getWidth(), panel.chkInsta.getHeight());
        panel.chkGmail.setBounds(panel.chkGmail.getX(), panel.chkGmail.getY() - 50, panel.chkGmail.getWidth(), panel.chkGmail.getHeight());
        panel.chkYoutube.setBounds(panel.chkYoutube.getX(), panel.chkYoutube.getY() - 50, panel.chkYoutube.getWidth(), panel.chkYoutube.getHeight());
        panel.btnDesmarcar.setBounds(panel.btnDesmarcar.getX(), panel.btnDesmarcar.getY() - 50, panel.btnDesmarcar.getWidth(), panel.btnDesmarcar.getHeight());
        panel.btnModificar.setBounds(panel.btnModificar.getX(), panel.btnModificar.getY() - 50, panel.btnModificar.getWidth(), panel.btnModificar.getHeight());
        panel.jScrollPane6.setBounds(panel.jScrollPane6.getX(), panel.jScrollPane6.getY() - 50, panel.jScrollPane6.getWidth() - 200, panel.jScrollPane6.getHeight() - 100);
        panel.btnMostrar.setBounds(panel.btnMostrar.getX(), panel.btnMostrar.getY() - 50, panel.btnMostrar.getWidth(), panel.btnMostrar.getHeight());
        panel.jScrollPane7.setBounds(panel.jScrollPane7.getX(), panel.jScrollPane7.getY() - 50, panel.jScrollPane7.getWidth() - 100, panel.jScrollPane7.getHeight());
        panel.lbl5Descrip.setBounds(panel.lbl5Descrip.getX(), panel.lbl5Descrip.getY() - 50, panel.lbl5Descrip.getWidth(), panel.lbl5Descrip.getHeight());

    }

    private void maximizarVisualizar() {

        panel.panelVisualizar.setBounds(panel.panelVisualizar.getX(), panel.panelVisualizar.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleVisualizacion.setBounds(panel.txtTtleVisualizacion.getX(), panel.txtTtleVisualizacion.getY() - 30, panel.txtTtleVisualizacion.getWidth() + 100, panel.txtTtleVisualizacion.getHeight() + 50);
        panel.txtTtleVisualizacion.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.lbl3Visualizar.setBounds(panel.lbl3Visualizar.getX(), panel.lbl3Visualizar.getY() + 50, panel.lbl3Visualizar.getWidth(), panel.lbl3Visualizar.getHeight());
        panel.txt3buscar.setBounds(panel.txt3buscar.getX(), panel.txt3buscar.getY() + 50, panel.txt3buscar.getWidth(), panel.txt3buscar.getHeight());
        panel.btnDesplegar2.setBounds(panel.btnDesplegar2.getX(), panel.btnDesplegar2.getY() + 50, panel.btnDesplegar2.getWidth(), panel.btnDesplegar2.getHeight());
        panel.lbl3usuario.setBounds(panel.lbl3usuario.getX(), panel.lbl3usuario.getY() + 50, panel.lbl3usuario.getWidth(), panel.lbl3usuario.getHeight());
        panel.txt3usuario.setBounds(panel.txt3usuario.getX(), panel.txt3usuario.getY() + 50, panel.txt3usuario.getWidth(), panel.txt3usuario.getHeight());
        panel.lbl3contraseña.setBounds(panel.lbl3contraseña.getX(), panel.lbl3contraseña.getY() + 50, panel.lbl3contraseña.getWidth(), panel.lbl3contraseña.getHeight());
        panel.txt3contraseña.setBounds(panel.txt3contraseña.getX(), panel.txt3contraseña.getY() + 50, panel.txt3contraseña.getWidth(), panel.txt3contraseña.getHeight());
        panel.lbl3Correo.setBounds(panel.lbl3Correo.getX(), panel.lbl3Correo.getY() + 50, panel.lbl3Correo.getWidth(), panel.lbl3Correo.getHeight());
        panel.txt3Correo.setBounds(panel.txt3Correo.getX(), panel.txt3Correo.getY() + 50, panel.txt3Correo.getWidth(), panel.txt3Correo.getHeight());
        panel.lbl3Descrip.setBounds(panel.lbl3Descrip.getX(), panel.lbl3Descrip.getY() + 50, panel.lbl3Descrip.getWidth(), panel.lbl3Descrip.getHeight());
        panel.lbl3Descrip1.setBounds(panel.lbl3Descrip1.getX(), panel.lbl3Descrip1.getY() + 50, panel.lbl3Descrip1.getWidth(), panel.lbl3Descrip1.getHeight());
        panel.btnInsta.setBounds(panel.btnInsta.getX(), panel.btnInsta.getY() + 50, panel.btnInsta.getWidth(), panel.btnInsta.getHeight());
        panel.btnGmail.setBounds(panel.btnGmail.getX(), panel.btnGmail.getY() + 50, panel.btnGmail.getWidth(), panel.btnGmail.getHeight());
        panel.btnYt.setBounds(panel.btnYt.getX(), panel.btnYt.getY() + 50, panel.btnYt.getWidth(), panel.btnYt.getHeight());
        panel.jScrollPane2.setBounds(panel.jScrollPane2.getX() - 50, panel.jScrollPane2.getY() + 50, panel.jScrollPane2.getWidth() + 200, panel.jScrollPane2.getHeight() + 100);
        panel.btnVisualizar.setBounds(panel.btnVisualizar.getX(), panel.btnVisualizar.getY() + 50, panel.btnVisualizar.getWidth(), panel.btnVisualizar.getHeight());
        panel.jScrollPane3.setBounds(panel.jScrollPane3.getX(), panel.jScrollPane3.getY() + 50, panel.jScrollPane3.getWidth() + 100, panel.jScrollPane3.getHeight());

    }

    private void minimizarVisualizar() {
        panel.panelVisualizar.setBounds(panel.panelVisualizar.getX(), panel.panelVisualizar.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleVisualizacion.setBounds(panel.txtTtleVisualizacion.getX(), panel.txtTtleVisualizacion.getY() + 30, panel.txtTtleVisualizacion.getWidth() - 100, panel.txtTtleVisualizacion.getHeight() - 50);
        panel.txtTtleVisualizacion.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.lbl3Visualizar.setBounds(panel.lbl3Visualizar.getX(), panel.lbl3Visualizar.getY() - 50, panel.lbl3Visualizar.getWidth(), panel.lbl3Visualizar.getHeight());
        panel.txt3buscar.setBounds(panel.txt3buscar.getX(), panel.txt3buscar.getY() - 50, panel.txt3buscar.getWidth(), panel.txt3buscar.getHeight());
        panel.btnDesplegar2.setBounds(panel.btnDesplegar2.getX(), panel.btnDesplegar2.getY() - 50, panel.btnDesplegar2.getWidth(), panel.btnDesplegar2.getHeight());
        panel.lbl3usuario.setBounds(panel.lbl3usuario.getX(), panel.lbl3usuario.getY() - 50, panel.lbl3usuario.getWidth(), panel.lbl3usuario.getHeight());
        panel.txt3usuario.setBounds(panel.txt3usuario.getX(), panel.txt3usuario.getY() - 50, panel.txt3usuario.getWidth(), panel.txt3usuario.getHeight());
        panel.lbl3contraseña.setBounds(panel.lbl3contraseña.getX(), panel.lbl3contraseña.getY() - 50, panel.lbl3contraseña.getWidth(), panel.lbl3contraseña.getHeight());
        panel.txt3contraseña.setBounds(panel.txt3contraseña.getX(), panel.txt3contraseña.getY() - 50, panel.txt3contraseña.getWidth(), panel.txt3contraseña.getHeight());
        panel.lbl3Correo.setBounds(panel.lbl3Correo.getX(), panel.lbl3Correo.getY() - 50, panel.lbl3Correo.getWidth(), panel.lbl3Correo.getHeight());
        panel.txt3Correo.setBounds(panel.txt3Correo.getX(), panel.txt3Correo.getY() - 50, panel.txt3Correo.getWidth(), panel.txt3Correo.getHeight());
        panel.lbl3Descrip.setBounds(panel.lbl3Descrip.getX(), panel.lbl3Descrip.getY() - 50, panel.lbl3Descrip.getWidth(), panel.lbl3Descrip.getHeight());
        panel.lbl3Descrip1.setBounds(panel.lbl3Descrip1.getX(), panel.lbl3Descrip1.getY() - 50, panel.lbl3Descrip1.getWidth(), panel.lbl3Descrip1.getHeight());
        panel.btnInsta.setBounds(panel.btnInsta.getX(), panel.btnInsta.getY() - 50, panel.btnInsta.getWidth(), panel.btnInsta.getHeight());
        panel.btnGmail.setBounds(panel.btnGmail.getX(), panel.btnGmail.getY() - 50, panel.btnGmail.getWidth(), panel.btnGmail.getHeight());
        panel.btnYt.setBounds(panel.btnYt.getX(), panel.btnYt.getY() - 50, panel.btnYt.getWidth(), panel.btnYt.getHeight());
        panel.jScrollPane2.setBounds(panel.jScrollPane2.getX() + 50, panel.jScrollPane2.getY() - 50, panel.jScrollPane2.getWidth() - 200, panel.jScrollPane2.getHeight() - 100);
        panel.btnVisualizar.setBounds(panel.btnVisualizar.getX(), panel.btnVisualizar.getY() - 50, panel.btnVisualizar.getWidth(), panel.btnVisualizar.getHeight());
        panel.jScrollPane3.setBounds(panel.jScrollPane3.getX(), panel.jScrollPane3.getY() - 50, panel.jScrollPane3.getWidth(), panel.jScrollPane3.getHeight());

    }

    private void maximizarEliminar() {
        panel.panelEliminar.setBounds(panel.panelEliminar.getX(), panel.panelEliminar.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleEliminacion.setBounds(panel.txtTtleEliminacion.getX(), panel.txtTtleEliminacion.getY() - 30, panel.txtTtleEliminacion.getWidth() + 100, panel.txtTtleEliminacion.getHeight() + 50);
        panel.txtTtleEliminacion.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.jScrollPane5.setBounds(panel.jScrollPane5.getX() - 50, panel.jScrollPane5.getY(), panel.jScrollPane5.getWidth() + 200, panel.jScrollPane5.getHeight() + 100);
        panel.btnEliminar.setBounds(panel.btnEliminar.getX(), panel.btnEliminar.getY() + 140, panel.btnEliminar.getWidth(), panel.btnEliminar.getHeight());
    }

    private void minimizarEliminar() {
        panel.panelEliminar.setBounds(panel.panelEliminar.getX(), panel.panelEliminar.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleEliminacion.setBounds(panel.txtTtleEliminacion.getX(), panel.txtTtleEliminacion.getY() + 30, panel.txtTtleEliminacion.getWidth() - 100, panel.txtTtleEliminacion.getHeight() - 50);
        panel.txtTtleEliminacion.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.jScrollPane5.setBounds(panel.jScrollPane5.getX() + 50, panel.jScrollPane5.getY(), panel.jScrollPane5.getWidth() - 200, panel.jScrollPane5.getHeight() - 100);
        panel.btnEliminar.setBounds(panel.btnEliminar.getX(), panel.btnEliminar.getY() - 140, panel.btnEliminar.getWidth(), panel.btnEliminar.getHeight());
    }

    private void maximizarAyuda() {
        panel.panelAyuda.setBounds(panel.panelAyuda.getX(), panel.panelAyuda.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleAyuda.setBounds(panel.txtTtleAyuda.getX(), panel.txtTtleAyuda.getY() - 30, panel.txtTtleAyuda.getWidth() + 100, panel.txtTtleAyuda.getHeight() + 50);
        panel.txtTtleAyuda.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.lblIndicar.setBounds(panel.lblIndicar.getX(), panel.lblIndicar.getY() + 50, panel.lblIndicar.getWidth(), panel.lblIndicar.getHeight());
        panel.lbl6Correo.setBounds(panel.lbl6Correo.getX(), panel.lbl6Correo.getY() + 70, panel.lbl6Correo.getWidth(), panel.lbl6Correo.getHeight());
        panel.lbl6Descrip.setBounds(panel.lbl6Descrip.getX(), panel.lbl6Descrip.getY() + 70, panel.lbl6Descrip.getWidth(), panel.lbl6Descrip.getHeight());
        panel.txt6Correo.setBounds(panel.txt6Correo.getX(), panel.txt6Correo.getY() + 70, panel.txt6Correo.getWidth(), panel.txt6Correo.getHeight());
        panel.btnEnviar.setBounds(panel.btnEnviar.getX(), panel.btnEnviar.getY() + 70, panel.btnEnviar.getWidth(), panel.btnEnviar.getHeight());
        panel.jScrollPane4.setBounds(panel.jScrollPane4.getX(), panel.jScrollPane4.getY() + 70, panel.jScrollPane4.getWidth(), panel.jScrollPane4.getHeight());
    }

    private void minimizarAyuda() {
        panel.panelAyuda.setBounds(panel.panelAyuda.getX(), panel.panelAyuda.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleAyuda.setBounds(panel.txtTtleAyuda.getX(), panel.txtTtleAyuda.getY() + 30, panel.txtTtleAyuda.getWidth() - 100, panel.txtTtleAyuda.getHeight() - 50);
        panel.txtTtleAyuda.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.lblIndicar.setBounds(panel.lblIndicar.getX(), panel.lblIndicar.getY() - 50, panel.lblIndicar.getWidth(), panel.lblIndicar.getHeight());
        panel.lbl6Correo.setBounds(panel.lbl6Correo.getX(), panel.lbl6Correo.getY() - 70, panel.lbl6Correo.getWidth(), panel.lbl6Correo.getHeight());
        panel.lbl6Descrip.setBounds(panel.lbl6Descrip.getX(), panel.lbl6Descrip.getY() - 70, panel.lbl6Descrip.getWidth(), panel.lbl6Descrip.getHeight());
        panel.txt6Correo.setBounds(panel.txt6Correo.getX(), panel.txt6Correo.getY() - 70, panel.txt6Correo.getWidth(), panel.txt6Correo.getHeight());
        panel.btnEnviar.setBounds(panel.btnEnviar.getX(), panel.btnEnviar.getY() - 70, panel.btnEnviar.getWidth(), panel.btnEnviar.getHeight());
        panel.jScrollPane4.setBounds(panel.jScrollPane4.getX(), panel.jScrollPane4.getY() - 70, panel.jScrollPane4.getWidth(), panel.jScrollPane4.getHeight());
    }

    private void maximizarConfigurar() {
        panel.panelConfiguracion.setBounds(panel.panelConfiguracion.getX(), panel.panelConfiguracion.getY() - 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleConfig.setBounds(panel.txtTtleConfig.getX(), panel.txtTtleConfig.getY() - 30, panel.txtTtleConfig.getWidth() + 100, panel.txtTtleConfig.getHeight() + 50);
        panel.txtTtleConfig.setFont(new Font("Source Code Pro", Font.BOLD, 32));
        panel.lbl7Color.setBounds(panel.lbl7Color.getX(), panel.lbl7Color.getY() + 50, panel.lbl7Color.getWidth(), panel.lbl7Color.getHeight());
        panel.jButton1.setBounds(panel.jButton1.getX(), panel.jButton1.getY() + 50, panel.jButton1.getWidth(), panel.jButton1.getHeight());
        panel.jButton2.setBounds(panel.jButton2.getX(), panel.jButton2.getY() + 50, panel.jButton2.getWidth(), panel.jButton2.getHeight());
        panel.jButton3.setBounds(panel.jButton3.getX(), panel.jButton3.getY() + 50, panel.jButton3.getWidth(), panel.jButton3.getHeight());
        panel.jButton4.setBounds(panel.jButton4.getX(), panel.jButton4.getY() + 50, panel.jButton4.getWidth(), panel.jButton4.getHeight());
        panel.jButton5.setBounds(panel.jButton5.getX(), panel.jButton5.getY() + 50, panel.jButton5.getWidth(), panel.jButton5.getHeight());
        panel.jButton6.setBounds(panel.jButton6.getX(), panel.jButton6.getY() + 50, panel.jButton6.getWidth(), panel.jButton6.getHeight());
        panel.jButton7.setBounds(panel.jButton7.getX(), panel.jButton7.getY() + 50, panel.jButton7.getWidth(), panel.jButton7.getHeight());
        panel.jButton8.setBounds(panel.jButton8.getX(), panel.jButton8.getY() + 50, panel.jButton8.getWidth(), panel.jButton8.getHeight());
        panel.jButton9.setBounds(panel.jButton9.getX(), panel.jButton9.getY() + 50, panel.jButton9.getWidth(), panel.jButton9.getHeight());
        panel.jButton10.setBounds(panel.jButton10.getX(), panel.jButton10.getY() + 50, panel.jButton10.getWidth(), panel.jButton10.getHeight());
        panel.jButton11.setBounds(panel.jButton11.getX(), panel.jButton11.getY() + 50, panel.jButton11.getWidth(), panel.jButton11.getHeight());
        panel.jButton12.setBounds(panel.jButton12.getX(), panel.jButton12.getY() + 50, panel.jButton12.getWidth(), panel.jButton12.getHeight());
        panel.lbl7gmail.setBounds(panel.lbl7gmail.getX(), panel.lbl7gmail.getY() + 100, panel.lbl7gmail.getWidth(), panel.lbl7gmail.getHeight());
        panel.jTextField1.setBounds(panel.jTextField1.getX(), panel.jTextField1.getY() + 100, panel.jTextField1.getWidth(), panel.jTextField1.getHeight());
        panel.lbl7Contra.setBounds(panel.lbl7Contra.getX(), panel.lbl7Contra.getY() + 100, panel.lbl7Contra.getWidth(), panel.lbl7Contra.getHeight());
        panel.jTextField2.setBounds(panel.jTextField2.getX(), panel.jTextField2.getY() + 100, panel.jTextField2.getWidth(), panel.jTextField2.getHeight());
        panel.lbl7Exportar.setBounds(panel.lbl7Exportar.getX(), panel.lbl7Exportar.getY() + 100, panel.lbl7Exportar.getWidth(), panel.lbl7Exportar.getHeight());
        panel.btnCrearU2.setBounds(panel.btnCrearU2.getX(), panel.btnCrearU2.getY() + 100, panel.btnCrearU2.getWidth(), panel.btnCrearU2.getHeight());
        panel.lbl7Importar.setBounds(panel.lbl7Importar.getX(), panel.lbl7Importar.getY() + 100, panel.lbl7Importar.getWidth(), panel.lbl7Importar.getHeight());
        panel.lbl7Color1.setBounds(panel.lbl7Color1.getX(), panel.lbl7Color1.getY() + 130, panel.lbl7Color1.getWidth(), panel.lbl7Color1.getHeight());
        panel.btnCrearU3.setBounds(panel.btnCrearU3.getX(), panel.btnCrearU3.getY() + 100, panel.btnCrearU3.getWidth(), panel.btnCrearU3.getHeight());
        panel.btnAceptarU4.setBounds(panel.btnAceptarU4.getX(), panel.btnAceptarU4.getY() + 130, panel.btnAceptarU4.getWidth(), panel.btnAceptarU4.getHeight());
        panel.btnCrearU1.setBounds(panel.btnCrearU1.getX(), panel.btnCrearU1.getY() + 130, panel.btnCrearU1.getWidth(), panel.btnCrearU1.getHeight());
        panel.btnCrearU5.setBounds(panel.btnCrearU5.getX(), panel.btnCrearU5.getY() + 130, panel.btnCrearU5.getWidth(), panel.btnCrearU5.getHeight());
    }

    private void minimizarConfigurar() {
        panel.panelConfiguracion.setBounds(panel.panelConfiguracion.getX(), panel.panelConfiguracion.getY() + 50, panel.jFrame2.getWidth(), panel.jFrame2.getHeight());
        panel.txtTtleConfig.setBounds(panel.txtTtleConfig.getX(), panel.txtTtleConfig.getY() + 30, panel.txtTtleConfig.getWidth() - 100, panel.txtTtleConfig.getHeight() - 50);
        panel.txtTtleConfig.setFont(new Font("Source Code Pro", Font.BOLD, 18));
        panel.lbl7Color.setBounds(panel.lbl7Color.getX(), panel.lbl7Color.getY() - 50, panel.lbl7Color.getWidth(), panel.lbl7Color.getHeight());
        panel.jButton1.setBounds(panel.jButton1.getX(), panel.jButton1.getY() - 50, panel.jButton1.getWidth(), panel.jButton1.getHeight());
        panel.jButton2.setBounds(panel.jButton2.getX(), panel.jButton2.getY() - 50, panel.jButton2.getWidth(), panel.jButton2.getHeight());
        panel.jButton3.setBounds(panel.jButton3.getX(), panel.jButton3.getY() - 50, panel.jButton3.getWidth(), panel.jButton3.getHeight());
        panel.jButton4.setBounds(panel.jButton4.getX(), panel.jButton4.getY() - 50, panel.jButton4.getWidth(), panel.jButton4.getHeight());
        panel.jButton5.setBounds(panel.jButton5.getX(), panel.jButton5.getY() - 50, panel.jButton5.getWidth(), panel.jButton5.getHeight());
        panel.jButton6.setBounds(panel.jButton6.getX(), panel.jButton6.getY() - 50, panel.jButton6.getWidth(), panel.jButton6.getHeight());
        panel.jButton7.setBounds(panel.jButton7.getX(), panel.jButton7.getY() - 50, panel.jButton7.getWidth(), panel.jButton7.getHeight());
        panel.jButton8.setBounds(panel.jButton8.getX(), panel.jButton8.getY() - 50, panel.jButton8.getWidth(), panel.jButton8.getHeight());
        panel.jButton9.setBounds(panel.jButton9.getX(), panel.jButton9.getY() - 50, panel.jButton9.getWidth(), panel.jButton9.getHeight());
        panel.jButton10.setBounds(panel.jButton10.getX(), panel.jButton10.getY() - 50, panel.jButton10.getWidth(), panel.jButton10.getHeight());
        panel.jButton11.setBounds(panel.jButton11.getX(), panel.jButton11.getY() - 50, panel.jButton11.getWidth(), panel.jButton11.getHeight());
        panel.jButton12.setBounds(panel.jButton12.getX(), panel.jButton12.getY() - 50, panel.jButton12.getWidth(), panel.jButton12.getHeight());
        panel.lbl7gmail.setBounds(panel.lbl7gmail.getX(), panel.lbl7gmail.getY() - 100, panel.lbl7gmail.getWidth(), panel.lbl7gmail.getHeight());
        panel.jTextField1.setBounds(panel.jTextField1.getX(), panel.jTextField1.getY() - 100, panel.jTextField1.getWidth(), panel.jTextField1.getHeight());
        panel.lbl7Contra.setBounds(panel.lbl7Contra.getX(), panel.lbl7Contra.getY() - 100, panel.lbl7Contra.getWidth(), panel.lbl7Contra.getHeight());
        panel.jTextField2.setBounds(panel.jTextField2.getX(), panel.jTextField2.getY() - 100, panel.jTextField2.getWidth(), panel.jTextField2.getHeight());
        panel.lbl7Exportar.setBounds(panel.lbl7Exportar.getX(), panel.lbl7Exportar.getY() - 100, panel.lbl7Exportar.getWidth(), panel.lbl7Exportar.getHeight());
        panel.btnCrearU2.setBounds(panel.btnCrearU2.getX(), panel.btnCrearU2.getY() - 100, panel.btnCrearU2.getWidth(), panel.btnCrearU2.getHeight());
        panel.lbl7Importar.setBounds(panel.lbl7Importar.getX(), panel.lbl7Importar.getY() - 100, panel.lbl7Importar.getWidth(), panel.lbl7Importar.getHeight());
        panel.lbl7Color1.setBounds(panel.lbl7Color1.getX(), panel.lbl7Color1.getY() - 130, panel.lbl7Color1.getWidth(), panel.lbl7Color1.getHeight());
        panel.btnCrearU3.setBounds(panel.btnCrearU3.getX(), panel.btnCrearU3.getY() - 100, panel.btnCrearU3.getWidth(), panel.btnCrearU3.getHeight());
        panel.btnCrearU1.setBounds(panel.btnCrearU1.getX(), panel.btnCrearU1.getY() - 130, panel.btnCrearU1.getWidth(), panel.btnCrearU1.getHeight());
        panel.btnCrearU5.setBounds(panel.btnCrearU5.getX(), panel.btnCrearU5.getY() - 130, panel.btnCrearU5.getWidth(), panel.btnCrearU5.getHeight());
        panel.btnAceptarU4.setBounds(panel.btnAceptarU4.getX(), panel.btnAceptarU4.getY() - 130, panel.btnAceptarU4.getWidth(), panel.btnAceptarU4.getHeight());

    }

    public void ocultarElementosConfigurar() {
        panel.txtTtleConfig.setVisible(false);
        panel.lbl7Color.setVisible(false);
        panel.jButton1.setVisible(false);
        panel.jButton2.setVisible(false);
        panel.jButton3.setVisible(false);
        panel.jButton4.setVisible(false);
        panel.jButton5.setVisible(false);
        panel.jButton6.setVisible(false);
        panel.jButton7.setVisible(false);
        panel.jButton8.setVisible(false);
        panel.jButton9.setVisible(false);
        panel.jButton10.setVisible(false);
        panel.jButton11.setVisible(false);
        panel.jButton12.setVisible(false);
        panel.lbl7gmail.setVisible(false);
        panel.jTextField1.setVisible(false);
        panel.lbl7Contra.setVisible(false);
        panel.jTextField2.setVisible(false);
        panel.lbl7Exportar.setVisible(false);
        panel.btnCrearU2.setVisible(false);
        panel.lbl7Importar.setVisible(false);
        panel.lbl7Color1.setVisible(false);
        panel.btnCrearU3.setVisible(false);
        panel.lbl7Color1.setVisible(false);
        panel.btnCrearU1.setVisible(false);
        panel.btnCrearU5.setVisible(false);
        panel.btnAceptarU4.setVisible(false);
    }

    public void visibleElementosConfiguracion() {

        panel.txtTtleConfig.setVisible(true);
        panel.lbl7Color.setVisible(true);
        panel.jButton1.setVisible(true);
        panel.jButton2.setVisible(true);
        panel.jButton3.setVisible(true);
        panel.jButton4.setVisible(true);
        panel.jButton5.setVisible(true);
        panel.jButton6.setVisible(true);
        panel.jButton7.setVisible(true);
        panel.jButton8.setVisible(true);
        panel.jButton9.setVisible(true);
        panel.jButton10.setVisible(true);
        panel.jButton11.setVisible(true);
        panel.jButton12.setVisible(true);
        panel.lbl7gmail.setVisible(true);
        panel.jTextField1.setVisible(true);
        panel.lbl7Contra.setVisible(true);
        panel.jTextField2.setVisible(true);
        panel.lbl7Exportar.setVisible(true);
        panel.btnCrearU2.setVisible(true);
        panel.lbl7Importar.setVisible(true);
        panel.lbl7Color1.setVisible(true);
        panel.btnCrearU3.setVisible(true);
        panel.lbl7Color1.setVisible(true);
        panel.btnCrearU1.setVisible(true);
        panel.btnCrearU5.setVisible(true);
        panel.btnAceptarU4.setVisible(true);

    }

    public void panelNull() {
        panel.panelPersonalizado2.setLayout(null);
        panel.panelVisualizar.setLayout(null);
        panel.panelModificar.setLayout(null);
        panel.panelComenzar.setLayout(null);
        panel.panelEliminar.setLayout(null);
        panel.panelCrear.setLayout(null);
        panel.panelAyuda.setLayout(null);
        panel.panelConfiguracion.setLayout(null);
        panel.jFileChooser2.setLayout(null);
    }

    public void maximizarBordeVentana() {
        panel.jFrame2.setExtendedState(JFrame.MAXIMIZED_BOTH);

        panel.panelPersonalizado2.setSize(panel.jFrame2.getWidth(), panel.jFrame2.getHeight());

        panel.lblPrincipal.setBounds(panel.lblPrincipal.getX(), panel.lblPrincipal.getY(), panel.jFrame2.getWidth(), panel.lblPrincipal.getHeight());
        panel.lblMenu.setBounds(panel.lblMenu.getX(), panel.lblMenu.getY(), panel.lblMenu.getWidth(), panel.jFrame2.getHeight());

        int xButtonPositionCerrar = panel.jFrame2.getWidth() - panel.btnCerrar.getWidth() - 10;
        int yButtonPositionCerrar = 10;
        int xButtonPositionMaximizar = panel.jFrame2.getWidth() - panel.btnMaximizar.getWidth() - 40;
        int yButtonPositionMaximizar = 10;
        int xButtonPositionMinimizar = panel.jFrame2.getWidth() - panel.btnMinimizar.getWidth() - 70;
        int yButtonPositionMinimizar = 10;

        panel.btnCerrar.setBounds(xButtonPositionCerrar, yButtonPositionCerrar, panel.btnCerrar.getWidth(), panel.btnCerrar.getHeight());
        panel.btnMinimizar.setBounds(xButtonPositionMinimizar, yButtonPositionMinimizar, panel.btnMinimizar.getWidth(), panel.btnMinimizar.getHeight());
        panel.btnMaximizar.setBounds(xButtonPositionMaximizar, yButtonPositionMaximizar, panel.btnMaximizar.getWidth(), panel.btnMaximizar.getHeight());
        panel.jFrame2.revalidate();
        panel.jFrame2.repaint();
    }

    public void centrarPanel() {

        int panelX = (panel.jFrame2.getContentPane().getWidth() - panel.panelComenzar.getWidth()) / 2;
        int panelY = (panel.jFrame2.getContentPane().getHeight() - panel.panelComenzar.getHeight()) / 2;

        panel.panelComenzar.setBounds(panelX, panelY, panel.panelComenzar.getWidth(), panel.panelComenzar.getHeight());
        panel.panelCrear.setBounds(panelX, panelY, panel.panelCrear.getWidth(), panel.panelCrear.getHeight());
        panel.panelVisualizar.setBounds(panelX, panelY, panel.panelVisualizar.getWidth(), panel.panelVisualizar.getHeight());
        panel.panelEliminar.setBounds(panelX, panelY, panel.panelEliminar.getWidth(), panel.panelEliminar.getHeight());
        panel.panelModificar.setBounds(panelX, panelY, panel.panelModificar.getWidth(), panel.panelModificar.getHeight());
        panel.panelAyuda.setBounds(panelX, panelY, panel.panelAyuda.getWidth(), panel.panelAyuda.getHeight());
        panel.panelConfiguracion.setBounds(panelX, panelY, panel.panelConfiguracion.getWidth(), panel.panelConfiguracion.getHeight());
        panel.jFileChooser2.setBounds(50, 60, panel.jFileChooser2.getWidth(), panel.jFileChooser2.getHeight());
        panel.jFrame2.revalidate();
        panel.jFrame2.repaint();

    }

    public void incialPanel() {
        panel.panelComenzar.setBounds(50, 60, panel.panelComenzar.getWidth(), panel.panelComenzar.getHeight());
        panel.panelCrear.setBounds(50, 60, panel.panelCrear.getWidth(), panel.panelCrear.getHeight());
        panel.panelVisualizar.setBounds(50, 60, panel.panelVisualizar.getWidth(), panel.panelVisualizar.getHeight());
        panel.panelEliminar.setBounds(50, 60, panel.panelEliminar.getWidth(), panel.panelEliminar.getHeight());
        panel.panelModificar.setBounds(50, 60, panel.panelModificar.getWidth(), panel.panelModificar.getHeight());
        panel.panelAyuda.setBounds(50, 60, panel.panelAyuda.getWidth(), panel.panelAyuda.getHeight());
        panel.panelConfiguracion.setBounds(50, 60, panel.panelConfiguracion.getWidth(), panel.panelConfiguracion.getHeight());
        panel.jFileChooser2.setBounds(50, 60, panel.jFileChooser2.getWidth(), panel.jFileChooser2.getHeight());

        panel.jFrame2.revalidate();
        panel.jFrame2.repaint();

    }

    public void visibleComponente(boolean comp) {

        panel.lblMenuVer.setVisible(comp);
        panel.lblMenuCrear.setVisible(comp);
        panel.lblInicio.setVisible(comp);
        panel.lblMenuEliminar.setVisible(comp);
        panel.lblMenuModificar.setVisible(comp);
        panel.lblMenuAyuda.setVisible(comp);
        panel.lblMenuConfi.setVisible(comp);

        panel.lblImgInicio.setVisible(comp);
        panel.lblImgCrear.setVisible(comp);
        panel.lblImgVer.setVisible(comp);
        panel.lblImgEliminar.setVisible(comp);
        panel.lblImgModificar.setVisible(comp);
        panel.lblImgAyuda.setVisible(comp);
        panel.lblImgConfi.setVisible(comp);

    }

    public void invisibleComponentesInicio() {
        panel.jFrame2.setVisible(true);
        panel.jFrame2.setVisible(false);
        panel.lblUsuario.setVisible(false);
        panel.lblPin.setVisible(false);
        panel.lblCorreo.setVisible(false);
        panel.lblCodigo.setVisible(false);
        panel.jFileChooser2.setVisible(false);

        visibleComponente(false);

        panel.txtUsuario.setVisible(false);
        panel.txtUsuario.setText("");
        panel.txtPin.setVisible(false);
        panel.txtPin.setText("");
        panel.txtCorreo.setVisible(false);
        panel.txtCorreo.setText("");
        panel.txtCodigo.setVisible(false);
        panel.txtCodigo.setText("");

        panel.btnAtras.setVisible(false);
        panel.btnCreado.setVisible(false);
        panel.btnInicio.setVisible(false);
        panel.btnEnvio.setVisible(false);

    }

    public void componentesVisibles() {
        panel.lblInicio.setBounds(panel.lblInicio.getX() + 30, panel.lblInicio.getY(), panel.lblInicio.getWidth(), panel.lblInicio.getHeight());
        panel.lblMenuCrear.setBounds(panel.lblMenuCrear.getX() + 30, panel.lblMenuCrear.getY(), panel.lblMenuCrear.getWidth(), panel.lblMenuCrear.getHeight());
        panel.lblMenuVer.setBounds(panel.lblMenuVer.getX() + 30, panel.lblMenuVer.getY(), panel.lblMenuVer.getWidth(), panel.lblMenuVer.getHeight());
        panel.lblMenuEliminar.setBounds(panel.lblMenuEliminar.getX() + 30, panel.lblMenuEliminar.getY(), panel.lblMenuEliminar.getWidth(), panel.lblMenuEliminar.getHeight());
        panel.lblMenuModificar.setBounds(panel.lblMenuModificar.getX() + 30, panel.lblMenuModificar.getY(), panel.lblMenuModificar.getWidth(), panel.lblMenuModificar.getHeight());
        panel.lblImgCrear.setBounds(panel.lblImgCrear.getX() + 20, panel.lblImgCrear.getY(), panel.lblImgCrear.getWidth(), panel.lblImgCrear.getHeight());
        panel.lblImgVer.setBounds(panel.lblImgInicio.getX() + 20, panel.lblImgVer.getY(), panel.lblImgVer.getWidth(), panel.lblImgVer.getHeight());
        panel.lblImgInicio.setBounds(panel.lblImgInicio.getX() + 20, panel.lblImgInicio.getY(), panel.lblImgInicio.getWidth(), panel.lblImgInicio.getHeight());
        panel.lblImgEliminar.setBounds(panel.lblImgEliminar.getX() + 20, panel.lblImgEliminar.getY(), panel.lblImgEliminar.getWidth(), panel.lblImgEliminar.getHeight());
        panel.lblImgModificar.setBounds(panel.lblImgModificar.getX() + 20, panel.lblImgModificar.getY(), panel.lblImgModificar.getWidth(), panel.lblImgModificar.getHeight());
        panel.lblImgAyuda.setBounds(panel.lblImgAyuda.getX() + 20, panel.lblImgAyuda.getY(), panel.lblImgAyuda.getWidth(), panel.lblImgAyuda.getHeight());
        panel.lblMenuAyuda.setBounds(panel.lblMenuAyuda.getX() + 30, panel.lblMenuAyuda.getY(), panel.lblMenuAyuda.getWidth(), panel.lblMenuAyuda.getHeight());
        panel.lblImgConfi.setBounds(panel.lblImgConfi.getX() + 20, panel.lblImgConfi.getY(), panel.lblImgConfi.getWidth(), panel.lblImgConfi.getHeight());
        panel.lblMenuConfi.setBounds(panel.lblMenuConfi.getX() + 30, panel.lblMenuConfi.getY(), panel.lblMenuConfi.getWidth(), panel.lblMenuConfi.getHeight());

    }

    public void componentesInvisibles() {
        panel.lblInicio.setBounds(panel.lblInicio.getX() - 30, panel.lblInicio.getY(), panel.lblInicio.getWidth(), panel.lblInicio.getHeight());
        panel.lblMenuCrear.setBounds(panel.lblMenuCrear.getX() - 30, panel.lblMenuCrear.getY(), panel.lblMenuCrear.getWidth(), panel.lblMenuCrear.getHeight());
        panel.lblMenuVer.setBounds(panel.lblMenuVer.getX() - 30, panel.lblMenuVer.getY(), panel.lblMenuVer.getWidth(), panel.lblMenuVer.getHeight());
        panel.lblMenuEliminar.setBounds(panel.lblMenuEliminar.getX() - 30, panel.lblMenuEliminar.getY(), panel.lblMenuEliminar.getWidth(), panel.lblMenuEliminar.getHeight());
        panel.lblMenuModificar.setBounds(panel.lblMenuModificar.getX() - 30, panel.lblMenuModificar.getY(), panel.lblMenuModificar.getWidth(), panel.lblMenuModificar.getHeight());
        panel.lblImgCrear.setBounds(panel.lblImgCrear.getX() - 20, panel.lblImgCrear.getY(), panel.lblImgCrear.getWidth(), panel.lblImgCrear.getHeight());
        panel.lblImgVer.setBounds(panel.lblImgInicio.getX() - 20, panel.lblImgVer.getY(), panel.lblImgVer.getWidth(), panel.lblImgVer.getHeight());
        panel.lblImgInicio.setBounds(panel.lblImgInicio.getX() - 20, panel.lblImgInicio.getY(), panel.lblImgInicio.getWidth(), panel.lblImgInicio.getHeight());
        panel.lblImgEliminar.setBounds(panel.lblImgEliminar.getX() - 20, panel.lblImgEliminar.getY(), panel.lblImgEliminar.getWidth(), panel.lblImgEliminar.getHeight());
        panel.lblImgModificar.setBounds(panel.lblImgModificar.getX() - 20, panel.lblImgModificar.getY(), panel.lblImgModificar.getWidth(), panel.lblImgModificar.getHeight());
        panel.lblImgAyuda.setBounds(panel.lblImgAyuda.getX() - 20, panel.lblImgAyuda.getY(), panel.lblImgAyuda.getWidth(), panel.lblImgAyuda.getHeight());
        panel.lblMenuAyuda.setBounds(panel.lblMenuAyuda.getX() - 30, panel.lblMenuAyuda.getY(), panel.lblMenuAyuda.getWidth(), panel.lblMenuAyuda.getHeight());
        panel.lblImgConfi.setBounds(panel.lblImgConfi.getX() - 20, panel.lblImgConfi.getY(), panel.lblImgConfi.getWidth(), panel.lblImgConfi.getHeight());
        panel.lblMenuConfi.setBounds(panel.lblMenuConfi.getX() - 30, panel.lblMenuConfi.getY(), panel.lblMenuConfi.getWidth(), panel.lblMenuConfi.getHeight());
    }

}
