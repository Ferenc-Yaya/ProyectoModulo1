package pe.codegym.modulo1.strategy;

public class ValidarTxt implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        boolean cadenaValida=true;
        if(!cadena.matches("^[^\\s].*")){
            cadenaValida=false;
        }
        return cadenaValida;
    }
}
