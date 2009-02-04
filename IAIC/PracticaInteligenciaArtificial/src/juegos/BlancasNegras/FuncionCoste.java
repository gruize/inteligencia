package juegos.BlancasNegras;

import aima.search.framework.StepCostFunction;

/**
 * @author Grupo C15
 */
public class FuncionCoste implements StepCostFunction{

	@Override
    public Double calculateStepCost(Object origen, Object destino, String accion) {
        Estado padre = (Estado)origen;
        Estado hijo = (Estado)destino;
        return padre.generarCoste(hijo);
    }

}
