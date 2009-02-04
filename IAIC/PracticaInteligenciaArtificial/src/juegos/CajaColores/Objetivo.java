package juegos.CajaColores;

import aima.search.framework.GoalTest;

/**
 *
 * @author usuario_local
 */
public class Objetivo implements GoalTest{
    
    private CajaDeColores caja;

    /**
     * Constructor parametrizado
     * @param caja
     */
    public Objetivo(CajaDeColores caja) {
        this.caja = caja;
    }

    @Override
    public boolean isGoalState(Object state) {
        boolean resultado = (((Estado)state).obtenerValorHeuristico() == 0.0 );
        this.caja.setSolucion(resultado);
        return resultado;
    }
    
    
}
