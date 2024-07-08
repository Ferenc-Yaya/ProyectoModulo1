package pe.codegym.modulo1;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validador {
//    public boolean isValidKey(int key, String alfabeto) {
//       // alfabeto.i
//        return true;
//    }
    public boolean isFileExists(Path ruta) {
        return Files.exists(ruta);
    }
}
