package juegos.Puzzle;

import java.util.Vector;

/**
 *
 * @author usuario_local
 */
public class Estado {

    private Momento instante;
    private Vector<Momento> contenido;
    
    public Estado(){
        this.instante = new Momento();
        this.contenido = new Vector<Momento>();
        this.contenido.add(instante);
    }
    
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
    
    public double obtenerValorHeuristico(){
        return this.getInstante().obtenerValorHeuristico();
    }
    
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