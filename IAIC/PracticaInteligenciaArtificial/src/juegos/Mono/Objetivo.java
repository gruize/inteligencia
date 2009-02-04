package juegos.Mono;

import aima.search.framework.GoalTest;

public class Objetivo implements GoalTest{

	private Mono jungla;
	
	/**
	 * Constructor parametrizado
	 * @param jungla
	 */
	public Objetivo(Mono jungla) {
		this.jungla = jungla;
	}

	@Override
	public boolean isGoalState(Object state) {
		boolean goal = ((Estado)state).getInstante().getPlatano();
		this.jungla.setSolucion(goal);
		return goal;
	}

}
