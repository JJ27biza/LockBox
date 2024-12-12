/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author micro
 */
public class InicioAutomatico {

    public void incioInstagram(String usuario, String contraseña) {

        System.setProperty("webdriver.chrome.driver", "src/recursos/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.instagram.com/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys(usuario);
        passwordField.sendKeys(contraseña);
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void incioGmail(String correo, String contraseña) {

        System.setProperty("webdriver.chrome.driver", "src/recursos/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://mail.google.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement emailField = driver.findElement(By.id("identifierId"));
        emailField.sendKeys(correo);
        WebElement nextButton = driver.findElement(By.xpath("//div[@id='identifierNext']"));
        nextButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='Passwd']"));
        passwordField.sendKeys(contraseña);

        WebElement signInButton = driver.findElement(By.xpath("//div[@id='passwordNext']"));
        signInButton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniciarSesionYoutube(String correo, String contraseña) {

        System.setProperty("webdriver.chrome.driver", "src/recursos/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.youtube.com");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginButton = driver.findElement(By.xpath("//a[@aria-label='Iniciar sesión']"));
        loginButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement emailField = driver.findElement(By.id("identifierId"));
        emailField.sendKeys(correo);

        WebElement nextButton = driver.findElement(By.xpath("//div[@id='identifierNext']"));
        nextButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='Passwd']"));
        passwordField.sendKeys(contraseña);

        WebElement signInButton = driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']"));
        signInButton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void mensajeGmail(String correo, String cuerpo) {
        final String username = "lockboxes24@gmail.com";
        final String password = "n d e p x u r g n v g m f m e u";

        String to = "lockboxes24@gmail.com";
        String subject = "Modulo Ayuda";
        String body = "Mensaje enviado por: " + correo + "\n" + cuerpo;
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

}
