package universo;

import aima.search.framework.GoalTest;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import universo.util.Nodo;

/**
 *
 * @author Grupo C15
 */
public class Objetivo implements GoalTest{

    private Universo galaxia;
    private Hashtable<Integer,Nodo> objetivos;
    
    public Objetivo(Universo aThis) {
        try {
            this.galaxia = aThis;
            this.objetivos = Universo.getInstancia().getDestinos();
        } catch (Exception ex) {
            Logger.getLogger(Objetivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isGoalState(Object estado) {
        boolean goal = false;
        Estado actual = (Estado)estado;
        if(this.objetivos.containsKey(actual.getActual().getId()))
            goal = true;     
        this.galaxia.setSolucion(goal);
        return goal;
    }

}
