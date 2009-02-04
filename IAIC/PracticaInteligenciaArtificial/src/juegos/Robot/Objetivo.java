package juegos.Robot;

import aima.search.framework.GoalTest;

/**
 *
 * @author Grupo C15
 */
class Objetivo implements GoalTest{

    private Robot wallie;
    
    /**
     * Constructor parametrizado
     * @param robot Controlador del juego
     */
    public Objetivo(Robot robot){
        this.wallie = robot;
    }
    
    @Override
    public boolean isGoalState(Object estado) {
        boolean goal = false;
        if((((Estado)estado).getInstante().posRobot().getXCoOrdinate() == ((Estado)estado).getPosFinal().getXCoOrdinate())
         &&(((Estado)estado).getInstante().posRobot().getYCoOrdinate() == ((Estado)estado).getPosFinal().getYCoOrdinate())){
            goal = true;
            this.wallie.setSolucion(goal);
        }
        return goal;
    }

}
