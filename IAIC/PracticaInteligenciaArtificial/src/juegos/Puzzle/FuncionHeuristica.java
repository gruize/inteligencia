package juegos.Puzzle;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author Grupo C15
 */
public class FuncionHeuristica implements HeuristicFunction{

	@Override
    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).obtenerValorHeuristico();
    }

}
