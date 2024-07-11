package pe.codegym.modulo1.strategy;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidarArchivoExiste implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        return Files.exists(Path.of(cadena));
    }
}
