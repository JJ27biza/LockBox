/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author micro
 */
public class GestionInicioSesion {

    private String url = "jdbc:mysql://urs6ctw95htbjmv9:oJO1z3GT0OeJYxpismUB@bzpbqy90m9bwqhn4y4hc-mysql.services.clever-cloud.com:3306/bzpbqy90m9bwqhn4y4hc";
    private String usuario = "root";
    private String contraseña = "1234abc.";
    private String username = "lockboxes24@gmail.com";
    private String password = "n d e p x u r g n v g m f m e u";

    private String encriptarContraseña(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hashedPassword = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para generar una sal aleatoria
    private byte[] generarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public void cambiarContraseñaUsuario(String contraseña) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            byte[] salt = generarSalt();
            String encryptedPassword = encriptarContraseña(contraseña, salt);
            String consultaSQL = "UPDATE usuarios SET contraseña = ?,salt=? where inicio = 1 ";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {

                preparedStatement.setString(1, encryptedPassword);
                preparedStatement.setBytes(2, salt);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void guardarContraseña(String username, String password, String gmail) {
        try {
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);

            byte[] salt = generarSalt();

            String encryptedPassword = encriptarContraseña(password, salt);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (alias, contraseña, salt, gmail) VALUES (?, ?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, encryptedPassword);
            statement.setBytes(3, salt);
            statement.setString(4, gmail);
            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarContraseña(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement statement = connection.prepareStatement("SELECT contraseña, salt FROM usuarios WHERE alias = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String encryptedPassword = resultSet.getString("contraseña");
                byte[] salt = resultSet.getBytes("salt");

                String encryptedProvidedPassword = encriptarContraseña(password, salt);
                

                return encryptedProvidedPassword.equals(encryptedPassword);

            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void envioGmail(String user, int codigo) {

        String gmail = "";
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {

            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT gmail FROM usuarios where alias = '" + user + "'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {
                gmail = resultados.getString("gmail");

            }
            mensajeGmail(gmail, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String devolverGmail(String user) {
        String gmail = "";
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {

            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT gmail FROM usuarios where alias = '" + user + "'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {
                gmail = resultados.getString("gmail");

            }
            return gmail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public int numeroCuentasTotales(int idUsuario) {

        int numero = 0;
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {

            Statement declaración = conexión.createStatement();

            String consultaSQL = "SELECT * FROM cuentas where idUsuario = " + idUsuario;
            ResultSet resultados = declaración.executeQuery(consultaSQL);

            while (resultados.next()) {
                numero++;

            }
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    private void mensajeGmail(String gmail, int codigo) {

        String to = gmail; 
        String subject = "Código Inicio de Sesión";
        String body = String.valueOf(codigo);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public boolean verificacionInicio(String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            Statement declaración = conexión.createStatement();
            String consultaSQL = "SELECT inicio FROM usuarios where inicio = 1 and uuid = "+"'"+uuid+"'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);
            while (resultados.next()) {
                if (resultados.getInt("inicio") == 1) {
                    System.out.println("True");
                    return true;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void inicioAutomatico(String nombre) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            String consultaSQL = "UPDATE usuarios SET inicio = 1 where alias =" + "'" + nombre + "'";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String comprobarUsuarioAutomatico(String uuid) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            Statement declaración = conexión.createStatement();
            String consultaSQL = "SELECT alias FROM usuarios where inicio = 1 AND uuid = "+"'"+uuid+"'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);
            while (resultados.next()) {
                return resultados.getString("alias");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public String comprobarUuidAutomatico(String usuarioP) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            Statement declaración = conexión.createStatement();
            String consultaSQL = "SELECT uuid FROM usuarios where alias = "+"'"+usuarioP+"'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);
            while (resultados.next()) {
                return resultados.getString("alias");
            }

        } catch (Exception e) {
            System.out.println("No se a encontrado el alias");
        }
        return null;
    }
    public void actualizarUuidUsuario(String uuid,String nombre){
        
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            String consultaSQL = "UPDATE usuarios SET uuid = "+"'"+uuid+"'"+" where alias =" + "'" + nombre + "'";
            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {

                preparedStatement.executeUpdate();
            } catch (Exception e) {
               
                System.out.println("Error uuid"+  e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }

    public int comprobarIdAutomatico(String uuid) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            Statement declaración = conexión.createStatement();
            String consultaSQL = "SELECT idUsuario FROM usuarios where inicio = 1 and uuid = "+"'"+uuid+"'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);
            while (resultados.next()) {
                return resultados.getInt("idUsuario");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String configurarColor(String uuid) {

        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            Statement declaración = conexión.createStatement();
            String consultaSQL = "SELECT color FROM usuarios where inicio = 1 and uuid = "+"'"+uuid+"'";
            ResultSet resultados = declaración.executeQuery(consultaSQL);
            while (resultados.next()) {
                return resultados.getString("color");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void cambiarColor(String color,String uuid) {
        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            String consultaSQL = "UPDATE usuarios SET color =" + "'" + color + "'" + " where inicio = 1 and uuid= "+"'"+uuid+"'";

            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int obtenerIdUsuarioActual(String alias) {
        int idUsuario = 0;

        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {
            String consultaSQL = "SELECT idUsuario FROM usuarios WHERE alias = ?";
            PreparedStatement statement = conexión.prepareStatement(consultaSQL);
            statement.setString(1, alias);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idUsuario = resultSet.getInt("idUsuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idUsuario;
    }

    private void cerrarAutomatico(String nombre) {

        try (Connection conexión = DriverManager.getConnection(url, usuario, contraseña)) {

            String consultaSQL = "UPDATE usuarios SET inicio = 0,uuid='' where alias =" + "'" + nombre + "'";

            try (PreparedStatement preparedStatement = conexión.prepareStatement(consultaSQL)) {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cerrarSesionAutomatica(String uuid) {

        cerrarAutomatico(comprobarUsuarioAutomatico(uuid));

    }

}
