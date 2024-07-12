package pe.codegym.modulo1.strategy;

public class ValidarVacio implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        boolean cadenaValida=true;
        try {
            if (!cadena.matches("^[^\\s].*") || cadena.isEmpty()) {//cadena="",cadena=null
                cadenaValida = false;
            }
        }catch(NullPointerException e){
            cadenaValida = false;
        }
        return cadenaValida;
    }
}
