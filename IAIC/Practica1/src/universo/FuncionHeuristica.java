package universo;

import aima.search.framework.HeuristicFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author GabiPC
 */
public class FuncionHeuristica implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        Double heuristica = null;
        try {
            heuristica = ((Estado) estado).obtenerValorHeuristico();
        } catch (Exception ex) {
            Logger.getLogger(FuncionHeuristica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return heuristica;
    }

}
