package pe.codegym.modulo1.strategy;

public class ValidarNumero implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {

        return cadena.matches("-?\\d+")? true:false;
    }
}
