package juegos.Reinas;

import aima.basic.XYLocation;
import aima.search.framework.GoalTest;
import java.util.List;

/**
 *
 * @author usuario_local
 */
class Objetivo implements GoalTest{
    
    private Estado estado;
    private Reinas reino;

    public Objetivo(Reinas reino) {
        this.reino = reino;
    }       
    
    public boolean reinasColocadasSinAtaques(List posicionReinas){
        boolean reinas = true;
        for(int i = 0; i < posicionReinas.size(); i++){
            XYLocation pos = (XYLocation) posicionReinas.get(i);
            if(this.estado.casillaAtacada(pos))
                reinas = false;
        }
        return reinas;
    }
    public boolean isGoalState(Object estado) {
        boolean goal = false;
        this.estado = (Estado)estado;
        if((this.estado.getTamanno() == this.estado.numeroReinasEnTablero()) && (this.reinasColocadasSinAtaques(this.estado.posicionReinas())))
            goal = true;
        this.reino.setSolucion(goal);
        return goal;
    }

}
