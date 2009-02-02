package juegos.Bloques;

import java.util.Vector;

public class Estado {
	
    private Momento bloques;
    private Vector<Momento> recorrido;

    public Estado() {
    	this.bloques = new Momento();
    	this.recorrido = new Vector<Momento>();
    	this.recorrido.add(bloques);
    }
    public Estado(Momento instante, Vector<Momento> recorrido){
    	this.bloques = instante;
    	this.recorrido = recorrido;
    	this.recorrido.add(instante);            
    }
    
    public boolean isFinal(){
            return (this.bloques.getBloques()[0].equals(Posiciones.B)
            && this.bloques.getBloques()[1].equals(Posiciones.C)
            && this.bloques.getBloques()[2].equals(Posiciones.mesa));
    }
    
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

