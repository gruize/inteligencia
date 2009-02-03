package juegos.LoboCabraCol;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import juegos.Juego;
import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.uninformed.BidirectionalSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthFirstSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
import aima.search.uninformed.UniformCostSearch;

public class LCC implements Juego {
	private Search busqueda = null;
	private Problem problema = null;
	private boolean solucion = false;
	private String nombre = null;
	private SearchAgent agente = null;

        public String generarAccion(LCCEstado actual){
            String accion = "";
            if(actual.getEstado().isGranjero())
                accion+="Granjero Izquierda, ";
            else
                accion+="Granjero Derecha, ";
            if(actual.getEstado().isLobo())
                accion+="Lobo Izquierda, ";
            else
                accion+="Lobo Derecha, ";
            if(actual.getEstado().isCabra())
                accion+="Cabra Izquierda, ";
            else
                accion+="Cabra Derecha, ";
            if(actual.getEstado().isCol())
                accion+="Col Izquierda )";
            else
                accion+="Col Derecha )";
            return accion;
        }
                
	public LCC(){		
		try {
			this.nombre = "Problema del Lobo la Cabra y la Col";
			//System.out.println("Comienza el problema del Lobo la Cabra y la Col");
			this.problema = new Problem(new LCCEstado(),
					new LCCFuncionSucesor(), new LCCObjetivo(this));			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//	 Para imprimir resultados
	private String printInstrumentation(Properties properties) {
		String ret = "";
		Iterator keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
			ret += key + " : " + property+"\n";
		}
		return ret;
	}

	public String imprimir(List actions) {
		String ret = "";
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
			ret+=action+"\n";
		}
		return ret;
	}

	public String getNombre() {
		return nombre;
	}

	public void setSolucion(boolean s) {
		solucion = s;
	}

	public void setBusqueda(Search s) {

		busqueda = s;
	}

	public boolean ejecutar() {
        this.busqueda = new IterativeDeepeningSearch();
		try {
			this.agente = new SearchAgent(this.problema, this.busqueda);
            System.out.println("Estado inicial: ( "+this.generarAccion(new LCCEstado()));
			imprimir(agente.getActions());
			printInstrumentation(agente.getInstrumentation());
                        if(this.getSolucion())
                            System.out.println("Es solucion");
                        else
                            System.out.println("NO es solucion");
		} catch (Exception e) {
			System.out.println(e);
		}
                return this.getSolucion();
	}

	public boolean getSolucion() {
		return this.solucion;
	}
	public String getDatos(){
		return imprimir(agente.getActions())+printInstrumentation(agente.getInstrumentation());
	}
	
    public SearchAgent getAgente() {
        return agente;
    }

    public String imprimirPropiedades(Properties propiedades) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

