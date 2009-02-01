package juegos.BlancasNegras;

import aima.search.framework.GoalTest;

/**
 *
 * @author GabiPC
 */
public class Objetivo implements GoalTest {

    private BlancasNegras bn;
    private MomentosObjetivo objetivos;
    
    Objetivo(BlancasNegras aThis) {
        this.bn = aThis;
        this.objetivos = new MomentosObjetivo();
    }

    public boolean isGoalState(Object estado) {
        boolean goal = false;
        int i = 0;
        while((!goal) && (i < this.objetivos.getMomentosObjetivo().size())){
            if(((Estado)estado).getInstante().equals(this.objetivos.getMomentosObjetivo().get(i)))
                goal = true;
            i++;
        }
        this.bn.setSolucion(goal);
        return goal;
    }

}
