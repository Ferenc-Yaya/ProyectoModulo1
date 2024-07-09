package pe.codegym.modulo1;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validador {
    public boolean isValidarClave(String clave) {
        return clave.matches("-?\\d+")? true:false;
    }
    public boolean isFileExists(Path ruta) {
        return Files.exists(ruta);
    }
}
