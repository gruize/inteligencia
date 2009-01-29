package universo;

import aima.search.framework.StepCostFunction;

/**
 *
 * @author usuario_local
 */
class FuncionCoste implements StepCostFunction{

    public FuncionCoste() {
    }

    public Double calculateStepCost(Object origen, Object destino, String accion) {
        return ((Estado)origen).generarCoste((Estado)destino);
    }

}
