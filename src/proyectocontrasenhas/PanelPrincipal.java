/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectocontrasenhas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

/**
 *
 * @author micro
 */
public class PanelPrincipal extends javax.swing.JFrame {

    protected String refeEliminar;
    protected String refeModificar;
    protected final int FILA_1 = 1;
    protected final int FILA_0 = 0;
    protected final int FILA_2 = 2;
    protected final int FILA_3 = 3;
    private GestionBaseDatos gbd = new GestionBaseDatos();
    private GestionInicioSesion gis = new GestionInicioSesion();
    private InicioAutomatico ia = new InicioAutomatico();
    private Ficheros f = new Ficheros();
    private DefaultTableModel tableModel1;
    private Object[] objectTable1 = new Object[4];
    private ArrayList<Cuentas> arrayCuentasLeidas;
    private int codigoGmail;
    private Point initialClick;
    private boolean retrocederMenu = false;
    private boolean retrocederPantalla = false;
    private boolean visualizarContra = false;
    private boolean instagram = false;
    private boolean youtube = false;
    private boolean gmail = false;
    public String uuid = "";
    protected ArrayList<Cuentas> arrayCuentas;
    private ArrayList<Cuentas> arrayCompletaCuentas;
    private JList<String> listaAutoCompletado = new JList<>();
    private UUIDManager uuidm = new UUIDManager();
    private GestionTablas gt = new GestionTablas(this);
    private GestionPaneles gp = new GestionPaneles(this);

    /**
     * Creates new form PanelPrincipal
     *
     * @throws java.io.IOException
     */
    public PanelPrincipal() throws WriterException {
        uuid = uuidm.getOrCreateUUID().toString();
        arrayCuentas = gbd.almacenarCuentasArray(uuid);
        initComponents();

        jFrame2.setLocationRelativeTo(null);
        deleteQRFile("imgQR.png");

        lblPrincipal.setPreferredSize(new Dimension(930, 50));
        lblMenu.setPreferredSize(new Dimension(50, 460));

        redondasEsquinas();
        gp.invisibleComponentesInicio();
        añadirQR();
        updateContenido();
        gt.referenciaEliminarCuenta();
        gt.referenciaModificar();
        gp.inicioPaneles();
        gp.panelNull();

        movimientoPanelRaton();
        gp.cambioPaneles();
        autoCompletadoTexto();

    }

    private void movimientoPanelRaton() {

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                ratonPresionado(evt);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                ratonMovido(evt);
            }
        });
        jFrame2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                ratonPresionado(evt);
            }
        });
        jFrame2.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                ratonMovido(evt);
            }
        });
    }

    private void deleteQRFile(String filePath) {
        File fileToDelete = new File(filePath);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }

    private void añadirQR() throws WriterException {
        try {
            generarQR("https://github.com/JJ27biza/LockBoxMobile/releases", 150, 150, "imgQR.png");
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("imgQR.png")));
            lblQR.setIcon(icon);
            panelPersonalizado2.repaint();
            panelPersonalizado2.revalidate();
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateContenido() {
        visualizarContra = false;
        arrayCuentas.clear();
        arrayCuentas = gbd.almacenarCuentasArray(uuid);
        gt.añadiTablaVisualizar();
        gt.añadirTablaEliminar();
        gt.añadirTablaModificar();
        gt.datosTablaACamposVisualizar();
        gt.datosTablaACamposModificar();

    }

    private void redondasEsquinas() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rounded Corner Frame");
        setPreferredSize(new java.awt.Dimension(900, 800));
        Shape roundedShape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        setShape(roundedShape);
        setLocationRelativeTo(null);
        pack();
    }

    private void ratonPresionado(MouseEvent evt) {
        initialClick = evt.getPoint();
        getComponentAt(initialClick);
        jFrame2.getComponentAt(initialClick);
    }

    private void ratonMovido(MouseEvent evt) {
        if (initialClick != null) {
            int thisX = getLocation().x;
            int thisY = getLocation().y;

            int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
            int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            setLocation(X, Y);
            jFrame2.setLocation(X, Y);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame2 = new javax.swing.JFrame();
        panelPersonalizado2 = new proyectocontrasenhas.PanelPersonalizado();
        btnDesplegar = new javax.swing.JButton();
        lblImgCrear = new javax.swing.JLabel();
        lblMenuCrear = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        lblImgInicio = new javax.swing.JLabel();
        lblImgEliminar = new javax.swing.JLabel();
        lblMenuEliminar = new javax.swing.JLabel();
        lblImgVer = new javax.swing.JLabel();
        lblMenuVer = new javax.swing.JLabel();
        lblImgModificar = new javax.swing.JLabel();
        lblMenuModificar = new javax.swing.JLabel();
        lblMenuAyuda = new javax.swing.JLabel();
        lblImgAyuda = new javax.swing.JLabel();
        lblImgConfi = new javax.swing.JLabel();
        lblMenuConfi = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        btnMaximizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        lblPrincipal = new javax.swing.JLabel();
        btnDesplegar1 = new javax.swing.JButton();
        panelComenzar = new javax.swing.JPanel();
        lblImgU = new javax.swing.JLabel();
        lblP1Nombre = new javax.swing.JLabel();
        lblP1Correo = new javax.swing.JLabel();
        lblP1Cuentas = new javax.swing.JLabel();
        lblP1NumeroC = new javax.swing.JLabel();
        lblP1P2 = new javax.swing.JLabel();
        lblP1P1 = new javax.swing.JLabel();
        lblP1P3 = new javax.swing.JLabel();
        lblP1P4 = new javax.swing.JLabel();
        lblP1P5 = new javax.swing.JLabel();
        lblP1P6 = new javax.swing.JLabel();
        lblP1P7 = new javax.swing.JLabel();
        lblQR = new javax.swing.JLabel();
        txtTtleInicio = new javax.swing.JLabel();
        panelCrear = new javax.swing.JPanel();
        txtNombreU = new javax.swing.JLabel();
        etNombreU = new javax.swing.JTextField();
        txtCorreoU = new javax.swing.JLabel();
        etCorreoU = new javax.swing.JTextField();
        txtContraseñaU = new javax.swing.JLabel();
        txtDescripU = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA = new javax.swing.JTextArea();
        txtBusquedaU = new javax.swing.JLabel();
        etBusquedaU = new javax.swing.JTextField();
        txtInicioU = new javax.swing.JLabel();
        btnCrearU = new ui.Button();
        btnContraseña = new ui.Button();
        button1 = new ui.Button();
        txtTtleCreacion = new javax.swing.JLabel();
        rbInstaU = new javax.swing.JRadioButton();
        rbMailU = new javax.swing.JRadioButton();
        rbYTU = new javax.swing.JRadioButton();
        etContraseña = new javax.swing.JPasswordField();
        btnVisualizarContra = new ui.Button();
        panelModificar = new javax.swing.JPanel();
        lbl5Usuario = new javax.swing.JLabel();
        txt5Usuario = new javax.swing.JTextField();
        lbl5Contraseña = new javax.swing.JLabel();
        lbl5Correo = new javax.swing.JLabel();
        txt5Correo = new javax.swing.JTextField();
        lbl5Auto = new javax.swing.JLabel();
        btnDesmarcar = new ui.Button();
        btnModificar = new ui.Button();
        txtTtleModificacion = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbModificar = new javax.swing.JTable();
        chkInsta = new javax.swing.JRadioButton();
        chkGmail = new javax.swing.JRadioButton();
        chkYoutube = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt5Area = new javax.swing.JTextArea();
        lbl5Descrip = new javax.swing.JLabel();
        btnMostrar = new ui.Button();
        txt5Contraseña = new javax.swing.JPasswordField();
        panelEliminar = new javax.swing.JPanel();
        btnEliminar = new ui.Button();
        txtTtleEliminacion = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbEliminar = new javax.swing.JTable();
        panelVisualizar = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtA3Descrip = new javax.swing.JTextArea();
        lbl3Descrip = new javax.swing.JLabel();
        lbl3usuario = new javax.swing.JLabel();
        txt3usuario = new javax.swing.JTextField();
        lbl3contraseña = new javax.swing.JLabel();
        lbl3Correo = new javax.swing.JLabel();
        txt3Correo = new javax.swing.JTextField();
        lbl3Visualizar = new javax.swing.JLabel();
        txt3buscar = new javax.swing.JTextField();
        btnDesplegar2 = new javax.swing.JButton();
        btnInsta = new javax.swing.JButton();
        btnGmail = new javax.swing.JButton();
        btnYt = new javax.swing.JButton();
        lbl3Descrip1 = new javax.swing.JLabel();
        txtTtleVisualizacion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUsuario = new javax.swing.JTable();
        btnVisualizar = new ui.Button();
        txt3contraseña = new javax.swing.JPasswordField();
        panelConfiguracion = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        lbl7gmail = new javax.swing.JLabel();
        lbl7Contra = new javax.swing.JLabel();
        lbl7Exportar = new javax.swing.JLabel();
        lbl7Importar = new javax.swing.JLabel();
        lbl7Color = new javax.swing.JLabel();
        lbl7Color1 = new javax.swing.JLabel();
        txtTtleConfig = new javax.swing.JLabel();
        btnCrearU1 = new ui.Button();
        btnCrearU2 = new ui.Button();
        btnCrearU3 = new ui.Button();
        btnAceptarU4 = new ui.Button();
        btnCrearU5 = new ui.Button();
        jFileChooser2 = new javax.swing.JFileChooser();
        panelAyuda = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtA6Descrip = new javax.swing.JTextArea();
        lbl6Descrip = new javax.swing.JLabel();
        lblIndicar = new javax.swing.JLabel();
        txt6Correo = new javax.swing.JTextField();
        txtTtleAyuda = new javax.swing.JLabel();
        lbl6Correo = new javax.swing.JLabel();
        btnEnviar = new ui.Button();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelPersonalizado1 = new proyectocontrasenhas.PanelPersonalizado();
        jLabel1 = new javax.swing.JLabel();
        btnIniciar = new ui.Button();
        btnCrear = new ui.Button();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPin = new javax.swing.JLabel();
        txtPin = new javax.swing.JPasswordField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnAtras = new ui.Button();
        btnCreado = new ui.Button();
        btnInicio = new ui.Button();
        btnEnvio = new ui.Button();
        buttonPrueba = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        buttonPrueba2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jFrame2.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame2.setMinimumSize(new java.awt.Dimension(930, 500));
        jFrame2.setUndecorated(true);

        panelPersonalizado2.setDoubleBuffered(false);
        panelPersonalizado2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDesplegar.setBackground(new java.awt.Color(51, 51, 51));
        btnDesplegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuadricula.png"))); // NOI18N
        btnDesplegar.setToolTipText("");
        btnDesplegar.setBorder(null);
        btnDesplegar.setBorderPainted(false);
        btnDesplegar.setContentAreaFilled(false);
        btnDesplegar.setDefaultCapable(false);
        btnDesplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplegarActionPerformed(evt);
            }
        });
        panelPersonalizado2.add(btnDesplegar, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 54, 40, 30));

        lblImgCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        panelPersonalizado2.add(lblImgCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, 40));

        lblMenuCrear.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuCrear.setText("Crear Cuenta");
        panelPersonalizado2.add(lblMenuCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        lblInicio.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblInicio.setText("Inicio");
        panelPersonalizado2.add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        lblImgInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hogar.png"))); // NOI18N
        panelPersonalizado2.add(lblImgInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        lblImgEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminarUser.png"))); // NOI18N
        panelPersonalizado2.add(lblImgEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, 40));

        lblMenuEliminar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuEliminar.setText("Eliminar Cuenta");
        panelPersonalizado2.add(lblMenuEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 30));

        lblImgVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vista.png"))); // NOI18N
        panelPersonalizado2.add(lblImgVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, 40));

        lblMenuVer.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuVer.setText("Ver Cuentas");
        panelPersonalizado2.add(lblMenuVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));

        lblImgModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        panelPersonalizado2.add(lblImgModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, 40));

        lblMenuModificar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuModificar.setText("Modifcar Cuenta");
        panelPersonalizado2.add(lblMenuModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 30));

        lblMenuAyuda.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuAyuda.setText("Ayuda");
        panelPersonalizado2.add(lblMenuAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, 30));

        lblImgAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ayuda.png"))); // NOI18N
        panelPersonalizado2.add(lblImgAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, -1, 40));

        lblImgConfi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/configuracion.png"))); // NOI18N
        panelPersonalizado2.add(lblImgConfi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, -1, 40));

        lblMenuConfi.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblMenuConfi.setText("Configuración");
        panelPersonalizado2.add(lblMenuConfi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 30));

        lblMenu.setBackground(new java.awt.Color(51, 51, 51));
        lblMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblMenu.setMaximumSize(null);
        lblMenu.setOpaque(true);
        panelPersonalizado2.add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        btnMaximizar.setBackground(new java.awt.Color(51, 51, 51));
        btnMaximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/maximizar.png"))); // NOI18N
        btnMaximizar.setBorder(null);
        btnMaximizar.setBorderPainted(false);
        btnMaximizar.setContentAreaFilled(false);
        btnMaximizar.setDefaultCapable(false);
        btnMaximizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarActionPerformed(evt);
            }
        });
        panelPersonalizado2.add(btnMaximizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, -1, -1));

        btnCerrar.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        btnCerrar.setToolTipText("");
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setDefaultCapable(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        panelPersonalizado2.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, -1));

        btnMinimizar.setBackground(new java.awt.Color(51, 51, 51));
        btnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar.png"))); // NOI18N
        btnMinimizar.setBorder(null);
        btnMinimizar.setBorderPainted(false);
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.setDefaultCapable(false);
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        panelPersonalizado2.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        lblPrincipal.setBackground(new java.awt.Color(51, 51, 51));
        lblPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPrincipal.setText("  LOCKBOX");
        lblPrincipal.setOpaque(true);
        panelPersonalizado2.add(lblPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnDesplegar1.setBackground(new java.awt.Color(51, 51, 51));
        btnDesplegar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuadricula.png"))); // NOI18N
        btnDesplegar1.setToolTipText("");
        btnDesplegar1.setBorder(null);
        btnDesplegar1.setBorderPainted(false);
        btnDesplegar1.setContentAreaFilled(false);
        btnDesplegar1.setDefaultCapable(false);
        btnDesplegar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplegar1ActionPerformed(evt);
            }
        });
        panelPersonalizado2.add(btnDesplegar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 54, 40, 30));

        panelComenzar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelComenzar.setInheritsPopupMenu(true);
        panelComenzar.setOpaque(false);

        lblImgU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/persona-sencilla (1).png"))); // NOI18N

        lblP1Nombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblP1Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1Nombre.setText("Nombre de usuario");

        lblP1Correo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblP1Correo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1Correo.setText("pereriracorbaldani@gmail.com");

        lblP1Cuentas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblP1Cuentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1Cuentas.setText("Numero de cuentas:");

        lblP1NumeroC.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblP1NumeroC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1NumeroC.setText("34");

        lblP1P2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P2.setText("en la gestión de cuentas y permitirá que tengas mayor");

        lblP1P1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P1.setText("Bienvenido a LOCKBOX una aplicación que te ayudará ");

        lblP1P3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P3.setText("seguridad y orden en tus cuentas.");

        lblP1P4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P4.setText("LOCKBOX tiene la opción de iniciar la cuenta ");

        lblP1P5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P5.setText("automaticamente en las plataformas más populares,");

        lblP1P6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P6.setText("les dejamos aqui un QR para poder instalar la aplicación");

        lblP1P7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblP1P7.setText("y poder utilizar la aplicación en cualquier lugar.");

        lblQR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtTtleInicio.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleInicio.setText("Inicio");
        txtTtleInicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelComenzarLayout = new javax.swing.GroupLayout(panelComenzar);
        panelComenzar.setLayout(panelComenzarLayout);
        panelComenzarLayout.setHorizontalGroup(
            panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComenzarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComenzarLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lblP1Nombre))
                    .addComponent(lblP1Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelComenzarLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblP1Cuentas)
                        .addGap(18, 18, 18)
                        .addComponent(lblP1NumeroC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComenzarLayout.createSequentialGroup()
                        .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblP1P3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP1P2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP1P4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP1P5, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP1P6, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP1P7, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComenzarLayout.createSequentialGroup()
                        .addComponent(lblQR, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
            .addGroup(panelComenzarLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(lblImgU)
                .addGap(134, 134, 134)
                .addComponent(txtTtleInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComenzarLayout.createSequentialGroup()
                    .addContainerGap(475, Short.MAX_VALUE)
                    .addComponent(lblP1P1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)))
        );
        panelComenzarLayout.setVerticalGroup(
            panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComenzarLayout.createSequentialGroup()
                .addGap(15, 97, Short.MAX_VALUE)
                .addComponent(lblP1P2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1P3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1P4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1P5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1P6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1P7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQR, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(panelComenzarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImgU, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTtleInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblP1Nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP1Correo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblP1Cuentas)
                    .addComponent(lblP1NumeroC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelComenzarLayout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(lblP1P1)
                    .addContainerGap(380, Short.MAX_VALUE)))
        );

        panelPersonalizado2.add(panelComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelCrear.setInheritsPopupMenu(true);
        panelCrear.setOpaque(false);

        txtNombreU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtNombreU.setText("Nombre de usuario:");

        etNombreU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etNombreUActionPerformed(evt);
            }
        });

        txtCorreoU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtCorreoU.setText("Correo:");

        txtContraseñaU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtContraseñaU.setText("Contraseña:");

        txtDescripU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtDescripU.setText("Descripción:");

        txtA.setColumns(20);
        txtA.setRows(5);
        jScrollPane1.setViewportView(txtA);

        txtBusquedaU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtBusquedaU.setText("Nombre de busqueda:");

        txtInicioU.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        txtInicioU.setText("Inicio automático:");

        btnCrearU.setBorder(null);
        btnCrearU.setText("Crear Cuenta");
        btnCrearU.setToolTipText("");
        btnCrearU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUActionPerformed(evt);
            }
        });

        btnContraseña.setBorder(null);
        btnContraseña.setText("Contraseña segura");
        btnContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContraseñaActionPerformed(evt);
            }
        });

        button1.setText("Desmarcar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        txtTtleCreacion.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleCreacion.setText("Creación");
        txtTtleCreacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        buttonGroup1.add(rbInstaU);
        rbInstaU.setText("Instagram");
        rbInstaU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInstaUActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbMailU);
        rbMailU.setText("Gmail");

        buttonGroup1.add(rbYTU);
        rbYTU.setText("YouTube");

        btnVisualizarContra.setBorder(null);
        btnVisualizarContra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        btnVisualizarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarContraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addComponent(txtDescripU)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(btnCrearU, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCrearLayout.createSequentialGroup()
                                        .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtTtleCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelCrearLayout.createSequentialGroup()
                                                .addComponent(txtNombreU)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(etNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtContraseñaU))
                                    .addGroup(panelCrearLayout.createSequentialGroup()
                                        .addComponent(txtCorreoU)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(etCorreoU, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelCrearLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(btnContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnVisualizarContra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(etContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelCrearLayout.createSequentialGroup()
                                .addComponent(txtBusquedaU)
                                .addGap(18, 18, 18)
                                .addComponent(etBusquedaU, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrearLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtInicioU)
                .addGap(28, 28, 28)
                .addComponent(rbInstaU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMailU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbYTU)
                .addGap(46, 46, 46)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        panelCrearLayout.setVerticalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTtleCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreU)
                    .addComponent(etNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseñaU)
                    .addComponent(etContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizarContra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCorreoU)
                        .addComponent(etCorreoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaU)
                    .addComponent(etBusquedaU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInicioU)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbInstaU)
                            .addComponent(rbMailU)
                            .addComponent(rbYTU))
                        .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(txtDescripU)))
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrearU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
        );

        panelPersonalizado2.add(panelCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelModificar.setInheritsPopupMenu(true);
        panelModificar.setOpaque(false);

        lbl5Usuario.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl5Usuario.setText("Nombre de usuario:");

        txt5Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5UsuarioActionPerformed(evt);
            }
        });

        lbl5Contraseña.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl5Contraseña.setText("Contraseña:");

        lbl5Correo.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl5Correo.setText("Correo:");

        lbl5Auto.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl5Auto.setText("Inicio automático:");

        btnDesmarcar.setText("Desmarcar");
        btnDesmarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesmarcarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        txtTtleModificacion.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleModificacion.setText("Modificación");
        txtTtleModificacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tbModificar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Correo", "Referencia", "Inicio Automatico"
            }
        ));
        jScrollPane6.setViewportView(tbModificar);

        buttonGroup2.add(chkInsta);
        chkInsta.setText("Instagram");
        chkInsta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInstaActionPerformed(evt);
            }
        });

        buttonGroup2.add(chkGmail);
        chkGmail.setText("Gmail");

        buttonGroup2.add(chkYoutube);
        chkYoutube.setText("Youtube");

        txt5Area.setColumns(20);
        txt5Area.setRows(5);
        jScrollPane7.setViewportView(txt5Area);

        lbl5Descrip.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl5Descrip.setText("Descripción:");

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(panelModificarLayout.createSequentialGroup()
                        .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                                    .addComponent(txtTtleModificacion)
                                    .addGap(106, 106, 106))
                                .addGroup(panelModificarLayout.createSequentialGroup()
                                    .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelModificarLayout.createSequentialGroup()
                                            .addComponent(lbl5Usuario)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt5Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lbl5Contraseña))
                                        .addGroup(panelModificarLayout.createSequentialGroup()
                                            .addComponent(lbl5Auto)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkInsta)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(chkGmail)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panelModificarLayout.createSequentialGroup()
                                                    .addComponent(chkYoutube)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnDesmarcar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                                .addComponent(lbl5Descrip)
                                .addGap(18, 18, 18)))
                        .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt5Contraseña)
                            .addComponent(jScrollPane7))
                        .addGap(17, 17, 17)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lbl5Correo)
                .addGap(18, 18, 18)
                .addComponent(txt5Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        panelModificarLayout.setVerticalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtTtleModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl5Usuario)
                    .addComponent(txt5Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl5Contraseña)
                    .addComponent(txt5Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl5Correo)
                        .addComponent(txt5Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl5Auto)
                            .addComponent(btnDesmarcar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkInsta)
                            .addComponent(chkGmail)
                            .addComponent(chkYoutube))
                        .addGap(18, 18, 18)
                        .addComponent(lbl5Descrip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        panelPersonalizado2.add(panelModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelEliminar.setInheritsPopupMenu(true);
        panelEliminar.setOpaque(false);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtTtleEliminacion.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleEliminacion.setText("Eliminación");
        txtTtleEliminacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tbEliminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Correo", "Referencia", "Inicio Automatico"
            }
        ));
        jScrollPane5.setViewportView(tbEliminar);

        javax.swing.GroupLayout panelEliminarLayout = new javax.swing.GroupLayout(panelEliminar);
        panelEliminar.setLayout(panelEliminarLayout);
        panelEliminarLayout.setHorizontalGroup(
            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarLayout.createSequentialGroup()
                .addGroup(panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarLayout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(txtTtleEliminacion)
                        .addGap(0, 403, Short.MAX_VALUE))
                    .addGroup(panelEliminarLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane5)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(345, 345, 345))
        );
        panelEliminarLayout.setVerticalGroup(
            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtTtleEliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        panelPersonalizado2.add(panelEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelVisualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelVisualizar.setInheritsPopupMenu(true);
        panelVisualizar.setOpaque(false);

        txtA3Descrip.setColumns(20);
        txtA3Descrip.setRows(5);
        jScrollPane3.setViewportView(txtA3Descrip);

        lbl3Descrip.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3Descrip.setText("Descripción:");

        lbl3usuario.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3usuario.setText("Nombre de usuario:");

        txt3usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3usuarioActionPerformed(evt);
            }
        });

        lbl3contraseña.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3contraseña.setText("Contraseña:");

        lbl3Correo.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3Correo.setText("Correo:");

        lbl3Visualizar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3Visualizar.setText("Buscar:");

        txt3buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3buscarActionPerformed(evt);
            }
        });

        btnDesplegar2.setBackground(new java.awt.Color(51, 51, 51));
        btnDesplegar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnDesplegar2.setToolTipText("");
        btnDesplegar2.setBorder(null);
        btnDesplegar2.setBorderPainted(false);
        btnDesplegar2.setContentAreaFilled(false);
        btnDesplegar2.setDefaultCapable(false);
        btnDesplegar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplegar2ActionPerformed(evt);
            }
        });

        btnInsta.setText("Instagram");
        btnInsta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstaActionPerformed(evt);
            }
        });

        btnGmail.setText("Gmail");
        btnGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGmailActionPerformed(evt);
            }
        });

        btnYt.setText("Youtube");
        btnYt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYtActionPerformed(evt);
            }
        });

        lbl3Descrip1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl3Descrip1.setText("Inicio:");

        txtTtleVisualizacion.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleVisualizacion.setText("Visualización");

        tbUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Correo", "Referencia", "Inicio Automatico"
            }
        ));
        jScrollPane2.setViewportView(tbUsuario);

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVisualizarLayout = new javax.swing.GroupLayout(panelVisualizar);
        panelVisualizar.setLayout(panelVisualizarLayout);
        panelVisualizarLayout.setHorizontalGroup(
            panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizarLayout.createSequentialGroup()
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl3Descrip))
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lbl3Descrip1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInsta)
                        .addGap(26, 26, 26)
                        .addComponent(btnGmail)
                        .addGap(38, 38, 38)
                        .addComponent(btnYt)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(panelVisualizarLayout.createSequentialGroup()
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2))
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(txtTtleVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelVisualizarLayout.createSequentialGroup()
                                .addComponent(lbl3Visualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt3buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDesplegar2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelVisualizarLayout.createSequentialGroup()
                                .addComponent(lbl3usuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt3usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl3contraseña)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt3contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
            .addGroup(panelVisualizarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbl3Correo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt3Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        panelVisualizarLayout.setVerticalGroup(
            panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisualizarLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtTtleVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl3Visualizar)
                        .addComponent(txt3buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDesplegar2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3usuario)
                    .addComponent(txt3usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3contraseña)
                    .addComponent(txt3contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl3Correo)
                        .addComponent(txt3Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelVisualizarLayout.createSequentialGroup()
                        .addComponent(lbl3Descrip)
                        .addGap(18, 18, 18)
                        .addGroup(panelVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl3Descrip1)
                            .addComponent(btnInsta)
                            .addComponent(btnGmail)
                            .addComponent(btnYt)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        panelPersonalizado2.add(panelVisualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelConfiguracion.setInheritsPopupMenu(true);
        panelConfiguracion.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/azulEleccion.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cyanEleccion.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/darkGrayEleccion.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GrayEleccion.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/greenEleccion.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lightgrayEleccion.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magentaEleccion.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/orangeEleccion.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pinkEleccion.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/redEleccion.png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/whiteEleccion.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/yellowEleccion.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        lbl7gmail.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7gmail.setText("Cambiar Correo:");

        lbl7Contra.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7Contra.setText("Cambiar Contraseña:");

        lbl7Exportar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7Exportar.setText("Exportar Cuentas:");

        lbl7Importar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7Importar.setText("Importar Cuentas:");

        lbl7Color.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7Color.setText("Cambiar Color");

        lbl7Color1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl7Color1.setText("Borrar Todas las cuentas:");

        txtTtleConfig.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleConfig.setText("Configuración");
        txtTtleConfig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnCrearU1.setBackground(new java.awt.Color(255, 0, 51));
        btnCrearU1.setBorder(null);
        btnCrearU1.setText("Borrar");
        btnCrearU1.setToolTipText("");
        btnCrearU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearU1ActionPerformed(evt);
            }
        });

        btnCrearU2.setBorder(null);
        btnCrearU2.setText("Exportar");
        btnCrearU2.setToolTipText("");
        btnCrearU2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearU2ActionPerformed(evt);
            }
        });

        btnCrearU3.setBorder(null);
        btnCrearU3.setText("Importar");
        btnCrearU3.setToolTipText("");
        btnCrearU3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearU3ActionPerformed(evt);
            }
        });

        btnAceptarU4.setBorder(null);
        btnAceptarU4.setText("Aceptar");
        btnAceptarU4.setToolTipText("");
        btnAceptarU4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarU4ActionPerformed(evt);
            }
        });

        btnCrearU5.setBackground(new java.awt.Color(255, 0, 51));
        btnCrearU5.setBorder(null);
        btnCrearU5.setText("Cerrar Sesión");
        btnCrearU5.setToolTipText("");
        btnCrearU5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearU5ActionPerformed(evt);
            }
        });

        jFileChooser2.setApproveButtonText(" Select");
        jFileChooser2.setApproveButtonToolTipText("");
        jFileChooser2.setCurrentDirectory(new java.io.File("C:\\Cuentas"));
        jFileChooser2.setDialogTitle("");
        jFileChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jFileChooser2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConfiguracionLayout = new javax.swing.GroupLayout(panelConfiguracion);
        panelConfiguracion.setLayout(panelConfiguracionLayout);
        panelConfiguracionLayout.setHorizontalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl7Color)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12))
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTtleConfig)
                                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5))))))
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                                        .addComponent(lbl7Contra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                                        .addComponent(lbl7gmail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl7Exportar)
                                    .addComponent(lbl7Importar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCrearU2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(btnCrearU3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                        .addComponent(btnCrearU5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(btnAceptarU4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                        .addComponent(lbl7Color1)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCrearU1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelConfiguracionLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelConfiguracionLayout.setVerticalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(txtTtleConfig)
                .addGap(35, 35, 35)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                        .addComponent(lbl7Color)
                        .addGap(4, 4, 4)))
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8)
                        .addComponent(jButton9)
                        .addComponent(jButton10)
                        .addComponent(jButton12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl7gmail)
                    .addComponent(lbl7Exportar)
                    .addComponent(btnCrearU2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl7Contra)
                    .addComponent(lbl7Importar)
                    .addComponent(btnCrearU3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lbl7Color1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearU1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearU5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarU4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelConfiguracionLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelPersonalizado2.add(panelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        panelAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelAyuda.setInheritsPopupMenu(true);
        panelAyuda.setOpaque(false);

        txtA6Descrip.setColumns(20);
        txtA6Descrip.setRows(5);
        jScrollPane4.setViewportView(txtA6Descrip);

        lbl6Descrip.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl6Descrip.setText("Escribanos tu consulta");

        lblIndicar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblIndicar.setText("Indica la dirección de correo a la que enviaremos la respuesta");

        txtTtleAyuda.setFont(new java.awt.Font("Source Code Pro", 1, 18)); // NOI18N
        txtTtleAyuda.setText("Ayuda");

        lbl6Correo.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl6Correo.setText("Correo:");

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAyudaLayout = new javax.swing.GroupLayout(panelAyuda);
        panelAyuda.setLayout(panelAyudaLayout);
        panelAyudaLayout.setHorizontalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAyudaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTtleAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(301, 301, 301))
            .addGroup(panelAyudaLayout.createSequentialGroup()
                .addGroup(panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAyudaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl6Descrip)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIndicar)))
                    .addGroup(panelAyudaLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAyudaLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lbl6Correo)
                        .addGap(18, 18, 18)
                        .addComponent(txt6Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        panelAyudaLayout.setVerticalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAyudaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txtTtleAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIndicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt6Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl6Correo))
                .addGap(50, 50, 50)
                .addComponent(lbl6Descrip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        panelPersonalizado2.add(panelAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 870, 470));

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPersonalizado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPersonalizado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LOCKBOX");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        panelPersonalizado1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LockBox");
        panelPersonalizado1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 132, -1, -1));

        btnIniciar.setBorder(null);
        btnIniciar.setText("Iniciar Sesión");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 325, 191, 42));

        btnCrear.setBorder(null);
        btnCrear.setText("Crear Cuenta");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 243, 191, 42));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setText("Nombre de Usuario:");
        panelPersonalizado1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 290, 130, 20));
        panelPersonalizado1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 282, 210, 30));

        lblPin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPin.setText("Contraseña:");
        panelPersonalizado1.add(lblPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 80, -1));
        panelPersonalizado1.add(txtPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 322, 120, 30));

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCorreo.setText("Correo Electronico: ");
        panelPersonalizado1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 130, -1));
        panelPersonalizado1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 280, 30));

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCodigo.setText("Codigo Correo:");
        panelPersonalizado1.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 120, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 130, 30));

        btnAtras.setText("Volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, 40));

        btnCreado.setText("Crear");
        btnCreado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreadoActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnCreado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, -1, 40));

        btnInicio.setText("Iniciar");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, -1, 40));

        btnEnvio.setText("Enviar Código");
        btnEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvioActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(btnEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 130, 40));

        buttonPrueba.setBackground(new java.awt.Color(51, 51, 51));
        buttonPrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar.png"))); // NOI18N
        buttonPrueba.setBorder(null);
        buttonPrueba.setBorderPainted(false);
        buttonPrueba.setContentAreaFilled(false);
        buttonPrueba.setDefaultCapable(false);
        buttonPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPruebaActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(buttonPrueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        button2.setBackground(new java.awt.Color(51, 51, 51));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        button2.setToolTipText("");
        button2.setBorder(null);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(false);
        button2.setDefaultCapable(false);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        buttonPrueba2.setBackground(new java.awt.Color(51, 51, 51));
        buttonPrueba2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/maximizar.png"))); // NOI18N
        buttonPrueba2.setBorder(null);
        buttonPrueba2.setBorderPainted(false);
        buttonPrueba2.setContentAreaFilled(false);
        buttonPrueba2.setDefaultCapable(false);
        buttonPrueba2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrueba2ActionPerformed(evt);
            }
        });
        panelPersonalizado1.add(buttonPrueba2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("  LOCKBOX");
        jLabel2.setOpaque(true);
        panelPersonalizado1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPersonalizado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPersonalizado1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        String usuario = txtUsuario.getText().toString();
        char[] password = txtPin.getPassword();
        String contraseña = new String(password);

        iniciarSesion(usuario, contraseña, codigoGmail);

    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnCreadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreadoActionPerformed
        // TODO add your handling code here:

        crearUsuario();
    }//GEN-LAST:event_btnCreadoActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        btnIniciar.setVisible(true);
        btnCrear.setVisible(true);

        lblUsuario.setVisible(false);

        lblPin.setVisible(false);

        lblCorreo.setVisible(false);

        lblCodigo.setVisible(false);

        txtUsuario.setVisible(false);
        txtUsuario.setText("");
        txtPin.setVisible(false);
        txtPin.setText("");
        txtCorreo.setVisible(false);
        txtCorreo.setText("");
        txtCodigo.setVisible(false);
        txtCodigo.setText("");

        btnAtras.setVisible(false);
        btnCreado.setVisible(false);
        btnInicio.setVisible(false);
        btnEnvio.setVisible(false);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        btnIniciar.setVisible(false);
        btnCrear.setVisible(false);

        lblUsuario.setVisible(true);
        lblPin.setVisible(true);
        lblCorreo.setVisible(true);

        txtUsuario.setVisible(true);
        txtPin.setVisible(true);
        txtCorreo.setVisible(true);

        btnAtras.setVisible(true);
        btnCreado.setVisible(true);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        if (gis.verificacionInicio(uuid) == true) {
            lblPrincipal.setPreferredSize(new Dimension(930, 50));
            lblMenu.setPreferredSize(new Dimension(50, 460));
            actionComenzar(gis.comprobarUsuarioAutomatico(uuid));
            this.setVisible(false);
            jFrame2.setVisible(true);

        } else {
            btnIniciar.setVisible(false);
            btnCrear.setVisible(false);

            lblUsuario.setVisible(true);
            lblPin.setVisible(true);
            lblCodigo.setVisible(true);

            txtUsuario.setVisible(true);
            txtPin.setVisible(true);
            txtCodigo.setVisible(true);

            btnAtras.setVisible(true);
            btnInicio.setVisible(true);
            btnEnvio.setVisible(true);

        }


    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvioActionPerformed
        // TODO add your handling code here:
        codigoGmail = generarCodigo();
        if (txtUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ponga el nombre de usuario y ten enviaremos \n el codigo "
                    + "por gmail", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            String user = txtUsuario.getText().toString();
            gis.envioGmail(user, codigoGmail);
        }
    }//GEN-LAST:event_btnEnvioActionPerformed

    private void buttonPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPruebaActionPerformed
        // TODO add your handling code here:
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_buttonPruebaActionPerformed

    private void buttonPrueba2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrueba2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPrueba2ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_button2ActionPerformed

    private void btnDesplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplegarActionPerformed
        // TODO add your handling code here:
        if (retrocederMenu == false) {
            lblMenu.setSize(lblMenu.getWidth() + 150, lblMenu.getHeight());
            btnDesplegar.setSize(btnDesplegar.getWidth() + 300, btnDesplegar.getHeight());
            gp.visibleComponente(true);

            gp.componentesVisibles();

            retrocederMenu = true;
        } else if (retrocederMenu == true) {
            lblMenu.setSize(lblMenu.getWidth() - 150, lblMenu.getHeight());
            btnDesplegar.setSize(btnDesplegar.getWidth() - 300, btnDesplegar.getHeight());
            gp.visibleComponente(false);
            gp.componentesInvisibles();

            retrocederMenu = false;
        }

    }//GEN-LAST:event_btnDesplegarActionPerformed


    private void btnDesplegar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplegar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesplegar1ActionPerformed

    private void btnMaximizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarActionPerformed

        if (!retrocederPantalla) {
            gp.maximizarBordeVentana();
            gp.centrarPanel();
            gp.maximizarComponentes();
            retrocederPantalla = true;
        } else if (retrocederPantalla) {
            gp.minimizarBordeVentana();
            gp.incialPanel();
            retrocederPantalla = false;
        }

    }//GEN-LAST:event_btnMaximizarActionPerformed

    private void actionComenzar(String user) {

        lblP1Nombre.setText(user);
        lblP1Correo.setText(gis.devolverGmail(user));
        lblP1NumeroC.setText("" + gis.numeroCuentasTotales(gis.obtenerIdUsuarioActual(gis.comprobarUsuarioAutomatico(uuid))));
    }
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        // TODO add your handling code here:
        jFrame2.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void txt3usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3usuarioActionPerformed

    private void txt3buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3buscarActionPerformed

    private void btnDesplegar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplegar2ActionPerformed
        String referencia = txt3buscar.getText().toString();
        if (!referencia.isEmpty()) {

            arrayCompletaCuentas = gbd.arrayCompletarArray(referencia);
            txt3usuario.setText(arrayCompletaCuentas.get(0).getUsuario().toString());
            txt3Correo.setText(arrayCompletaCuentas.get(0).getCorreo().toString());
            txt3contraseña.setText(arrayCompletaCuentas.get(0).getContraseña().toString());
            txtA3Descrip.setText(arrayCompletaCuentas.get(0).getDescripcion().toString());
            arrayCompletaCuentas.clear();

        }

    }//GEN-LAST:event_btnDesplegar2ActionPerformed

    private void autoCompletadoTexto() {

        arrayListParseJList();

        AutoCompleteDecorator.decorate(listaAutoCompletado, txt3buscar, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

    }

    private void visualizarBotonesAuto(String nombre) {

        if (nombre.equals("Gmail")) {
            btnGmail.setVisible(true);
            btnInsta.setVisible(false);
            btnYt.setVisible(false);
            chkInsta.setSelected(false);
            chkGmail.setSelected(true);
            chkYoutube.setSelected(false);

        } else if (nombre.equals("Insta")) {
            btnInsta.setVisible(true);
            btnGmail.setVisible(false);
            btnYt.setVisible(false);
            chkInsta.setSelected(true);
            chkGmail.setSelected(false);
            chkYoutube.setSelected(false);

        } else if (nombre.equals("Youtube")) {
            btnYt.setVisible(true);
            btnInsta.setVisible(false);
            btnGmail.setVisible(false);
            chkInsta.setSelected(false);
            chkGmail.setSelected(false);
            chkYoutube.setSelected(true);

        } else {
            btnYt.setVisible(false);
            btnInsta.setVisible(false);
            btnGmail.setVisible(false);
            chkInsta.setSelected(false);
            chkGmail.setSelected(false);
            chkYoutube.setSelected(false);

        }

    }

    private void arrayListParseJList() {

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (int i = 0; i < arrayCuentas.size(); i++) {

            listModel.add(i, arrayCuentas.get(i).getReferencia().toString());
        }

        listaAutoCompletado.setModel(listModel);

    }
    private void txt5UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5UsuarioActionPerformed

    private void btnDesmarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesmarcarActionPerformed
        buttonGroup2.clearSelection();       // TODO add your handling code here:

    }//GEN-LAST:event_btnDesmarcarActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_button1ActionPerformed

    private void btnContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContraseñaActionPerformed
        etContraseña.setText(creacionContraseñaSegura(10, 48, 112));

    }//GEN-LAST:event_btnContraseñaActionPerformed
    private String creacionContraseñaSegura(int len, int randNumOrigin, int randNumBound) {

        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

    }

    public void actualizarCamposTexto(String referencia, String usuario, String correo) {
        arrayCompletaCuentas = gbd.arrayCompletarArray(referencia);

        if (!arrayCompletaCuentas.isEmpty()) {
            txt3usuario.setText(usuario);
            txt3Correo.setText(correo);
            txt3buscar.setText(referencia);
            txt3contraseña.setText(arrayCompletaCuentas.get(0).getContraseña().toString());
            txtA3Descrip.setText(arrayCompletaCuentas.get(0).getDescripcion().toString());
        } else {
            System.out.println("No se encontraron cuentas asociadas a la referencia.");
            txt3contraseña.setText("");
            txtA3Descrip.setText("");
        }

        arrayCompletaCuentas.clear();

        visualizarBotonesAuto(gbd.devolverIncioAutomatico(referencia));
    }

    public void actualizarCamposModificar(String referencia, String usuario) {
        arrayCompletaCuentas = gbd.arrayCompletarArray(referencia);

        if (!arrayCompletaCuentas.isEmpty()) {
            txt5Usuario.setText(usuario);
            txt5Contraseña.setText(arrayCompletaCuentas.get(0).getContraseña().toString());
            txt5Correo.setText(arrayCompletaCuentas.get(0).getCorreo().toString());
            txt5Area.setText(arrayCompletaCuentas.get(0).getDescripcion().toString());
        } else {
            System.out.println("No se encontraron cuentas asociadas a la referencia.");
            limpiarCamposModificar();
        }

        arrayCompletaCuentas.clear();

        visualizarBotonesAuto(gbd.devolverIncioAutomatico(referencia));
    }

    private void limpiarCamposModificar() {
        txt5Usuario.setText("");
        txt5Contraseña.setText("");
        txt5Correo.setText("");
        txt5Area.setText("");
    }

    public boolean comprobarInicio(int a) {

        if (arrayCuentas.get(a).isGmail() || arrayCuentas.get(a).isInsta() || arrayCuentas.get(a).isYoutube()) {
            return true;
        } else {
            return false;
        }

    }
    private void btnCrearUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUActionPerformed

        if (rbInstaU.isSelected()) {
            instagram = true;
        } else if (rbMailU.isSelected()) {
            youtube = true;
        } else if (rbYTU.isSelected()) {
            gmail = true;
        }
        if (etCorreoU.getText().isEmpty() || etContraseña.getText().isEmpty() || etBusquedaU.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos obligatorios sin rellenar ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {

            gbd.insertarCuentaBD(etNombreU.getText().toString(), etCorreoU.getText().toString(), new String(etContraseña.getText().toString()), etBusquedaU.getText().toString(), txtA.getText().toString(), instagram, gmail, youtube, gis.obtenerIdUsuarioActual(gis.comprobarUsuarioAutomatico(uuid)));

            limpiarETCreacion();
            JOptionPane.showMessageDialog(this, "Cuenta Creada ", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btnCrearUActionPerformed
    private void limpiarETCreacion() {

        etNombreU.setText("");
        etCorreoU.setText("");
        etContraseña.setText("");
        etBusquedaU.setText("");
        txtA.setText("");

    }

    private void etNombreUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etNombreUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etNombreUActionPerformed

    private void rbInstaUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInstaUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbInstaUActionPerformed

    private void btnVisualizarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarContraActionPerformed
        if (visualizarContra == false) {
            etContraseña.setEchoChar((char) 0);
            visualizarContra = true;
        } else {
            etContraseña.setEchoChar('*');
            visualizarContra = false;
        }

    }//GEN-LAST:event_btnVisualizarContraActionPerformed

    private void eliminarTabla() {

        DefaultTableModel model = (DefaultTableModel) tbEliminar.getModel();
        model.setRowCount(0);
        arrayCuentas.clear();
        arrayCuentas = gbd.almacenarCuentasArray(uuid);

        if (arrayCuentas != null && !arrayCuentas.isEmpty()) {
            for (Cuentas cuenta : arrayCuentas) {
                Object dato1 = cuenta.getUsuario() != null ? cuenta.getUsuario() : "";
                Object dato2 = cuenta.getCorreo() != null ? cuenta.getCorreo() : "";
                Object dato3 = cuenta.getReferencia() != null ? cuenta.getReferencia() : "";
                model.addRow(new Object[]{dato1, dato2, dato3});
            }
        }

    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int selectedRow = tbEliminar.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar la siguiente cuenta: " + refeEliminar + " ?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            gbd.borradoBaseDatos(refeEliminar);
            tbEliminar.clearSelection();
            tbUsuario.clearSelection();

            DefaultTableModel model = (DefaultTableModel) tbEliminar.getModel();
            if (selectedRow >= 0 && selectedRow < model.getRowCount()) {
                model.removeRow(selectedRow);
            }

            eliminarTabla();

            JOptionPane.showMessageDialog(null, "Cuenta Borrada");

            gt.limpiartablaUsuario();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void chkInstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInstaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkInstaActionPerformed

    private int verificarInsta() {

        if (chkInsta.isSelected()) {

            return 1;
        }
        return 0;

    }

    private int verificarYouTube() {

        if (chkYoutube.isSelected()) {

            return 1;
        }
        return 0;

    }

    private int verificarGmail() {

        if (chkGmail.isSelected()) {

            return 1;
        }
        return 0;

    }
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (txt5Usuario.getText().isEmpty() || txt5Contraseña.getText().isEmpty() || txt5Correo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos ", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {

            gbd.actualizarBaseDatos(txt5Usuario.getText(), txt5Contraseña.getText().toString(), txt5Correo.getText().toString(), txt5Area.getText().toString(), refeModificar, verificarInsta(), verificarYouTube(), verificarGmail());
            arrayCuentas.clear();
            arrayCuentas = gbd.almacenarCuentasArray(uuid);
            gt.añadirTablaModificar();
            JOptionPane.showMessageDialog(this, "Modificación Completada ", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

        if (visualizarContra == false) {
            txt5Contraseña.setEchoChar((char) 0);
            visualizarContra = true;
        } else {
            txt5Contraseña.setEchoChar('*');
            visualizarContra = false;
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        // TODO add your handling code here:
        if (visualizarContra == false) {
            txt3contraseña.setEchoChar((char) 0);
            visualizarContra = true;
        } else {
            txt3contraseña.setEchoChar('*');
            visualizarContra = false;
        }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGmailActionPerformed
        if (txt3Correo.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Correo Vacio ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            ia.incioGmail(txt3Correo.getText().toString(), txt3contraseña.getText().toString());
        }
    }//GEN-LAST:event_btnGmailActionPerformed

    private void btnInstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstaActionPerformed
        if (txt3usuario.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Usuario Vacio ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            ia.incioInstagram(txt3usuario.getText().toString(), txt3contraseña.getText().toString());
        }
    }//GEN-LAST:event_btnInstaActionPerformed

    private void btnYtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYtActionPerformed
        // TODO add your handling code here
        if (txt3Correo.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Correo Vacio ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            ia.iniciarSesionYoutube(txt3Correo.getText().toString(), txt3contraseña.getText().toString());
        }
    }//GEN-LAST:event_btnYtActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if (txt6Correo.getText().equals("") || txtA6Descrip.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Campos Vaciose ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            boolean probar = true;
            try {
                ia.mensajeGmail(txt6Correo.getText().toString(), txtA6Descrip.getText().toString());
            } catch (Exception e) {
                probar = false;
            }
            if (probar) {
                JOptionPane.showMessageDialog(this, "Mensaje enviado", "Informaicón", JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }//GEN-LAST:event_btnEnviarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gis.cambiarColor("blue", uuid);
        ventanaCambiarColor("Blue");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        gis.cambiarColor("cyan", uuid);
        ventanaCambiarColor("Cyan");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        gis.cambiarColor("gray", uuid);
        ventanaCambiarColor("Gray");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnCrearU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearU1ActionPerformed

        int n = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar todas tus cuentas?", "Eliminar", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            gbd.borradoCompletoCuentas(uuid);
            lblP1NumeroC.setText("" + gis.numeroCuentasTotales(gis.obtenerIdUsuarioActual(gis.comprobarUsuarioAutomatico(uuid))));

        }


    }//GEN-LAST:event_btnCrearU1ActionPerformed

    private void ventanaCambiarColor(String color) {

        JOptionPane.showMessageDialog(this, "Color cambiado a " + color, "Información", JOptionPane.INFORMATION_MESSAGE);

    }
    private void btnCrearU2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearU2ActionPerformed

        try {
            f.escribirArchivo(gbd.almacenarCuentasFichero(uuid));
            JOptionPane.showMessageDialog(this, "Exportación realizada en C:/Cuentas/output.txt", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception w) {

            System.out.println("" + w.getMessage());
        }


    }//GEN-LAST:event_btnCrearU2ActionPerformed

    private void btnCrearU3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearU3ActionPerformed
        try {
            jFileChooser2.setVisible(true);
            gp.ocultarElementosConfigurar();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_btnCrearU3ActionPerformed

    private void btnAceptarU4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarU4ActionPerformed
        int a = 0;
        if (!(jTextField1.getText().isEmpty())) {

            gbd.cambiarCorreoUsuario(jTextField1.getText().toString(), uuid);
            a = 1;

        }
        if (!(jTextField2.getText().isEmpty())) {

            gis.cambiarContraseñaUsuario(jTextField2.getText().toString());
            a = a + 2;

        }
        if (a == 1) {
            JOptionPane.showMessageDialog(this, "Se ha cambiado el correo", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (a == 2) {

            JOptionPane.showMessageDialog(this, "Se ha camabiado la contraseña", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (a == 3) {

            JOptionPane.showMessageDialog(this, "Se han cambiado ambos campos", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btnAceptarU4ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        gis.cambiarColor("black", uuid);
        ventanaCambiarColor("Black");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        gis.cambiarColor("graydark", uuid);
        ventanaCambiarColor("GrayDark");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        gis.cambiarColor("green", uuid);
        ventanaCambiarColor("Green");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        gis.cambiarColor("magenta", uuid);
        ventanaCambiarColor("Magenta");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        gis.cambiarColor("orange", uuid);
        ventanaCambiarColor("Orange");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        gis.cambiarColor("pink", uuid);
        ventanaCambiarColor("Pink");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        gis.cambiarColor("red", uuid);
        ventanaCambiarColor("Red");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        gis.cambiarColor("graylight", uuid);
        ventanaCambiarColor("GrayLight");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        gis.cambiarColor("yellow", uuid);
        ventanaCambiarColor("Yellow");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnCrearU5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearU5ActionPerformed
        // TODO add your handling code here:
        try {
            gis.cerrarSesionAutomatica(uuid);
        } catch (Exception e) {
            System.out.println("Error cerrar" + e.getMessage());
        }

        System.exit(0);
    }//GEN-LAST:event_btnCrearU5ActionPerformed

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed

        try {
            fileChooserActionPerformed(evt);
        } catch (Exception ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jFileChooser2ActionPerformed

    private void fileChooserActionPerformed(ActionEvent e) throws Exception {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            System.out.println("" + jFileChooser2.getSelectedFile());
            añadirCuentasLeidas();

        } else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION)) {
            jFileChooser2.setVisible(false);
            gp.visibleElementosConfiguracion();
        }
    }

    private void añadirCuentasLeidas() throws Exception {

        arrayCuentasLeidas = f.leerArchivo(jFileChooser2.getSelectedFile().toString());
        for (int a = 0; a < arrayCuentasLeidas.size(); a++) {
            System.out.println("" + arrayCuentasLeidas.get(a).getUsuario());
            byte[] decode = Base64.getDecoder().decode(arrayCuentasLeidas.get(a).getContraseña());

            String contraseña = gbd.descifra(decode);
            try {
                gbd.insertarCuentaBD(arrayCuentasLeidas.get(a).getUsuario(), arrayCuentasLeidas.get(a).getCorreo(), contraseña, arrayCuentasLeidas.get(a).getReferencia(), arrayCuentasLeidas.get(a).getDescripcion(), arrayCuentasLeidas.get(a).isInsta(), arrayCuentasLeidas.get(a).isYoutube(), arrayCuentasLeidas.get(a).isGmail(), gis.comprobarIdAutomatico(uuid));
            } catch (Exception e) {

            }
        }

    }

    private void hideActiveFrame() {
        // Obtener el frame activo
        Window activeWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

        if (activeWindow instanceof JFrame) {
            JFrame activeFrame = (JFrame) activeWindow;
            activeFrame.setVisible(false);
        }
    }

    private void crearUsuario() {
        if (txtUsuario.getText().equals("") || txtPin.getPassword().equals("") || txtCorreo.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos ", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            String usuario = txtUsuario.getText();
            char[] password = txtPin.getPassword();
            String passwordText = new String(password);
            String gmail = txtCorreo.getText();

            gis.guardarContraseña(usuario, passwordText, gmail);

            JOptionPane.showMessageDialog(this, "Usuario Creado ", "Información", JOptionPane.INFORMATION_MESSAGE);
            // TODO add your handling code here:
            btnIniciar.setVisible(true);
            btnCrear.setVisible(true);
            lblUsuario.setVisible(false);
            lblPin.setVisible(false);
            lblCorreo.setVisible(false);
            lblCodigo.setVisible(false);
            txtUsuario.setVisible(false);
            txtUsuario.setText("");
            txtPin.setVisible(false);
            txtPin.setText("");
            txtCorreo.setVisible(false);
            txtCorreo.setText("");
            txtCodigo.setVisible(false);
            txtCodigo.setText("");
            btnAtras.setVisible(false);
            btnCreado.setVisible(false);
            btnInicio.setVisible(false);
        }

    }

    private void iniciarSesion(String usuario, String contraseña, int codigo) {

        if (gis.verificarContraseña(usuario, contraseña)) {

            String codigoenvio = String.valueOf(codigo);
            if (txtCodigo.getText().equals(codigoenvio)) {

                lblPrincipal.setPreferredSize(new Dimension(930, 50));
                lblMenu.setPreferredSize(new Dimension(50, 460));
                actionComenzar(usuario);
                gis.inicioAutomatico(usuario);
                this.setVisible(false);
                jFrame2.setVisible(true);
                String resultado = gis.comprobarUsuarioAutomatico(uuid);
                String uuidD = gis.comprobarUuidAutomatico(usuario);
                if (resultado == null || resultado.isEmpty() || uuidD != uuid) {
                    gis.actualizarUuidUsuario(uuid, usuario);
                } else {
                    System.out.println("uuid añadido");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Código Incorrecto ", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Usuario Incorrecto ", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private int generarCodigo() {

        int min = 100000;
        int max = 999999;
        Random random = new Random();

        return random.nextInt(min, max);

    }

    private static void generarQR(String text, int width, int height, String outputPath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        qrImage.createGraphics();

        Graphics2D graphics = qrImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(qrImage, "png", new File(outputPath));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PanelPrincipal().setVisible(true);
                } catch (WriterException ex) {
                    Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected ui.Button btnAceptarU4;
    protected ui.Button btnAtras;
    protected javax.swing.JButton btnCerrar;
    protected ui.Button btnContraseña;
    protected ui.Button btnCreado;
    protected ui.Button btnCrear;
    protected ui.Button btnCrearU;
    protected ui.Button btnCrearU1;
    protected ui.Button btnCrearU2;
    protected ui.Button btnCrearU3;
    protected ui.Button btnCrearU5;
    protected ui.Button btnDesmarcar;
    protected javax.swing.JButton btnDesplegar;
    protected javax.swing.JButton btnDesplegar1;
    protected javax.swing.JButton btnDesplegar2;
    protected ui.Button btnEliminar;
    protected ui.Button btnEnviar;
    protected ui.Button btnEnvio;
    protected javax.swing.JButton btnGmail;
    protected ui.Button btnIniciar;
    protected ui.Button btnInicio;
    protected javax.swing.JButton btnInsta;
    protected javax.swing.JButton btnMaximizar;
    protected javax.swing.JButton btnMinimizar;
    protected ui.Button btnModificar;
    protected ui.Button btnMostrar;
    protected ui.Button btnVisualizar;
    protected ui.Button btnVisualizarContra;
    protected javax.swing.JButton btnYt;
    protected ui.Button button1;
    protected javax.swing.JButton button2;
    protected javax.swing.ButtonGroup buttonGroup1;
    protected javax.swing.ButtonGroup buttonGroup2;
    protected javax.swing.JButton buttonPrueba;
    protected javax.swing.JButton buttonPrueba2;
    protected javax.swing.JRadioButton chkGmail;
    protected javax.swing.JRadioButton chkInsta;
    protected javax.swing.JRadioButton chkYoutube;
    protected javax.swing.JTextField etBusquedaU;
    protected javax.swing.JPasswordField etContraseña;
    protected javax.swing.JTextField etCorreoU;
    protected javax.swing.JTextField etNombreU;
    protected javax.swing.JButton jButton1;
    protected javax.swing.JButton jButton10;
    protected javax.swing.JButton jButton11;
    protected javax.swing.JButton jButton12;
    protected javax.swing.JButton jButton2;
    protected javax.swing.JButton jButton3;
    protected javax.swing.JButton jButton4;
    protected javax.swing.JButton jButton5;
    protected javax.swing.JButton jButton6;
    protected javax.swing.JButton jButton7;
    protected javax.swing.JButton jButton8;
    protected javax.swing.JButton jButton9;
    protected javax.swing.JFileChooser jFileChooser2;
    protected javax.swing.JFrame jFrame2;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JScrollPane jScrollPane3;
    protected javax.swing.JScrollPane jScrollPane4;
    protected javax.swing.JScrollPane jScrollPane5;
    protected javax.swing.JScrollPane jScrollPane6;
    protected javax.swing.JScrollPane jScrollPane7;
    protected javax.swing.JTextField jTextField1;
    protected javax.swing.JTextField jTextField2;
    protected javax.swing.JLabel lbl3Correo;
    protected javax.swing.JLabel lbl3Descrip;
    protected javax.swing.JLabel lbl3Descrip1;
    protected javax.swing.JLabel lbl3Visualizar;
    protected javax.swing.JLabel lbl3contraseña;
    protected javax.swing.JLabel lbl3usuario;
    protected javax.swing.JLabel lbl5Auto;
    protected javax.swing.JLabel lbl5Contraseña;
    protected javax.swing.JLabel lbl5Correo;
    protected javax.swing.JLabel lbl5Descrip;
    protected javax.swing.JLabel lbl5Usuario;
    protected javax.swing.JLabel lbl6Correo;
    protected javax.swing.JLabel lbl6Descrip;
    protected javax.swing.JLabel lbl7Color;
    protected javax.swing.JLabel lbl7Color1;
    protected javax.swing.JLabel lbl7Contra;
    protected javax.swing.JLabel lbl7Exportar;
    protected javax.swing.JLabel lbl7Importar;
    protected javax.swing.JLabel lbl7gmail;
    protected javax.swing.JLabel lblCodigo;
    protected javax.swing.JLabel lblCorreo;
    protected javax.swing.JLabel lblImgAyuda;
    protected javax.swing.JLabel lblImgConfi;
    protected javax.swing.JLabel lblImgCrear;
    protected javax.swing.JLabel lblImgEliminar;
    protected javax.swing.JLabel lblImgInicio;
    protected javax.swing.JLabel lblImgModificar;
    protected javax.swing.JLabel lblImgU;
    protected javax.swing.JLabel lblImgVer;
    protected javax.swing.JLabel lblIndicar;
    protected javax.swing.JLabel lblInicio;
    protected javax.swing.JLabel lblMenu;
    protected javax.swing.JLabel lblMenuAyuda;
    protected javax.swing.JLabel lblMenuConfi;
    protected javax.swing.JLabel lblMenuCrear;
    protected javax.swing.JLabel lblMenuEliminar;
    protected javax.swing.JLabel lblMenuModificar;
    protected javax.swing.JLabel lblMenuVer;
    protected javax.swing.JLabel lblP1Correo;
    protected javax.swing.JLabel lblP1Cuentas;
    protected javax.swing.JLabel lblP1Nombre;
    protected javax.swing.JLabel lblP1NumeroC;
    protected javax.swing.JLabel lblP1P1;
    protected javax.swing.JLabel lblP1P2;
    protected javax.swing.JLabel lblP1P3;
    protected javax.swing.JLabel lblP1P4;
    protected javax.swing.JLabel lblP1P5;
    protected javax.swing.JLabel lblP1P6;
    protected javax.swing.JLabel lblP1P7;
    protected javax.swing.JLabel lblPin;
    protected javax.swing.JLabel lblPrincipal;
    protected javax.swing.JLabel lblQR;
    protected javax.swing.JLabel lblUsuario;
    protected javax.swing.JPanel panelAyuda;
    protected javax.swing.JPanel panelComenzar;
    protected javax.swing.JPanel panelConfiguracion;
    protected javax.swing.JPanel panelCrear;
    protected javax.swing.JPanel panelEliminar;
    protected javax.swing.JPanel panelModificar;
    protected proyectocontrasenhas.PanelPersonalizado panelPersonalizado1;
    protected proyectocontrasenhas.PanelPersonalizado panelPersonalizado2;
    protected javax.swing.JPanel panelVisualizar;
    protected javax.swing.JRadioButton rbInstaU;
    protected javax.swing.JRadioButton rbMailU;
    protected javax.swing.JRadioButton rbYTU;
    protected javax.swing.JTable tbEliminar;
    protected javax.swing.JTable tbModificar;
    protected javax.swing.JTable tbUsuario;
    protected javax.swing.JTextField txt3Correo;
    protected javax.swing.JTextField txt3buscar;
    protected javax.swing.JPasswordField txt3contraseña;
    protected javax.swing.JTextField txt3usuario;
    protected javax.swing.JTextArea txt5Area;
    protected javax.swing.JPasswordField txt5Contraseña;
    protected javax.swing.JTextField txt5Correo;
    protected javax.swing.JTextField txt5Usuario;
    protected javax.swing.JTextField txt6Correo;
    protected javax.swing.JTextArea txtA;
    protected javax.swing.JTextArea txtA3Descrip;
    protected javax.swing.JTextArea txtA6Descrip;
    protected javax.swing.JLabel txtBusquedaU;
    protected javax.swing.JTextField txtCodigo;
    protected javax.swing.JLabel txtContraseñaU;
    protected javax.swing.JTextField txtCorreo;
    protected javax.swing.JLabel txtCorreoU;
    protected javax.swing.JLabel txtDescripU;
    protected javax.swing.JLabel txtInicioU;
    protected javax.swing.JLabel txtNombreU;
    protected javax.swing.JPasswordField txtPin;
    protected javax.swing.JLabel txtTtleAyuda;
    protected javax.swing.JLabel txtTtleConfig;
    protected javax.swing.JLabel txtTtleCreacion;
    protected javax.swing.JLabel txtTtleEliminacion;
    protected javax.swing.JLabel txtTtleInicio;
    protected javax.swing.JLabel txtTtleModificacion;
    protected javax.swing.JLabel txtTtleVisualizacion;
    protected javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
