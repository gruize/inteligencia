package juegos.BlancasNegras;

import java.util.Vector;

/**
 * @author Grupo C15
 */
public class Estado {

    private BlancasNegras bn;
    private Momento instante;
    private Vector<Momento> contenido;

    /**
     * Contructor parametrizado
     * @param bn BlancasNegras
     */
    public Estado(BlancasNegras bn) {
        this.bn = bn;
        this.instante = new Momento();
        this.contenido = new Vector<Momento>();
        this.contenido.add(this.instante);
    }

    /**
     * Constructor parametrizado
     * @param bn BlancasNegras
     * @param instante Nuevo Momento
     * @param contenido Recorrido
     */
    public Estado(BlancasNegras bn, Momento instante, Vector<Momento> contenido) {
        this.bn = bn;
        this.instante = instante;
        this.contenido = (Vector<Momento>) contenido.clone();
        this.contenido.add(this.instante);
    }

    /**
     * Constructor parametrizado
     * @param instante Nuevo momento
     * @param padre Estado anterior
     */
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
    
    /**
     * Calcula el coste necesario para pasar del estado actual al estado hijo.
     * @param hijo Posible siguiente estado
     * @return Valor del coste.
     */
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
        for(int i = 6; i >= 0; i--){
            if(actual[i] == Fichas.Blanca)
                p--;
            if(actual[i] == Fichas.Negra)
                heuristica= p;                
        }        
        return heuristica;
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
