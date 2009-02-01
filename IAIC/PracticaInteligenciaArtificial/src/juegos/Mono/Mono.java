package juegos.Mono;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import juegos.Juego;

public class Mono implements Juego {

	private String nombre;
	private boolean solucion;
	private Search busqueda = null;
	private SearchAgent agente = null;
	private Problem problema = null;
	private Estado estado = null;
	private Objetivo objetivo = null;
	
	public Mono(){
		this.nombre = "Problema del Mono";
		this.solucion = false;
		this.estado = new Estado();
		this.objetivo = new Objetivo(this);
		this.problema = new Problem(this.estado, new FuncionSucesora(), this.objetivo, new FuncionHeuristica());
	}
		
	public boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(boolean solucion) {
		this.solucion = solucion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setBusqueda(Search busqueda) {
		this.busqueda = busqueda;
	}

	public SearchAgent getAgente() {
		return agente;
	}


	public void setAgente(SearchAgent agente) {
		this.agente = agente;
	}


	public Problem getProblema() {
		return problema;
	}


	public void setProblema(Problem problema) {
		this.problema = problema;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Objetivo getObjetivo() {
		return objetivo;
	}


	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}


	public Search getBusqueda() {
		return busqueda;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public boolean ejecutar() {
        this.busqueda = new AStarSearch(new TreeSearch());
		try {
            this.agente = new SearchAgent(this.problema, this.busqueda);
            System.out.println("Estado inicial: Mono en la puerta y la caja en el lado de la ventana");
            this.imprimir(this.agente.getActions());
            this.imprimirPropiedades(this.agente.getInstrumentation());
            if(this.getSolucion())
                System.out.println("ES solucion");
            else
                System.out.println("NO es solucion");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.getSolucion();		
	}

	@Override
	public String imprimir(List eventos) {
        String ret = "";
        for (int i = 0; i < eventos.size(); i++) {
            String action = (String) eventos.get(i);
            System.out.println(action);
            ret+=action+"\n";
        }
        return ret;
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