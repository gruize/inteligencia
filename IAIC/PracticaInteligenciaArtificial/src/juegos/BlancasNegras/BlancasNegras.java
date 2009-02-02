	package juegos.BlancasNegras;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import juegos.Juego;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.uninformed.DepthLimitedSearch;

/**
 *
 * @author GabiPC
 */
public class BlancasNegras implements Juego {

    private String nombre;
    private boolean solucion;
    private Search busqueda = null;
    private Problem problema = null;
    private SearchAgent agente = null;
    private Estado estado = null;
    private Objetivo objetivo = null;

    public BlancasNegras() {
        try{
            this.nombre = "Blancas vs. Negras";
            this.solucion = false;
            this.objetivo = new Objetivo(this);
            this.estado = new Estado(this);
            this.problema = new Problem(this.estado, new FuncionSucesora(), this.objetivo, new FuncionCoste(), new FuncionHeuristica());
        }catch(Exception e){
            System.out.println(e);
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
    
    public boolean ejecutar() {
        this.busqueda = new DepthLimitedSearch(10);
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

    public String imprimir(List eventos){
        Momento inicial = new Momento();
        String ret = "Estado inicial : [ "+inicial.getFichas(0)+" , "+inicial.getFichas(1)+" , "+inicial.getFichas(2)+" , "+inicial.getFichas(3)+" , "+inicial.getFichas(4)+" , "+inicial.getFichas(5)+" , "+inicial.getFichas(6)+" ]\n";
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
    
    public boolean controlaCiclos(){
        return !this.busqueda.getClass().getName().contains("IterativeDeepeningSearch");
    }

}
