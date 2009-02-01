package juegos.Mono;

import aima.search.framework.HeuristicFunction;

public class FuncionHeuristica implements HeuristicFunction {

	@Override
	public double getHeuristicValue(Object state) {
		return ((Estado)state).generarHeuristica();
	}

}
