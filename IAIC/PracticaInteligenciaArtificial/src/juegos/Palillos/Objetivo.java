package juegos.Palillos;

import aima.search.framework.GoalTest;

public class Objetivo implements GoalTest{

	private Palillos palos;
	
	public Objetivo(Palillos palillos) {
		this.palos = palillos;
	}

	@Override
	public boolean isGoalState(Object state) {
		boolean goal = ((((Estado)state).getInstante().getPalillos() == 0) && 
				         (((Estado)state).getInstante().getTurno()%2 == 0));
		this.palos.setSolucion(goal);
		return goal;
	}

}
