/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegos.Reinas;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author usuario_local
 */
public class FuncionHeuristica implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        return ((Estado)estado).getTamanno() - ((Estado)estado).numeroReinasEnTablero();
    }

}
