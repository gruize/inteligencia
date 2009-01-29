package universo;

import aima.search.framework.GoalTest;
import java.util.Enumeration;
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
            this.objetivos = aThis.getInstancia().getDestinos();
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
        int i = 0;
        while(!goal && (i < this.objetivos.size())){            
            Enumeration<Integer> clavesObjetivos = this.objetivos.keys();          
            if(actual.equals(objetivos.get(clavesObjetivos.nextElement())))
                goal = true;
            //Agrega el nodo actual al recorrido porque ya ha pasado por él.
            actual.getRecorridos().add(actual.getActual());
            i++;
        }
        this.galaxia.setSolucion(goal);
        return goal;
    }

}
