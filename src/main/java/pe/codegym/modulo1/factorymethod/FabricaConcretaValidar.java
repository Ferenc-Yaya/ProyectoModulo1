package pe.codegym.modulo1.factorymethod;

import pe.codegym.modulo1.Errores;
import pe.codegym.modulo1.strategy.*;

public class FabricaConcretaValidar extends FabricaValidar{
    @Override

    public EstrategiaValidar crearEstrategiaValidar(String tipoValidar) {

        Errores errores= Errores.valueOf(tipoValidar);
        switch (errores){
            case ARCHIVO_NO_ENCONTRADO:
                return new ValidarArchivoExiste();
            case CLAVE_NO_NUMERO:
                return new ValidarNumero();
            case CAMPO_VACIO:
                return new ValidarVacio();
        }
        return null;
    }
}
