package juegos.LoboCabraCol;


import aima.search.framework.GoalTest;

public class LCCObjetivo implements GoalTest{
    
    private LCC source = null;
    
    public LCCObjetivo(LCC source) {
            this.source = source;
    }
    
    @Override
    public boolean isGoalState(Object state) {
            if (((LCCEstado) state).isFinal()) {
                    source.setSolucion(true);
                    return true;
            } else
                    return false;

    }
}
