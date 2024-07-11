package pe.codegym.modulo1;

import pe.codegym.modulo1.singleton.Alfabeto;

public enum Errores {
    ARCHIVO_NO_ENCONTRADO("El archivo no se encontró"),
    CLAVE_NO_NUMERO("No es una clave valida, use un número menor a "+ Alfabeto.getInstancia().getAlfabeto().length()),
    CAMPO_VACIO("Ingrese el dato requerido");

    private String mensaje = "";

    Errores(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return mensaje;
    }
}
