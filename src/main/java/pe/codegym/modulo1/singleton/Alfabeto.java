package pe.codegym.modulo1.singleton;

public class Alfabeto {
    private static Alfabeto alfabeto;
    private static final String ALFABETO="abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ123456789 !¡¿?$%&/()=><;:,.-_áéíóú";
    private Alfabeto() {
    }
    public static Alfabeto getInstancia(){
        if (alfabeto == null)
            alfabeto = new Alfabeto();
        return alfabeto;
    }

    public String getAlfabeto(){
        return ALFABETO;
    }
}
