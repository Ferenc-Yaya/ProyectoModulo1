package pe.codegym.modulo1.strategy;

public class ValidarVacio implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        return !cadena.isEmpty();
    }
}
