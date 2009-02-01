package juegos.CajaColores;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.UniformCostSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import juegos.Juego;

/**
 *
 * @author usuario_local
 */
public class CajaDeColores implements Juego{

    private String nombre;
    private boolean solucion;
    private Search busqueda = null;
    private Problem problema = null;
    private SearchAgent agente = null;
    private Estado estado = null;
    private Objetivo objetivo = null;
    
    public CajaDeColores() {
        try {
            this.nombre = "Caja de Colores Rojo vs Azul";
            this.estado = new Estado(3);
            this.objetivo = new Objetivo(this);
            this.problema = new Problem(this.estado,new FuncionSucesora(),this.objetivo,new FuncionHeuristica());
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
        //this.busqueda = new BreadthFirstSearch(new TreeSearch());
        //this.busqueda = new UniformCostSearch(new TreeSearch());
        //this.busqueda = new AStarSearch(new TreeSearch());
        //this.busqueda = new GreedyBestFirstSearch(new TreeSearch());
        this.busqueda = new AStarSearch(new GraphSearch());
        try {
            this.agente = new SearchAgent(this.problema,this.busqueda);
            this.imprimir(this.agente.getActions());
            this.printInstrumentation(this.agente.getInstrumentation());
            if(this.getSolucion())
                System.out.println("ES solucion");
            else
                System.out.println("NO es solucion");
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.getSolucion();
    }

    public String imprimir(List acciones){
        String ret = "";
        for (int i = 0; i < acciones.size(); i++) {
                String action = (String) acciones.get(i);
                System.out.println(action);
                ret+=action+"\n";
        }
        return ret;
    }
    
    public String printInstrumentation(Properties propiedades){
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

    public String getDatos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String imprimirPropiedades(Properties propiedades) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
