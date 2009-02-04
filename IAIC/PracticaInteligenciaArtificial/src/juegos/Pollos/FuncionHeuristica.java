package juegos.Pollos;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author GabiPC
 */
public class FuncionHeuristica implements HeuristicFunction{

	@Override
    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).generarHeuristica();
    }

}
