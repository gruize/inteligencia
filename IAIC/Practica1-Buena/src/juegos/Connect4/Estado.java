package juegos.Connect4;

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
    
    public Estado(Estado estado, Momento momento){
        this.instante = momento;
        this.contenido = estado.getContenido();
        this.contenido.add(this.instante);
    }

    public Vector<Momento> getContenido() {
        return contenido;
    }

    public Momento getContenido(int i){
        return this.contenido.elementAt(i);
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
    
    public boolean permiteMovimiento(Momento posible){
        boolean valido = false;
        int i = 0;
        while(valido && (i < this.getContenido().size())){
            if(this.getContenido(i).equals(posible))
                valido = false;
            i++;
        }
        return valido;
    }
}
