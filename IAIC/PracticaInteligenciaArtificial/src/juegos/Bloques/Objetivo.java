package juegos.Bloques;

import aima.search.framework.GoalTest;

public class Objetivo implements GoalTest {

	private Bloques source = null;
   
	public Objetivo(Bloques source) {
            this.source = source;
    }
    
	public boolean isGoalState(Object state) {
		boolean goal = (((Estado)state).getBloques().getBloques()[0].equals(Posiciones.B)
	            && ((Estado)state).getBloques().getBloques()[1].equals(Posiciones.C)
	            && ((Estado)state).getBloques().getBloques()[2].equals(Posiciones.mesa));
		this.source.setSolucion(goal);
		return goal;
    }

}
