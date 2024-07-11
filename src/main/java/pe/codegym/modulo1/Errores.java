package pe.codegym.modulo1;

public enum Errores {
    ARCHIVO_NO_ENCONTRADO("El archivo no se encontró"),
    CLAVE_NO_NUMERO("No es una clave valida"),
    CAMPO_VACIO("Ingrese el dato requerido"),
    TXT_NO_VALIDO("No escriba espacios en blanco al inicio del nombre");

    private String mensaje = "";

    Errores(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getMensaje(){
        return mensaje;
    }
}
