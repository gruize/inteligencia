package juegos.Puzzle;

import java.util.Vector;

/**
 *
 * @author Grupo C15
 */
public class Estado {

    private Momento instante;
    private Vector<Momento> contenido;
    
    /**
     * Constructor por defecto
     */
    public Estado(){
        this.instante = new Momento();
        this.contenido = new Vector<Momento>();
        this.contenido.add(instante);
    }
    
    /**
     * Constructor parametrizado
     * @param estado Estado anterior
     * @param momento Momento que forma parte del estado actual
     */
    public Estado(Estado estado, Momento momento){
        this.instante = momento;
        this.contenido = estado.getContenido();
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
    
    /**
     * Devuelve el valor heuristico del estado
     * @return
     */
    public double obtenerValorHeuristico(){
        return this.getInstante().obtenerValorHeuristico();
    }
    
    /**
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido
     * del arbol de busqueda.
     * @param posible Siguiente posible momento
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
    public boolean permiteMovimiento(Momento posible){
        boolean resultado = true;
        int i = 0;
        while(resultado && (i < this.getContenido().size())){            
            if(this.getContenido().get(i).equals(posible))
                resultado = false;
            i++;
        }
        return resultado;
    }
}
