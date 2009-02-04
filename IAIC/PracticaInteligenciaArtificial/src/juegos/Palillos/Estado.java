package juegos.Palillos;

import java.util.Vector;

public class Estado {

	Momento instante;
	private Vector<Momento> recorrido;
	
	/**
	 * Constructor por defecto
	 */
	public Estado() {
		this.instante = new Momento();
		this.recorrido = new Vector<Momento>();
		this.recorrido.add(this.instante);
	}
	
	/**
	 * Constructor parametrizado
	 * @param padre Estado anterior
	 * @param actual Momento que forma al estado actual.
	 */
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
	
	/**
	 * Devuelve el valor heuristico
	 * @return
	 */
	public double generarHeuristica(){
		return this.getInstante().generarHeuristica();
	}
	
    /**
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido
     * del arbol de busqueda.
     * @param posible Siguiente posible momento
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
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
