package universo;


import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.uninformed.BreadthFirstSearch;
import conector.Conector;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Universo {

    public static Conector planetas = null;
    private String nombre;
    private boolean solucion;
    private Search busqueda = null;
    private SearchAgent agente = null;
    private Problem problema = null;
    private Estado estado = null;
    private Objetivo objetivo = null;

    public Universo(){
        try {
            this.nombre = "Universo";
            this.solucion = false;
            Universo.planetas = Universo.getInstancia();
            this.estado = new Estado();
            this.objetivo = new Objetivo(this);
            this.problema = new Problem(this.estado, new FuncionSucesora(this), this.objetivo, new FuncionCoste(), new FuncionHeuristica());
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

    public static Conector getPlanetas() {
        return planetas;
    }

    public static void setPlanetas(Conector planetas) {
        Universo.planetas = planetas;
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
     	
    public static Conector getInstancia()throws Exception{
        if (planetas == null)
            planetas = new Conector("planetas.txt");
        return planetas;
    }
    
    public boolean ejecutar() {
        this.busqueda = new BreadthFirstSearch(new TreeSearch());
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
    
    public boolean controlaCiclos(){
        return !this.busqueda.getClass().getName().contains("IterativeDeepeningSearch");
    }
}