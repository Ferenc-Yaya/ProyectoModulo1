package pe.codegym.modulo1.factorymethod;

import pe.codegym.modulo1.strategy.EstrategiaValidar;

public abstract class FabricaValidar {
    public abstract EstrategiaValidar crearEstrategia(String tipoValidar);
}
