package pe.codegym.modulo1.strategy;

public class ValidarVacio implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        boolean cadenaValida=true;
        if(!cadena.matches("^[^\\s].*")||cadena.isEmpty()){
            cadenaValida=false;
        }
        return cadenaValida;
    }
}
