package juegos.Robot;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author usuario_local
 */
class FuncionHeuristica implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).generarHeuristica();
    }

}
