package juegos.Canibal;

import aima.search.framework.GoalTest;
import java.util.Vector;

/**
 *
 * @author GabiPC
 */
public class EstadoObjetivo implements GoalTest{

    private Misionero mision;

    /**
     * Constructor parametrizado
     * @param mision
     */
    public EstadoObjetivo(Misionero mision) {
        this.mision = mision;
    }

    @Override
    public boolean isGoalState(Object state) {
        boolean resultado = ((Estado)state).getDatos().equals(new Orilla(0,0,false));
        this.mision.setSolucion(resultado);
        return resultado;
    }


}
