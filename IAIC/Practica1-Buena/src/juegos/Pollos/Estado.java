package juegos.Pollos;

import java.util.Vector;

/**
 *
 * @author GabiPC
 */
public class Estado {

    private Momento instante;
    private Vector<Momento> contenido;
    
    public Estado(){
        this.instante = new Momento();
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }
    
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
    
    public double generarCoste(Estado hijo) {
        return this.getInstante().generarCoste(hijo.getInstante());
    }
    
    public double generarHeuristica(){
        return this.getInstante().generarHeuristica();
    }
    
}
