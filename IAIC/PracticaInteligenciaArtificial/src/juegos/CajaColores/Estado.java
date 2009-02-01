package juegos.CajaColores;

import java.util.Vector;

/**
 *
 * @author usuario_local
 */
public class Estado {

    private int tamanno;
    //Cube lo tiene como final
    private Momento instante;
    //Cube lo tiene como final    
    private Vector<Momento> contenido;

    public Estado(int tamanno) {
        this.tamanno = tamanno;
        this.instante = new Momento(tamanno);
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }
    
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
    
    public double obtenerValorHeuristico(){
        return this.getInstante().obtenerValorHeuristico();
    }
    
}
