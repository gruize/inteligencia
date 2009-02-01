package juegos.Palillos;

import java.util.Vector;

public class Estado {

	Momento instante;
	private Vector<Momento> recorrido;
	
	public Estado() {
		this.instante = new Momento();
		this.recorrido = new Vector<Momento>();
		this.recorrido.add(this.instante);
	}
	
	public Estado(Estado padre, Momento actual){
		this.instante = actual;
		this.recorrido = padre.getRecorrido();
		this.recorrido.add(actual);
	}

	public Momento getInstante() {
		return instante;
	}

	public void setInstante(Momento instante) {
		this.instante = instante;
	}

	public Vector<Momento> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Vector<Momento> recorrido) {
		this.recorrido = recorrido;
	}	
	
	public double generarHeuristica(){
		return this.getInstante().generarHeuristica();
	}
	
    public boolean permiteMovimiento(Momento posible){
        boolean resultado = true;
        int i = 0;
        while(resultado && (i < this.getRecorrido().size())){            
            if(this.getRecorrido().get(i).equals(posible))
                resultado = false;
            i++;
        }
        return resultado;
    }
	
}
