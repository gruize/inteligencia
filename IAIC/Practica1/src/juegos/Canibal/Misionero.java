package juegos.Canibal;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.UniformCostSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import juegos.Juego;

/**
 *
 * @author GabiPC
 */
public class Misionero implements Juego{

    private String nombre;
    private boolean solucion;
    private SearchAgent agente = null;
    private Search busqueda = null;
    private Problem problema = null;

    public Misionero(){
    
        try{
            this.nombre = "Misioneros vs. Canibales";
            this.solucion = false;
            this.problema = new Problem(new Estado(this), new FuncionSucesora(), new EstadoObjetivo(this));
            //this.busqueda = new BreadthFirstSearch(new TreeSearch());
            this.busqueda = new UniformCostSearch(new TreeSearch());           
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public SearchAgent getAgente() {
        return agente;
    }

    public void setAgente(SearchAgent agente) {
        this.agente = agente;
    }

    public Search getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Search busqueda) {
        this.busqueda = busqueda;
    }

    public String getNombre() {
        return nombre;
    }

    public Problem getProblema() {
        return problema;
    }

    public void setProblema(Problem problema) {
        this.problema = problema;
    }

    public boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public boolean permiteControlCiclos() {
		return !this.busqueda.getClass().getName().contains("IterativeDeepeningSearch");
	}

    public boolean ejecutar(){
        try{
            this.agente = new SearchAgent(this.problema,this.busqueda);
            System.out.println("Estado inicial: ( 3 , 3 , Izquierda )");
            this.printActions(this.agente.getActions());
            this.printInstrumentation(this.agente.getInstrumentation());        
            if(this.getSolucion())
                System.out.println("Es Solución");
            else
                System.out.println("NO es solución");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return this.getSolucion();
    }

	private String printInstrumentation(Properties properties) {
		String impresion = "";
		Iterator keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
			impresion += key + " : " + property+"\n";
		}
		return impresion;
	}

	private String printActions(List actions) {
		String impresion = "";
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
			impresion+=action+"\n";
		}
		return impresion;
	}

    public String getDatos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String imprimir(List eventos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String imprimirPropiedades(Properties propiedades) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
