package pe.codegym.modulo1.strategy;

import pe.codegym.modulo1.singleton.Alfabeto;

public class ValidarNumero implements EstrategiaValidar {
    @Override
    public boolean validar(String cadena) {
        boolean respuesta=true;
        int claveMax=Alfabeto.getInstancia().getAlfabeto().length();
        if(!cadena.matches("-?\\d+")) {
            respuesta = false;
        }else if(!(Math.abs(Integer.parseInt(cadena))<claveMax)){
            respuesta=false;
        }
        return respuesta;
    }
}
