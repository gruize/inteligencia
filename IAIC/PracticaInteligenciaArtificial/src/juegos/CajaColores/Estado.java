package juegos.CajaColores;

import java.util.Vector;

/**
 * @author Grupo C15
 */
public class Estado {

    private int tamanno;
    //Cube lo tiene como final
    private Momento instante;
    //Cube lo tiene como final    
    private Vector<Momento> contenido;

    /**
     * Constructor parametrizado
     * @param tamanno
     */
    public Estado(int tamanno) {
        this.tamanno = tamanno;
        this.instante = new Momento(tamanno);
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }
    
    /**
     * Constructor parametrizado
     * @param tamanno
     * @param momento
     * @param estado
     */
    public Estado(int tamanno, Momento momento, Estado estado){
        this.tamanno = tamanno;
        this.instante = momento;
        this.contenido = new Vector<Momento>();
        for(int i = 0; i < estado.getContenido().size(); i++)
            this.contenido.add(i,estado.getContenido().get(i));
        this.contenido.add(momento);
    }

    public Vector<Momento> getContenido() {
        return contenido;
    }

    public void setContenido(Vector<Momento> contenido) {
        this.contenido = contenido;
    }

    public Momento getInstante() {
        return instante;
    }

    public void setInstante(Momento instante) {
        this.instante = instante;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }
    
    /**
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido
     * del arbol de busqueda.
     * @param momento Siguiente posible momento
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
    public boolean permiteMovimiento(Momento momento){
    	boolean resultado = true;
        int i = 0;
        while((i < this.getContenido().size()) && resultado){
            if(momento.equals(this.getContenido().get(i)))
                resultado = false;
            i++;
        }
        return resultado;        
    }
    
    /**
     * Obtiene el valor heuristico de este estado
     * @return
     */
    public double obtenerValorHeuristico(){
        return this.getInstante().obtenerValorHeuristico();
    }
    
}
