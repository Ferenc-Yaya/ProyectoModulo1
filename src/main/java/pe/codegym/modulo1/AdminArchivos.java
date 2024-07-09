package pe.codegym.modulo1;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AdminArchivos {
    private Path ruta;
    private static final String PROPERTY="clave.property";

    public AdminArchivos(Path ruta) {
        this.ruta = ruta;
    }

    public String lee(String nombreArchivo){
//        String mensaje="";
//        try(BufferedReader br= new BufferedReader(new FileReader(this.ruta.resolve(nombreArchivo).toString()))){
//            int charCode;
//           while((charCode = br.read())!=-1){
//               char character = (char) charCode;
//               mensaje+=character;
//           }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return mensaje;
        String mensaje="";
        try{
            mensaje=Files.readString(this.ruta.resolve(nombreArchivo));
        }catch (IOException e){
            e.printStackTrace();
        }
        return mensaje;
    }
    public void escribe(String mensaje,String nombreArchivo){
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.ruta.resolve(nombreArchivo).toString()))) {
//            bw.write(mensaje);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try{
            Files.writeString(this.ruta.resolve(nombreArchivo),mensaje);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String leeProperty(){
        Properties properties=new Properties();
        String respuesta="";
//        try(BufferedReader br=new BufferedReader(new FileReader(ruta.resolve(PROPERTY).toString()))){
//            properties.load(br);
//            respuesta=properties.getProperty("clave");
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        try{
            respuesta=Files.readString(ruta.resolve(PROPERTY));
        }catch (IOException e){
            e.printStackTrace();
        }
        return respuesta;
    }

    public void escribeProperty(String clave){
        Properties properties=new Properties();
        properties.setProperty("clave",clave);
//        try (BufferedWriter bw=new BufferedWriter(new FileWriter(ruta.resolve(PROPERTY).toString()))){
//            properties.store(bw,"");
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        try{
            Files.writeString(ruta.resolve(PROPERTY),clave);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
