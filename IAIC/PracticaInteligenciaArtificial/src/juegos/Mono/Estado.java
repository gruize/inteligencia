package juegos.Mono;

import java.util.Vector;

import juegos.Mono.Momento;

public class Estado {

	private Momento instante;
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
	 * @param padre Estado
	 * @param nuevo Momento
	 */
	public Estado(Estado padre, Momento nuevo) {
		this.instante = nuevo;
		this.recorrido = padre.getRecorrido();
		this.recorrido.add(nuevo);
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
	
	/**
     * Indica si un momento ya ha sido creado en algun otro punto del recorrido
     * del arbol de busqueda.
	 * @param posible
	 * @return
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
