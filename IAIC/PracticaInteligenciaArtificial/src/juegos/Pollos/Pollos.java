package juegos.Pollos;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.informed.HillClimbingSearch;
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
public class Pollos implements Juego{

    private String nombre;
    private boolean solucion;
    private Search busqueda = null;
    private SearchAgent agente = null;
    private Problem problema = null;
    private Objetivo objetivo = null;
    private Estado estado = null;
    
    public Pollos(){
        this.nombre = "Problema de los Pollos";
        this.solucion = false;
        this.objetivo = new Objetivo(this);
        this.estado = new Estado();
        this.problema = new Problem(this.estado,new FuncionSucesora(),this.objetivo,new FuncionCoste(),new FuncionHeuristica());
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
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
    
    public boolean ejecutar(){
        this.busqueda = new AStarSearch(new TreeSearch());   	       
        try{
            this.agente = new SearchAgent(this.problema,this.busqueda);
            this.imprimir(this.agente.getActions());
            this.imprimirPropiedades(this.agente.getInstrumentation());
            if(this.getSolucion())
                System.out.println("ES solución");
            else
                System.out.println("NO es solucion");
        }catch(Exception ex){
            System.out.println(ex);
        }
        return this.getSolucion();
    }

    public String imprimir(List eventos) {
        String ret = "";
        for (int i = 0; i < eventos.size(); i++) {
            String action = (String) eventos.get(i);
            System.out.println(action);
            ret+=action+"\n";
        }
        return ret;
    }

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
