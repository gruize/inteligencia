/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegos.Reinas;

import aima.search.framework.GoalTest;
import aima.search.framework.Problem;
import juegos.Juego;

/**
 *
 * @author usuario_local
 */
public class ReinasSinHeuristica extends Reinas implements Juego{

    public ReinasSinHeuristica() {
        this.nombre = "Problema de las N Reinas Sin Heuristica";
        this.solucion = false;
        this.estado = new Estado(this,8);
        this.objetivo = new Objetivo(this);
        this.problema = new Problem(this.estado,new FuncionSucesor(),(GoalTest) this.objetivo);
    }    
    
}
