/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

/**
 *
 * @author micro
 */
public class Cuentas {
    
    private GestionBaseDatos gb;
    private int id;
    
    private String usuario;
    
    private String correo;
    
    private String contraseña;
    
    private String referencia;
    
    private String descripcion;
    
    private boolean insta;
    
    private boolean gmail;
    
    private boolean youtube;
    private int idUsuario;

    public Cuentas(int id, String usuario, String correo, String contraseña, String referencia, String descripcion, boolean insta, boolean gmail, boolean youtube) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.insta = insta;
        this.gmail = gmail;
        this.youtube = youtube;
    }

    public Cuentas(int id, String usuario, String correo, String referencia) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.referencia = referencia;
    }

    public Cuentas( String usuario, String correo, String contraseña, String referencia, String descripcion, boolean insta, boolean gmail, boolean youtube) {
       
       
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.insta = insta;
        this.gmail = gmail;
        this.youtube = youtube;
        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isInsta() {
        return insta;
    }

    public void setInsta(boolean insta) {
        this.insta = insta;
    }

    public boolean isGmail() {
        return gmail;
    }

    public void setGmail(boolean gmail) {
        this.gmail = gmail;
    }

    public boolean isYoutube() {
        return youtube;
    }

    public void setYoutube(boolean youtube) {
        this.youtube = youtube;
    }
    
    
    
    
    
    
    
    
    
}
