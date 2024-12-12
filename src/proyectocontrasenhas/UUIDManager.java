/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocontrasenhas;

/**
 *
 * @author micro
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.UUID;

public class UUIDManager {

    private static final String UUID_FILE_PATH = "uuid.txt";

   

    public  UUID getOrCreateUUID() {
        File file = new File(UUID_FILE_PATH);

        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();

                String uuidString = new String(data);
                return UUID.fromString(uuidString);
            } else {
                UUID newUUID = UUID.randomUUID();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(newUUID.toString().getBytes());
                fos.close();
                return newUUID;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}
