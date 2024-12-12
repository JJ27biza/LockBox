/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author DaniDisplayPort
 */
public class Ficheros {

    private ArrayList<Cuentas> arrayExportar = new ArrayList<>();
    private String usuario;
    private String correo;
    private String contraseña;
    private String referencia;
    private String descripcion;
    private boolean insta;
    private boolean youtube;
    private boolean gmail;
    private int idUsuario;

    public void escribirArchivo(String texto) {

        FileWriter writer;

        try {

            File file = new File("C:/Cuentas/");
            if (!file.exists()) {
                file.mkdirs();
            }
            writer = new FileWriter("C:/Cuentas/output.txt");
            writer.write(texto);

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Cuentas> leerArchivo(String ruta) {

        try (FileReader fr = new FileReader(ruta)) {
            BufferedReader br = new BufferedReader(fr);

            String linea;
            String[] stringArray = null;
            int a = 0;
            while ((linea = br.readLine()) != null) {

                stringArray = linea.split(",");

                usuario = stringArray[0];
                correo = stringArray[1];
                contraseña = stringArray[2];
                referencia = stringArray[3];
                descripcion = stringArray[4];
                insta = Boolean.valueOf(stringArray[5]);
                youtube = Boolean.valueOf(stringArray[6]);
                gmail = Boolean.valueOf(stringArray[7]);
                Cuentas cuentas= new Cuentas(usuario,correo,contraseña,referencia,descripcion,insta,gmail,youtube);
                arrayExportar.add(cuentas);
                
            }
            return arrayExportar;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
