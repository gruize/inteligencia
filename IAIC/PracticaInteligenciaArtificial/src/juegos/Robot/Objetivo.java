package juegos.Robot;

import aima.search.framework.GoalTest;

/**
 *
 * @author GabiPC
 */
class Objetivo implements GoalTest{

    private Robot wallie;
    
    public Objetivo(Robot robot){
        this.wallie = robot;
    }
    
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
