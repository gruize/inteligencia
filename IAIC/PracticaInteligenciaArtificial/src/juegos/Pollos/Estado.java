package juegos.Pollos;

import java.util.Vector;

/**
 *
 * @author GabiPC
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
        this.contenido.add(this.instante);
    }
    
    /**
     * Constructor parametrizado
     * @param padre Estado anterior
     * @param actual Momento que forma el estado actual
     */
    public Estado(Estado padre, Momento actual){
        this.instante = actual;
        this.contenido = padre.getContenido();
        this.contenido.add(actual);
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
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido
     * del arbol de busqueda.
     * @param nuevo Siguiente posible momento
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */ 
    public boolean movimientoPosible(Momento nuevo){
        boolean posible = true;
        int i = 0;
        while(posible && (i < this.getContenido().size())){
            if(this.getContenido().get(i).equals(nuevo))
                posible = false;
            i++;
        }
        return posible;
    }
    
    /**
     * Devuelve el coste generado para ir hasta el Estado hijo
     * @param hijo Estado destino
     * @return
     */
    public double generarCoste(Estado hijo) {
        return this.getInstante().generarCoste(hijo.getInstante());
    }
    
    /**
     * Devuelve el valor heuristico de este estado
     * @return
     */
    public double generarHeuristica(){
        return this.getInstante().generarHeuristica();
    }
    
}
