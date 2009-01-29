package juegos.Robot;

import aima.basic.XYLocation;
import java.util.Vector;

/**
 *
 * @author GabiPC
 */
class Estado {

    private Momento instante;
    private Vector<Momento> contenido;
    private XYLocation posFinal;
    
    public Estado(XYLocation origen, XYLocation destino){
        this.posFinal = destino;
        this.instante = new Momento(origen);
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }
    
    public Estado(Estado actual, Momento posible){
        this.posFinal = actual.getPosFinal();
        this.instante = posible;
        this.contenido = actual.getContenido();
        this.contenido.add(posible);
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

    public XYLocation getPosFinal() {
        return posFinal;
    }

    public void setPosFinal(XYLocation posFinal) {
        this.posFinal = posFinal;
    }

    public boolean permiteMovimiento(Momento siguiente){
        boolean posible = true;
        int i = 0;
        while (posible && (i < this.getContenido().size())){
            if(this.getContenido().get(i).equals(siguiente))
                posible = false;
            i++;
        }
        return posible;
    }
    
    public double generarHeuristica(){
        return Math.abs(this.getInstante().posRobot().getXCoOrdinate() 
                        - this.getPosFinal().getXCoOrdinate())
             + Math.abs(this.getInstante().posRobot().getYCoOrdinate() 
                        - this.getPosFinal().getYCoOrdinate());      
    }
}
