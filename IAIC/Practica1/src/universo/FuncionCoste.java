package universo;

import aima.search.framework.StepCostFunction;

/**
 *
 * @author Grupo C15
 */
class FuncionCoste implements StepCostFunction{

	/**
	 * Devuelve el coste que supone el paso de un estado origen a un estado destino
	 */
    public Double calculateStepCost(Object origen, Object destino, String accion) {
        return ((Estado)origen).generarCoste((Estado)destino);
    }

}
