package juegos.Connect4;

import aima.search.framework.GoalTest;

/**
 *
 * @author GabiPC
 */
class Objetivo implements GoalTest{

    private Connect4 conecta;
    
    public Objetivo(Connect4 conecta){
        this.conecta = conecta;
    }
    
    public boolean isGoalState(Object estado) {
        boolean goal = false;
        //Vertical
        int i = 0, cont = 0, j = 0;
        Estado actual = (Estado)estado;
        while(!goal && (i < actual.getInstante().getTamanno())){
            while(!goal && (j < actual.getInstante().getTamanno())){
                
            }
            i++;
            j = 0;
        }
        //Horizontal
        
        //Diagonal derecha
        
        //Diagonal izquierda
        
        return goal;
    }

}
