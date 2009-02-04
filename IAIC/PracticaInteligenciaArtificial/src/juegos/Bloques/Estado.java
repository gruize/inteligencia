package juegos.Bloques;

import java.util.Vector;

/**
 * @author Grupo C15
 */
public class Estado {
	
    private Momento bloques;
    private Vector<Momento> recorrido;

    /**
     * Constructor por defecto
     */
    public Estado() {
    	this.bloques = new Momento();
    	this.recorrido = new Vector<Momento>();
    	this.recorrido.add(bloques);
    }
    
    /**
     * Constructor parametrizado
     * @param instante
     * @param recorrido
     */
    public Estado(Momento instante, Vector<Momento> recorrido){
    	this.bloques = instante;
    	this.recorrido = recorrido;
    	this.recorrido.add(instante);            
    }
    
    /**
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido
     * del arbol de busqueda.
     * @param o Siguiente posible momento
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
    public boolean permiteMovimiento(Momento o){
        boolean posible = true;
    	for(int i = 0; i < this.recorrido.size(); i++)
            if(this.recorrido.get(i).equals(o))
            	posible = false;
        return posible;
    }
    public Momento getBloques() {
            return this.bloques;
    }
    public void setBloques(Momento bloques) {
            this.bloques = bloques;
    }
    public Vector<Momento> getRecorrido() {
            return this.recorrido;
    }
    public void setRecorrido(Vector<Momento> recorrido) {
            this.recorrido = recorrido;
    }
    
}

