/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import com.sun.tools.javac.Main;
import java.io.InputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author micro
 */
public class GestionBaseDatos {

    private String url = "jdbc:mysql://urs6ctw95htbjmv9:oJO1z3GT0OeJYxpismUB@bzpbqy90m9bwqhn4y4hc-mysql.services.clever-cloud.com:3306/bzpbqy90m9bwqhn4y4hc";
    private String usuarioDB = "root";
    private String contraseñaDB = "1234abc.";
    ArrayList<String> refe = new ArrayList<>();
    private ArrayList<Cuentas> arrayCuentas = new ArrayList<>();
    private String[] autoCompleteRefe;

    public void insertarCuentaBD(String usuario, String correo, String contraseña, String referencia, String descrip, boolean insta, boolean youtube, boolean gmail, int idUsuario) {
        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "INSERT INTO cuentas (usuario, correo, contraseña, referencia, descrip, insta, youtube, gmail,idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, correo);
                 String s = Base64.getEncoder().encodeToString(cifra(contraseña));
                preparedStatement.setString(3, s);
                preparedStatement.setString(4, referencia);
                preparedStatement.setString(5, descrip);
                preparedStatement.setBoolean(6, insta);
                preparedStatement.setBoolean(7, youtube);
                preparedStatement.setBoolean(8, gmail);
                preparedStatement.setInt(9, idUsuario);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
           // e.printStackTrace();
              JOptionPane.showMessageDialog(null, "Un usuario importado ya existe", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void crearUsuarioBD(String usuario1, String contraseña1, String gmail) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "INSERT INTO usuarios (alias, contraseña, gmail) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.setString(1, usuario1);
                preparedStatement.setString(2, contraseña1);
                preparedStatement.setString(3, gmail);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void devolverReferencia(ArrayList<String> ref) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            ref.clear();
            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT * FROM cuentas";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {
                int id = resultados.getInt("id");
                String referencia = resultados.getString("referencia");
                ref.add(referencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String devolverContraseña(String referencia) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {

            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT contraseña FROM cuentas where referencia = '" + referencia + "'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {
                String cuenta = resultados.getString("contraseña");
                  byte[] decode = Base64.getDecoder().decode(cuenta);
                String descodificado=descifra(decode);

                return descodificado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public void borradoBaseDatos(String referencia) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "DELETE FROM cuentas WHERE referencia = ?";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.setString(1, referencia);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void actualizarBaseDatos(String nombre, String contra, String correo, String descrip, String referencia1, int insta, int you, int gmail) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "UPDATE cuentas SET usuario = ?,contraseña = ?,descrip =?, insta =?,youtube =?,gmail =?,correo =? where referencia = ?";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.setString(1, nombre);
                 String s = Base64.getEncoder().encodeToString(cifra(contra));
                preparedStatement.setString(2, s);
                preparedStatement.setString(3, descrip);
                preparedStatement.setInt(4, insta);
                preparedStatement.setInt(5, you);
                preparedStatement.setInt(6, gmail);
                preparedStatement.setString(7, correo);
                preparedStatement.setString(8, referencia1);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public String devolverIncioAutomatico(String refe) {
        String resultado = "Ninguna";

        try (Connection conexion = DriverManager.getConnection(url, usuarioDB, contraseñaDB); PreparedStatement consulta = conexion.prepareStatement("SELECT insta, youtube, gmail FROM cuentas WHERE referencia = ?")) {

            consulta.setString(1, refe);
            ResultSet resultados = consulta.executeQuery();

            if (resultados.next()) {
                Map<String, Boolean> cuentas = new HashMap<>();
                cuentas.put("Insta", resultados.getBoolean("insta"));
                cuentas.put("Youtube", resultados.getBoolean("youtube"));
                cuentas.put("Gmail", resultados.getBoolean("gmail"));

                for (Map.Entry<String, Boolean> entry : cuentas.entrySet()) {
                    if (entry.getValue()) {
                        resultado = entry.getKey();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public ArrayList<Cuentas> almacenarCuentasArray(String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {

            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT * FROM cuentas where idUsuario =(SELECT idUsuario FROM usuarios WHERE inicio = 1 and uuid ="+"'"+uuid+"'"+")";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {

                int id = resultados.getInt("idUsuario");
                String usuario = resultados.getString("usuario");
                String correo = resultados.getString("correo");
                String contra = resultados.getString("contraseña");
                byte[] decode = Base64.getDecoder().decode(contra);
                String descodificado=descifra(decode);
                String refe = resultados.getString("referencia");
                String descrip = resultados.getString("descrip");
                boolean insta = resultados.getBoolean("insta");
                boolean gmail = resultados.getBoolean("gmail");
                boolean youtube = resultados.getBoolean("youtube");
                Cuentas cuentas = new Cuentas(id, usuario, correo, descodificado, refe, descrip, insta, gmail, youtube);
                arrayCuentas.add(cuentas);

            }
            return arrayCuentas;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayCuentas;
    }

    public String almacenarCuentasFichero(String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            StringBuilder contenido = new StringBuilder(); // Inicializamos un StringBuilder
            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT * FROM cuentas where idUsuario =(SELECT idUsuario FROM usuarios WHERE inicio = 1 and uuid="+"'"+uuid+"'"+")";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {

                String usuario = resultados.getString("usuario");
                String correo = resultados.getString("correo");
                String contra = resultados.getString("contraseña");
                String refe = resultados.getString("referencia");
                String descrip = resultados.getString("descrip");
                boolean insta = resultados.getBoolean("insta");
                boolean gmail = resultados.getBoolean("gmail");
                boolean youtube = resultados.getBoolean("youtube");
            

                contenido.append(usuario).append(",").append(correo).append(",")
                        .append(contra).append(",").append(refe).append(",").append(descrip).append(",")
                        .append(insta).append(",").append(gmail).append(",").append(youtube).append(";\n");

            }
            return contenido.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No tienes cuentas";
    }

    public ArrayList<Cuentas> arrayCompletarArray(String referencia) {
        ArrayList<Cuentas> arrayCuentas = new ArrayList<>();

        String consultaSQL = "SELECT * FROM cuentas WHERE referencia = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuarioDB, contraseñaDB); PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {

            consulta.setString(1, referencia);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                int id = resultados.getInt("idUsuario");
                String usuario = resultados.getString("usuario");
                String correo = resultados.getString("correo");
                String contra = resultados.getString("contraseña");
                 byte[] decode = Base64.getDecoder().decode(contra);
                String descodificado=descifra(decode);
                String refe = resultados.getString("referencia");
                String descrip = resultados.getString("descrip");
                boolean insta = resultados.getBoolean("insta");
                boolean gmail = resultados.getBoolean("gmail");
                boolean youtube = resultados.getBoolean("youtube");

                Cuentas cuentas = new Cuentas(id, usuario, correo, descodificado, refe, descrip, insta, gmail, youtube);
                arrayCuentas.add(cuentas);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayCuentas;
    }

    public void cambiarCorreoUsuario(String correo,String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "UPDATE usuarios SET gmail = ? where inicio = 1 and uuid ="+"'"+uuid+"'";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.setString(1, correo);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void borradoCompletoCuentas(String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String consultaSQL = "DELETE FROM cuentas WHERE idUsuario = (Select idUsuario from usuarios where inicio=1 and uuid ="+"'"+uuid+"'"+")";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public byte[] cifra(String sinCifrar) throws Exception {
        try{
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
        }catch(Exception w){
        }
        return null;
        
    }

    public String descifra(byte[] cifrado) throws Exception {
        
        final Cipher aes = obtieneCipher(false);
        try {
            final byte[] bytes = aes.doFinal(cifrado);
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw e; 
        }
    }

    private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }
}
