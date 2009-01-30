package run;

import java.util.Hashtable;

import juegos.Juego;
import universo.Universo;
import universo.util.Enlace;
import universo.util.Nodo;
import conector.GeneraUniverso;

public class Main {
	public static void main(String[] args) throws Exception {

		
		/** Generamos un universo cualquiera
		 *  y llamamos al Universo para que lo capture
		 *  El Universo usa el patron Singleton para generar una sola isntancia del
		 *  conector se llame cuantas veces se llame al universo.
		 *  
		 *  modo de empleo:
		 *  Universo.getInstance(); -> Devuelve el conector con sus metodos y atributos.
		 *  
		 *  De momento el fichero de entrada para el universo es
		 *  siempre el mismo planetas.txt
		 *  
		 *  y el generador de universo lo sobreescribe siempre.
		 *  (Es algo parametrizable pero no preocupa ahora mismo.)
		 *  
		 */
		GeneraUniverso uni = new GeneraUniverso("planetas.plan");
		uni.generar();
		System.out.println(uni.getClass());
		
		Hashtable<Integer, Nodo> nodosUniverso = Universo.getInstancia().getNodosH();
		Hashtable<Integer, Nodo> nodosDestino = Universo.getInstancia().getDestinos();
		Nodo nodoOrigen = Universo.getInstancia().getOrigen();
		System.out.println(nodosUniverso.getClass());
		System.out.println(nodosDestino.getClass());
		System.out.println(nodoOrigen.getClass());

		
		/**
		 * 
		 * Lectura del fichero de juegos.
		 *     
		 */
		
		Enlace enlacetmp = new Enlace();
		enlacetmp.setDestino(2);
		enlacetmp.setDistancia(2);
		enlacetmp.setJuego(9);
		
		Juego game = enlacetmp.dameJuego();
		
		if ( game != null ){
			game.ejecutar();
		}
	}


}
