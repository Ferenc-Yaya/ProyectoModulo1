package pe.codegym.modulo1;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminArchivos {
    private Path ruta;

    public AdminArchivos(Path ruta) {
        this.ruta = ruta;
    }

    public String lee(String nombreArchivo){
        try(BufferedReader br= new BufferedReader(new FileReader(this.ruta.resolve(nombreArchivo).toString()))){
           String mensaje="";
            int charCode;
           while((charCode = br.read())!=-1){
               char character = (char) charCode;
               mensaje+=character;
           }
           return mensaje;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void escribe(String mensaje,String nombreArchivo){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.ruta.resolve(nombreArchivo).toString()))) {
            bw.write(mensaje);
        } catch (IOException a) {
            throw new RuntimeException(a);
        }
    }
}
