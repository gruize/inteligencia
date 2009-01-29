package juegos.Puzzle;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author usuario_local
 */
public class FuncionHeuristica implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).obtenerValorHeuristico();
    }

}
