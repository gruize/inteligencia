package juegos.BlancasNegras;

import aima.search.framework.StepCostFunction;

/**
 *
 * @author GabiPC
 */
public class FuncionCoste implements StepCostFunction{

    public Double calculateStepCost(Object origen, Object destino, String accion) {
        Estado padre = (Estado)origen;
        Estado hijo = (Estado)destino;
        return padre.generarCoste(hijo);
    }

}
