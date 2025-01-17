package pe.codegym.modulo1.strategy;

public class ContextoValidar {
    private EstrategiaValidar estrategiaValidar;

    public ContextoValidar(EstrategiaValidar estrategiaValidar) {
        this.estrategiaValidar = estrategiaValidar;
    }

    public boolean validarCadena(String cadena){
        return this.estrategiaValidar.validar(cadena);
    }
}
