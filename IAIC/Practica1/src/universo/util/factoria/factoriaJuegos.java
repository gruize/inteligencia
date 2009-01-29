package universo.util.factoria;

import juegos.*;
import juegos.BlancasNegras.BlancasNegras;
import juegos.CajaColores.CajaDeColores;
import juegos.Canibal.Misionero;
import juegos.Garrafas.Garrafas;
import juegos.LoboCabraCol.LCC;
import juegos.Pollos.Pollos;
import juegos.Puzzle.Puzzle;
import juegos.Reinas.Reinas;
import juegos.Reinas.ReinasSinHeuristica;
import juegos.Robot.Robot;

public class factoriaJuegos {
	
	public factoriaJuegos(){
		
	}
	
	/**
	 * 
	 * @param juego
	 * @return Juego
	 * 
	 * Se devuelve la interfaz del juego solicitado.
	 * 
	 * para a�adir mas juegos simplemente hay que a�adir
	 * en esta clase clausulas else if con el nombre del nuevo juego 
	 * del fichero de configuracion.
	 * 
	 */
	
	public Juego dameJuego(String juego){
		
		if (juego.equals("Blancas vs. Negras")){
			return new BlancasNegras();
		}else if (juego.equals("Caja de Colores Rojo vs Azul")){
			return new CajaDeColores();
		}else if (juego.equals("Misioneros vs. Canibales")){
			return new Misionero();
		}else if (juego.equals("Garrafas de capacidades diferentes")){
			return new Garrafas();
		}else if (juego.equals("Problema del Lobo la Cabra y la Col")){
			return new LCC();
		}else if (juego.equals("Problema de los Pollos")) {
			return new Pollos();
		}else if (juego.equals("8-Puzzle")){
			return new Puzzle();
		}else if (juego.equals("Problema de las N Reinas")){
			return new Reinas();
		}else if (juego.equals("Problema de las N Reinas Sin Heuristica")){
			return new ReinasSinHeuristica();
		}else if (juego.equals("Problema del Robot")){
			return new Robot();
		}else {
			// No se ha pasado un String valido
			// Esto implica devolver null.
			return null;
		}
		
	}
}
