import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminArchivos {
    public static final Path RUTA= Paths.get("ficheros");

    public String lee(String nombreArchivo){
        try(BufferedReader br= new BufferedReader(new FileReader(RUTA.resolve(nombreArchivo).toString()))){
           String mensaje="";
           while(br.read()!=-1){
               mensaje+=br.read();
           }
           return mensaje;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void escribe(String mensaje,String nombreArchivo){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA.resolve(nombreArchivo).toString()))) {
            bw.write(mensaje);
        } catch (IOException a) {
            throw new RuntimeException(a);
        }
    }
}
