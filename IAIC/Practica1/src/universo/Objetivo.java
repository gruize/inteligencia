package universo;

import aima.search.framework.GoalTest;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import universo.util.Nodo;

/**
 *
 * @author usuario_local
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

    /**
     * FixMe: Puede dar fallos. Mirar como funciona lo de la obtencionde las claves
     * en la Hashtable
     */
    public boolean isGoalState(Object estado) {
        boolean goal = false;
        Estado actual = (Estado)estado;
        if(this.objetivos.containsKey(actual.getActual().getId()))
            goal = true;
        //Agrega el nodo actual al recorrido porque ya ha pasado por él.
        actual.getRecorridos().add(actual.getActual());      
        this.galaxia.setSolucion(goal);
        return goal;
    }

}
