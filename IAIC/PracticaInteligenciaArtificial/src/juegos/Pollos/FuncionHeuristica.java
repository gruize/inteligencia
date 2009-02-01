package juegos.Pollos;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author GabiPC
 */
public class FuncionHeuristica implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).generarHeuristica();
    }

}
