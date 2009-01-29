package juegos.Pollos;

import aima.search.framework.GoalTest;

/**
 *
 * @author GabiPC
 */
public class Objetivo implements GoalTest {

    private Pollos granja;
    
    Objetivo(Pollos aThis) {
        this.granja = aThis;
    }

    public boolean isGoalState(Object estado) {
        boolean goal = false;
        if(((Estado)estado).generarHeuristica() == 0.0)
            goal = true;
        this.granja.setSolucion(goal);
        return goal;
    }

}
