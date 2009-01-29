package juegos.BlancasNegras;

import java.util.Vector;

/**
 *
 * @author GabiPC
 */
public class Estado {

    private BlancasNegras bn;
    private Momento instante;
    private Vector<Momento> contenido;

    public Estado(BlancasNegras bn) {
        this.bn = bn;
        this.instante = new Momento();
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }

    public Estado(BlancasNegras bn, Momento instante, Vector<Momento> contenido) {
        this.bn = bn;
        this.instante = instante;
        this.contenido = (Vector<Momento>) contenido.clone();
        this.contenido.add(this.instante);
    }

    public Estado(Momento instante, Estado padre) {
        this.instante = instante;
        this.contenido = (Vector<Momento>) padre.getContenido().clone();
        this.contenido.add(this.instante);
    }   
    
    public BlancasNegras getBn() {
        return bn;
    }

    public void setBn(BlancasNegras bn) {
        this.bn = bn;
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
    
    public double generarCoste(Estado hijo) {
        return this.instante.generarCoste(hijo.getInstante());
    }

    /**
     * Numero de fichas Negra a la izquierda de cada ficha Blanca
     * @return Heuristica
     */
    public double generarHeuristica(){
        double heuristica = 0.0;
        Fichas[] actual = this.instante.getFichas();
        int p = 0;
        for(int i = 6; i == 0; i--){
            if(actual[i] == Fichas.Blanca)
                p++;
            if(actual[i] == Fichas.Negra)
                heuristica+= p;                
        }        
        return heuristica;
    }
    
    public boolean movimientoPosible(Momento nuevo){
        boolean posible = true;
        //if(this.bn.controlaCiclos()){
            int i = 0;
            while((posible) && (i < this.contenido.size())){
                if(nuevo.equals(this.contenido.get(i)))
                    posible = false;
                i++;
            }
        //}                    
        return posible;
    }

    @Override
    public boolean equals(Object obj) {
        Momento temp = ((Estado)obj).getInstante();        
        return this.getInstante().equals(temp);
    }
    
}
