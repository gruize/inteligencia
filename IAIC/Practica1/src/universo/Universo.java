package universo;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthFirstSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.UniformCostSearch;
import conector.Conector;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Universo, establece los objetos necesarios para realizar los recorridos 
 * necesarios por todos los estados que necesite generar y a su vez, escoger el mejor 
 * camino segun el metodo de busqueda que se especifique, ya sea de forma manual o 
 * aleatoriamente. 
 * @author Grupo C15 
 */
public class Universo {

    private String nombre;
    private boolean solucion;
    private Search busqueda = null;
    private SearchAgent agente = null;
    private Problem problema = null;
    private Estado estado = null;
    private Objetivo objetivo = null;

    /** 
     * Constructor por defecto
     */
    public Universo(){
        try {
            this.nombre = "Universo";
            this.solucion = false;
            this.estado = new Estado();
            this.objetivo = new Objetivo(this);
            this.problema = new Problem(this.estado, new FuncionSucesora(), this.objetivo,new FuncionCoste(),new FuncionHeuristica());
        } catch (Exception ex) {
            Logger.getLogger(Universo.class.getName()).log(Level.SEVERE, null, ex);
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

    
    /**
     * Establece el metodo de busqueda y hace las llamadas necesarias a las diferentes 
     * clases, para obtener una lista de acciones que deben realizarse segun el algoritmo
     * establecido para poder conseguir el mejor recorrido llegando a sus objetivos  
     * @return
     */
    public boolean ejecutar() {
        //this.busqueda = new BreadthFirstSearch(new TreeSearch());
        //this.busqueda = new UniformCostSearch(new TreeSearch());
        this.busqueda = new AStarSearch(new TreeSearch());
        //this.busqueda = new GreedyBestFirstSearch(new TreeSearch());
        //this.busqueda = new AStarSearch(new GraphSearch());
    	//this.busqueda = new DepthFirstSearch(new GraphSearch());
    	//this.busqueda = new DepthLimitedSearch(10);
    	//this.busqueda=new IterativeDeepeningSearch();
        try{
            this.agente = new SearchAgent(this.problema,this.busqueda);
            this.imprimir(this.agente.getActions());
            this.imprimirPropiedades(this.agente.getInstrumentation());
            if(this.getSolucion())
                System.out.println("ES solucion");
            else
                System.out.println("NO es solucion");
        }catch(Exception e){
            System.out.println(e);
        }
        return this.getSolucion();
    }

    /**
     * Genera un String con todos las acciones que deben realizarse
     * @param eventos Listado de acciones que deben realizarse
     * @return String con el listado de acciones concatenados
     */
    public String imprimir(List eventos){
        String ret = "";
        for (int i = 0; i < eventos.size(); i++) {
            String action = (String) eventos.get(i);
            System.out.println(action);
            ret+=action+"\n";
        }
        return ret;
    }
    
    /**
     * Genera un String con las propiedades resultantes del recorrido
     * @param propiedades Propiedades obtenidas
     * @return String con las propiedades concatenadas
     */
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
    
    /**
     * Devuelve la certeza de si el metodo de busqueda que se esta utilizando realiza
     * el control de ciclos
     * @return 	True si controla los ciclos
     * 			False en caso contrario
     */
    public boolean controlaCiclos(){
        return !this.busqueda.getClass().getName().contains("IterativeDeepeningSearch");
    }
}