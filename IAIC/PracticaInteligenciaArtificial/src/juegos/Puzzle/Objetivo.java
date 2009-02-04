package juegos.Puzzle;

import aima.search.framework.GoalTest;

/**
 *
 * @author Grupo C15
 */
public class Objetivo implements GoalTest{

    private Puzzle puzzle;
    
    /**
     * Constructor parametrizado
     * @param aThis Control del juego
     */
    public Objetivo(Puzzle aThis) {
        this.puzzle = aThis;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public boolean isGoalState(Object estado) {
        this.puzzle.setSolucion(((Estado)estado).obtenerValorHeuristico() == 0.0);
        return this.getPuzzle().getSolucion();
    }

}
