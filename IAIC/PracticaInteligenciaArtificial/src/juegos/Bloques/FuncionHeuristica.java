package juegos.Bloques;

import aima.search.framework.HeuristicFunction;

public class FuncionHeuristica implements HeuristicFunction {

    public double getHeuristicValue(Object state) {
        Estado bloques = (Estado) state;
        double heuristica = 0;
        int i;
        for (i = 0; i < 3; i++)
                switch (i) {
                case 0:
                        if (!bloques.getBloques().getBloques()[i].equals(Posiciones.B))
                                heuristica++;
                        break;
                case 1:
                        if (!bloques.getBloques().getBloques().equals(Posiciones.C))
                                heuristica++;
                        break;
                case 2:
                        if (!bloques.getBloques().getBloques().equals(Posiciones.mesa))
                                heuristica++;
                        break;
                }
        return heuristica;
}

}
