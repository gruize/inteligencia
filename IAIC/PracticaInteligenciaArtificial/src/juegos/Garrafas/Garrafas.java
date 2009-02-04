package juegos.Garrafas;

import aima.search.framework.GoalTest;
import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.SuccessorFunction;
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

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import juegos.Juego;
/**
 *
 * @author GabiPC
 */
public class Garrafas implements Juego{

    private String nombre;
    private boolean solucion;
    private SearchAgent agente = null;
    private Search busqueda = null;
    private Problem problema = null;
    private GarrafaEstado estado = null;
    private GarrafaObjetivo objetivo = null;

    /**
     * Constructor por defecto
     */
    public Garrafas(){
        this.nombre = "Garrafas de capacidades diferentes";
        this.solucion = false;
        this.estado = new GarrafaEstado();
        this.objetivo = new GarrafaObjetivo(this);
        this.problema = new Problem(this.estado,(SuccessorFunction) new GarrafaFuncionSucesor(),(GoalTest) this.objetivo);        
    }

    public SearchAgent getAgente() {
        return agente;
    }

    public Search getBusqueda() {
        return busqueda;
    }

    public GarrafaEstado getEstado() {
        return estado;
    }

    public GarrafaObjetivo getObjetivo() {
        return objetivo;
    }

    public Problem getProblema() {
        return problema;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getSolucion() {
        return solucion;
    }

    public void setBusqueda(Search busqueda) {
        this.busqueda = busqueda;
    }

    public void setEstado(GarrafaEstado estado) {
        this.estado = estado;
    }

    public void setAgente(SearchAgent agente) {
        this.agente = agente;
    }

    public void setObjetivo(GarrafaObjetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void setProblema(Problem problema) {
        this.problema = problema;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    @Override
    public boolean ejecutar(){       
        this.busqueda = new BreadthFirstSearch(new TreeSearch());
    	try {
            System.out.println("Garrafa 3 : " + this.estado.getGarrafa3());
            this.agente = new SearchAgent(this.problema, this.busqueda);
            this.imprimir(this.agente.getActions());
            this.imprimirPropiedades(this.agente.getInstrumentation());
            if(this.getSolucion())
                System.out.println("ES solucion");
            else
                System.out.println("NO es solucion");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return this.getSolucion();
    }

    @Override
    public String imprimir(List eventos){
        String impresion = "";
        for(int i = 0; i < eventos.size(); i++){
            String aux = (String) eventos.get(i);
            System.out.println(aux);
            impresion = impresion + aux + "\n";
        }
        return impresion;
    }

    @Override
    public String imprimirPropiedades(Properties propiedades) {
		String ret = "";
		Iterator keys = propiedades.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = propiedades.getProperty(key);
			System.out.println(key + " : " + property);
			ret += key + " : " + property+"\n";
		}
		return ret;
    }

}
